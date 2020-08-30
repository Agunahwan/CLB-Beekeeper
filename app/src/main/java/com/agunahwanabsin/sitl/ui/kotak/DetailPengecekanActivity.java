package com.agunahwanabsin.sitl.ui.kotak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.api.instance.DetailPengecekanInterface;
import com.agunahwanabsin.sitl.api.model.request.DetailPengecekanRequest;
import com.agunahwanabsin.sitl.api.services.DetailPengecekanApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.ConstantObject;
import com.agunahwanabsin.sitl.model.DetailPengecekan;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPengecekanActivity extends AppCompatActivity {

    // View Object
    TextView txtObject;
    TextView txtStatusObject;
    TextView txtTindakan;
    TextView txtKeterangan;
    ImageView imgFoto;
    Button btnClose;

    // Global Variable
    List<DetailPengecekan> listDetailPengecekan;
    String objectName;

    // Session Manager Class
    SessionManager session;

    // Extra data
    Bundle extra;
    int idObject;
    int idHasilPengecekan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengecekan);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        // Declare object view
        txtObject = (TextView) findViewById(R.id.object);
        txtStatusObject = (TextView) findViewById(R.id.status_object);
        txtTindakan = (TextView) findViewById(R.id.tindakan);
        txtKeterangan = (TextView) findViewById(R.id.keterangan);
        imgFoto = (ImageView) findViewById(R.id.image);
        btnClose = (Button) findViewById(R.id.close);

        // Get extra
        extra = getIntent().getExtras();

        if (extra != null) {
            idObject = extra.getInt(ConstantObject.ID_OBJECT);
            idHasilPengecekan = extra.getInt(ConstantObject.ID_HASIL_PENGECEKAN);
            objectName = extra.getString(ConstantObject.OBJECT);

            loadDetailPengecekan();
        }

        txtObject.setText(objectName);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadDetailPengecekan() {
        DetailPengecekanRequest detailPengecekanRequest = new DetailPengecekanRequest(idHasilPengecekan, idObject);
        DetailPengecekanInterface service = DetailPengecekanApiService.getDetailPengecekan();
        Call<List<DetailPengecekan>> call = service.getDetailPengecekan(detailPengecekanRequest);
        Callback<List<DetailPengecekan>> callback = new Callback<List<DetailPengecekan>>() {
            @Override
            public void onResponse(Call<List<DetailPengecekan>> call, Response<List<DetailPengecekan>> response) {
                if (response.isSuccessful()) {
                    listDetailPengecekan = response.body();
                    if (listDetailPengecekan.size() > 0) {
                        DetailPengecekan detailPengecekan = listDetailPengecekan.get(0);

                        String urlImage = ConstantObject.URL_FOLDER_IMAGE + detailPengecekan.getImage();

                        // Set view
                        txtStatusObject.setText(detailPengecekan.getStatusObject());
                        txtTindakan.setText(detailPengecekan.getTindakan());
                        txtKeterangan.setText(detailPengecekan.getKeterangan());
                        Picasso.get().load(urlImage).into(imgFoto);
                    } else {
                        Toast.makeText(getApplicationContext(), "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DetailPengecekan>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}