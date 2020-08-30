package com.agunahwanabsin.sitl.ui.jadwal.pemeriksaan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.ObjectAdapter;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.api.instance.KotakInterface;
import com.agunahwanabsin.sitl.api.instance.ObjectInterface;
import com.agunahwanabsin.sitl.api.instance.PengecekanInterface;
import com.agunahwanabsin.sitl.api.model.RequestObject;
import com.agunahwanabsin.sitl.api.services.KotakApiService;
import com.agunahwanabsin.sitl.api.services.ObjectApiService;
import com.agunahwanabsin.sitl.api.services.PengecekanApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.ConstantObject;
import com.agunahwanabsin.sitl.model.ObjectKotak;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckingActivity extends AppCompatActivity implements OnListListener {

    // View Object
    TextView txtBlokKotak;
    RecyclerView rvObject;

    // Global Object
    ObjectAdapter objectAdapter;
    List<ObjectKotak> listObject;
    Context mContext;

    // Session Manager Class
    SessionManager session;

    // Extra data
    Bundle extra;
    String kodeKotak;
    int idJadwalPengecekan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set Context
        mContext = getApplicationContext();

        // Session class instance
        session = new SessionManager(mContext);

        // Declare object view
        txtBlokKotak = (TextView) findViewById(R.id.text_blok_kotak);
        rvObject = (RecyclerView) findViewById(R.id.object_recycler);

        // Set view Recycler
        rvObject.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvObject.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        // Get extra
        extra = getIntent().getExtras();

        if (extra != null) {
            kodeKotak = extra.getString(ConstantObject.KODE_KOTAK);
            idJadwalPengecekan = extra.getInt(ConstantObject.ID_JADWAL_PENGECEKAN);

            txtBlokKotak.setText(kodeKotak);
            objectAdapter = new ObjectAdapter(getApplicationContext(), this);
            loadListData(kodeKotak);
        }
    }

    private void loadListData(String kodeKotak) {
        try {
            RequestObject requestObject = new RequestObject(kodeKotak);
            ObjectInterface service = ObjectApiService.getListObject();
            Call<ResponseBody> call = service.getListObject(requestObject);
            Callback<ResponseBody> callback = new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String responseString = response.body().string();
                            JSONArray jsonRESULTS = new JSONArray(responseString);
                            if (jsonRESULTS != null && !jsonRESULTS.toString().equals("[]")) {
                                Type listType = new TypeToken<List<ObjectKotak>>() {
                                }.getType();
                                listObject = new Gson().fromJson(String.valueOf(jsonRESULTS), listType);

                                if (listObject.size() > 0) {
                                    objectAdapter.setData(listObject);
                                    objectAdapter.notifyDataSetChanged();

                                    rvObject.setAdapter(objectAdapter);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Data kosong", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Response kosong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException | IOException e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_LONG).show();
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    private String generateKodeHasilPengecekan() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());
        String formattedDate = df.format(currentTime);
        String kodeHasilPengecekan = kodeKotak + "-" + formattedDate;

        return kodeHasilPengecekan;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void OnListClick(int position) {
        ObjectKotak objectKotak = listObject.get(position);
        String kodeHasilPengecekan = generateKodeHasilPengecekan();
        Intent intent = new Intent(getApplicationContext(), UpdateCheckingActivity.class);
        intent.putExtra(ConstantObject.ID_JADWAL_PENGECEKAN, idJadwalPengecekan);
        intent.putExtra(ConstantObject.ID_OBJECT, objectKotak.getIdObject());
        intent.putExtra(ConstantObject.KODE_HASIL_PENGECEKAN, kodeHasilPengecekan);
        intent.putExtra(ConstantObject.OBJECT, objectKotak.getObject());
        startActivity(intent);
    }
}