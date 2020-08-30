package com.agunahwanabsin.sitl.ui.absensi;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.MainActivity;
import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.api.instance.AbsensiInterface;
import com.agunahwanabsin.sitl.api.instance.StatusObjectInterface;
import com.agunahwanabsin.sitl.api.services.AbsensiApiService;
import com.agunahwanabsin.sitl.api.services.KotakApiService;
import com.agunahwanabsin.sitl.api.services.StatusObjectApiService;
import com.agunahwanabsin.sitl.helper.PermissionHelper;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.CommonLibrary;
import com.agunahwanabsin.sitl.library.ImageLibrary;
import com.agunahwanabsin.sitl.model.Beekeper;
import com.agunahwanabsin.sitl.model.StatusObject;
import com.agunahwanabsin.sitl.ui.jadwal.pemeriksaan.UpdateCheckingActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agunahwanabsin.sitl.library.CommonLibrary.isDeviceSupportCamera;

public class AbsensiActivity extends AppCompatActivity {

    // Global Constant
    static final int REQUEST_IMAGE_CAPTURE = 1;

    // Global Variable
    Context mContext;
    SessionManager session;
    String pathImage;
    Camera camera;
    int cameraId = 0;
    Beekeper beekeper;
    FusedLocationProviderClient mFusedLocation;
    LocationRequest locationRequest;
    LocationCallback mLocationCallback;
    PermissionHelper permissionHelper;
    double currentLongitude, currentLatitude;
    ProgressDialog loading;

    // View Object
    ImageView ivCamera;
    TextView txtPhoto;
    TextView txtTanggal;
    TextView txtLongitude;
    TextView txtLatitude;
    Button btnCamera;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        // Set Context
        mContext = getApplicationContext();

        // Declare object view
        ivCamera = (ImageView) findViewById(R.id.fotoKamera);
        txtPhoto = (TextView) findViewById(R.id.photo);
        txtTanggal = (TextView) findViewById(R.id.tanggal);
        txtLongitude = (TextView) findViewById(R.id.longitude);
        txtLatitude = (TextView) findViewById(R.id.latitude);
        btnCamera = (Button) findViewById(R.id.camera);
        btnSimpan = (Button) findViewById(R.id.simpan);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        beekeper = session.getUserDetails();

//        loading = new ProgressDialog(mContext);
//        loading.setMessage(getString(R.string.msg_loading));
//        loading.show();

        checkAbsensiToday();

        // Permission
        permissionHelper = new PermissionHelper(getApplicationContext(), AbsensiActivity.this);
        permissionHelper.checkReadExternalStoragePermission();

        // Set Text
        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        txtTanggal.setText("Tanggal : " + today);
        getCurrentLocation();

        // Action
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDeviceSupportCamera(getApplicationContext())) {
                    takeCamera();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.alert_not_camera, Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAbsensi();
            }
        });
    }

    private void checkAbsensiToday() {
        try {
            AbsensiInterface service = AbsensiApiService.checkAbsensiToday();
            Call<Integer> call = service.checkAbsensiToday(beekeper.getIdBeekeper());
            Callback<Integer> callback = new Callback<Integer>() {

                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        int countAbsensi = response.body();

                        if (countAbsensi > 0) {
                            moveToMain();
                        }
                    }
//                    loading.dismiss();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
//                    loading.dismiss();
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    private void getCurrentLocation() {
        buildLocationRequest();
        buildLocationCallback();

        permissionHelper.checkAccessFineLocationPermission();

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private void buildLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in TextView
                    txtLongitude.setText("Long : " + location.getLongitude());
                    txtLatitude.setText("Lat : " + location.getLatitude());
                    currentLongitude = location.getLongitude();
                    currentLatitude = location.getLatitude();
                }
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }

    private void takeCamera() {
        permissionHelper.checkReadExternalStoragePermission();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void takeFrontCamera() {
        try {
            permissionHelper.checkReadExternalStoragePermission();

            // do we have a camera?
            if (!getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                        .show();
            } else {
                cameraId = findFrontFacingCamera();
                releaseCameraAndPreview();
                if (cameraId < 0) {
                    Toast.makeText(this, "No front facing camera found.",
                            Toast.LENGTH_LONG).show();
                } else {
//                    if (cameraId == 0) {
//                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
//                    } else {
//                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//                    }
                    camera = Camera.open(cameraId);
                }
            }
        } catch (Exception e) {
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }
    }

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private void saveAbsensi() {
        //pass it like this
        File file = new File(pathImage);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("foto", file.getName(), requestFile);

        AbsensiInterface service = AbsensiApiService.save();
        Call<ResponseBody> call = service.save(
                body,
                session.getUserDetails().getIdBeekeper(),
                currentLongitude,
                currentLatitude,
                session.getUserDetails().getBeekeper()
        );
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String isSuccess = response.body().string();
                        if (isSuccess.equals("true")) {
                            Toast.makeText(mContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            moveToMain();
                        } else {
                            // Jika login gagal
                            Toast.makeText(mContext, isSuccess, Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        Toast.makeText(mContext, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d("Absensi", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private void moveToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String picturePath = "Path : ";
            Bitmap bitmap = null;
            Uri originalUri = null;

            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    originalUri = ImageLibrary.getImageUri(getApplicationContext(), bitmap);

                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    pathImage = ImageLibrary.getRealPathFromURI(getApplicationContext(), originalUri);
                    picturePath += pathImage;
                    break;
            }

            ivCamera.setImageBitmap(bitmap);
            txtPhoto.setText(picturePath);
        }
    }
}