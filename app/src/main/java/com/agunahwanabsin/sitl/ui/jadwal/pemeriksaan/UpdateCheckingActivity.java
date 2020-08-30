package com.agunahwanabsin.sitl.ui.jadwal.pemeriksaan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.api.instance.DetailPengecekanInterface;
import com.agunahwanabsin.sitl.api.instance.StatusObjectInterface;
import com.agunahwanabsin.sitl.api.instance.TindakanInterface;
import com.agunahwanabsin.sitl.api.model.request.DetailPengecekanRequest;
import com.agunahwanabsin.sitl.api.services.DetailPengecekanApiService;
import com.agunahwanabsin.sitl.api.services.StatusObjectApiService;
import com.agunahwanabsin.sitl.api.services.TindakanApiService;
import com.agunahwanabsin.sitl.helper.PermissionHelper;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.ConstantObject;
import com.agunahwanabsin.sitl.library.ImageLibrary;
import com.agunahwanabsin.sitl.model.Beekeper;
import com.agunahwanabsin.sitl.model.DetailPengecekan;
import com.agunahwanabsin.sitl.model.StatusObject;
import com.agunahwanabsin.sitl.model.Tindakan;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agunahwanabsin.sitl.library.CommonLibrary.isDeviceSupportCamera;

public class UpdateCheckingActivity extends AppCompatActivity {

    // Global Constant
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_INTENT_CALLED = 2;
    static final int GALLERY_KITKAT_INTENT_CALLED = 3;

    // Global Variable
    SessionManager session;
    Beekeper beekeper;
    String pathImage;
    List<StatusObject> listStatusObject;
    List<Tindakan> listTindakan;
    PermissionHelper permissionHelper;
    Context mContext;
    StatusObject currentStatusObject;
    Tindakan currentTindakan;
    String objectName;

    // View Object
    TextView txtObject;
    ImageView ivCamera;
    Spinner spnStatusObject;
    Spinner spnTindakan;
    EditText txtKeterangan;
    Button btnCamera;
    Button btnGallery;
    Button btnSimpan;

    // Extra data
    Bundle extra;
    int idObject;
    int idJadwalPengecekan;
    String kodeHasilPengecekan;

    // Adapters
    ArrayAdapter<StatusObject> statusObjectAdapter;
    ArrayAdapter<Tindakan> tindakanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_checking);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = getApplicationContext();

        // Session Manager
        session = new SessionManager(getApplicationContext());
        beekeper = session.getUserDetails();

        // Declare object view
        txtObject = (TextView) findViewById(R.id.object);
        ivCamera = (ImageView) findViewById(R.id.fotoKamera);
        spnStatusObject = (Spinner) findViewById(R.id.status_object);
        spnTindakan = (Spinner) findViewById(R.id.tindakan);
        txtKeterangan = (EditText) findViewById(R.id.keterangan);
        btnCamera = (Button) findViewById(R.id.camera);
        btnGallery = (Button) findViewById(R.id.gallery);
        btnSimpan = (Button) findViewById(R.id.simpan);

        // Permission
        permissionHelper = new PermissionHelper(getApplicationContext(), UpdateCheckingActivity.this);
        permissionHelper.checkReadExternalStoragePermission();

        // Populate Spinner
        getAllStatusObject();
        getAllTindakan();

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
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicture();
            }
        });
        spnStatusObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentStatusObject = (StatusObject) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnTindakan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentTindakan = (Tindakan) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Get extra
        extra = getIntent().getExtras();

        if (extra != null) {
            idJadwalPengecekan = extra.getInt(ConstantObject.ID_JADWAL_PENGECEKAN);
            idObject = extra.getInt(ConstantObject.ID_OBJECT);
            kodeHasilPengecekan = extra.getString(ConstantObject.KODE_HASIL_PENGECEKAN);
            objectName = extra.getString(ConstantObject.OBJECT);
        }

        txtObject.setText(objectName);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetailPengecekan();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getDetailPengecekan();
    }

    private void saveDetailPengecekan() {
        try {
            //pass it like this
            File file = new File(pathImage);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("foto", file.getName(), requestFile);

            DetailPengecekanInterface service = DetailPengecekanApiService.save();
            Call<ResponseBody> call = service.save(
                    body,
                    idJadwalPengecekan,
                    kodeHasilPengecekan,
                    idObject,
                    currentStatusObject.getIdStatusObject(),
                    currentTindakan.getIdTindakan(),
                    txtKeterangan.getText().toString(),
                    beekeper.getBeekeper()
            );
            Callback<ResponseBody> callback = new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String isSuccess = response.body().string();
                            if (isSuccess.equals("true")) {
                                Toast.makeText(mContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
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
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    private void getDetailPengecekan() {
        try {
            DetailPengecekanRequest detailPengecekanRequest = new DetailPengecekanRequest(idJadwalPengecekan, idObject);
            DetailPengecekanInterface service = DetailPengecekanApiService.getDetailPengecekan();
            Call<List<DetailPengecekan>> call = service.getDetailPengecekan(detailPengecekanRequest);
            Callback<List<DetailPengecekan>> callback = new Callback<List<DetailPengecekan>>() {

                @Override
                public void onResponse(Call<List<DetailPengecekan>> call, Response<List<DetailPengecekan>> response) {
                    if (response.isSuccessful()) {
                        List<DetailPengecekan> detailPengecekanList = response.body();

                        // Populate data
                        if (detailPengecekanList.size() > 0) {
                            String urlImage = ConstantObject.URL_FOLDER_IMAGE + detailPengecekanList.get(0).getImage();

                            Picasso.get().load(urlImage).into(ivCamera);

                            int idStatusObject = detailPengecekanList.get(0).getIdStatusObject();
                            if (idStatusObject != 0) {
                                StatusObject selectedStatusObject = new StatusObject();
                                for (StatusObject statusObject : listStatusObject) {
                                    if (statusObject.getIdStatusObject() == idStatusObject) {
                                        selectedStatusObject = statusObject;
                                        break;
                                    }
                                }
                                int spinnerPosition = statusObjectAdapter.getPosition(selectedStatusObject);
                                spnStatusObject.setSelection(spinnerPosition);
                            }
                            int idTindakan = detailPengecekanList.get(0).getIdTindakan();
                            if (idTindakan != 0) {
                                Tindakan selectedTindakan = new Tindakan();
                                for (Tindakan tindakan : listTindakan) {
                                    if (tindakan.getIdTindakan() == idTindakan) {
                                        selectedTindakan = tindakan;
                                        break;
                                    }
                                }
                                int spinnerPosition = tindakanAdapter.getPosition(selectedTindakan);
                                spnTindakan.setSelection(spinnerPosition);
                            }
                            txtKeterangan.setText(detailPengecekanList.get(0).getKeterangan());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<DetailPengecekan>> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    private void getAllStatusObject() {
        try {
            StatusObjectInterface service = StatusObjectApiService.getListStatusObject();
            Call<List<StatusObject>> call = service.getListStatusObject();
            Callback<List<StatusObject>> callback = new Callback<List<StatusObject>>() {

                @Override
                public void onResponse(Call<List<StatusObject>> call, Response<List<StatusObject>> response) {
                    if (response.isSuccessful()) {
                        listStatusObject = response.body();

                        statusObjectAdapter = new ArrayAdapter<StatusObject>(mContext, android.R.layout.simple_spinner_item, listStatusObject);

                        // Drop down layout style - list view
                        statusObjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Set Adapter to spiner
                        spnStatusObject.setAdapter(statusObjectAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<StatusObject>> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    public ArrayList<String> statusObjectToArrayList() {
        ArrayList<String> result = new ArrayList<>();

        for (StatusObject statusObject :
                listStatusObject) {
            result.add(statusObject.getStatusObject());
        }

        return result;
    }

    public void getAllTindakan() {
        try {
            TindakanInterface service = TindakanApiService.getListTindakan();
            Call<List<Tindakan>> call = service.getListTindakan();
            Callback<List<Tindakan>> callback = new Callback<List<Tindakan>>() {

                @Override
                public void onResponse(Call<List<Tindakan>> call, Response<List<Tindakan>> response) {
                    if (response.isSuccessful()) {
                        listTindakan = response.body();

                        tindakanAdapter = new ArrayAdapter<Tindakan>(mContext, android.R.layout.simple_spinner_item, listTindakan);

                        // Drop down layout style - list view
                        tindakanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Set Adapter to spiner
                        spnTindakan.setAdapter(tindakanAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Tindakan>> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    public ArrayList<String> tindakanToArrayList() {
        ArrayList<String> result = new ArrayList<>();

        for (Tindakan tindakan :
                listTindakan) {
            result.add(tindakan.getTindakan());
        }

        return result;
    }

    private void takeCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void getPicture() {
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), GALLERY_INTENT_CALLED);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
        }
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
                case GALLERY_INTENT_CALLED:
                    originalUri = data.getData();

                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(originalUri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    picturePath += cursor.getString(columnIndex); // returns null
                    cursor.close();

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), originalUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case GALLERY_KITKAT_INTENT_CALLED:
                    originalUri = data.getData();
                    final int takeFlags = data.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    // Check for the freshest data.
                    getContentResolver().takePersistableUriPermission(originalUri, takeFlags);

                    /* now extract ID from Uri path using getLastPathSegment() and then split with ":"
                    then call get Uri to for Internal storage or External storage for media I have used getUri()
                    */
                    String id = originalUri.getLastPathSegment().split(":")[1];
                    final String[] imageColumns = {MediaStore.Images.Media.DATA};
                    final String imageOrderBy = null;

                    Uri uri = ImageLibrary.getUri();

                    Cursor imageCursor = managedQuery(uri, imageColumns, MediaStore.Images.Media._ID + "=" + id, null, imageOrderBy);

                    if (imageCursor.moveToFirst()) {
                        picturePath += imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    }

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), originalUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }

            ivCamera.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}