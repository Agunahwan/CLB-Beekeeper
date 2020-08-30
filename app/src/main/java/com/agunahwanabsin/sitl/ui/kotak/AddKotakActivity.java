package com.agunahwanabsin.sitl.ui.kotak;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.api.instance.BlokInterface;
import com.agunahwanabsin.sitl.api.instance.KotakInterface;
import com.agunahwanabsin.sitl.api.instance.OwnerInterface;
import com.agunahwanabsin.sitl.api.services.BlokApiService;
import com.agunahwanabsin.sitl.api.services.KotakApiService;
import com.agunahwanabsin.sitl.api.services.OwnerApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.model.Blok;
import com.agunahwanabsin.sitl.model.Owner;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddKotakActivity extends AppCompatActivity {

    // Global variable
    Blok selectedBlok;
    Owner selectedOwner;

    // View Object
    Spinner blok, owner;
    EditText kotak, keterangan;
    TextView tanggalPanen;
    Button tanggalPanenPicker, simpan;

    // Session Manager Class
    SessionManager session;

    // List Data
    List<Blok> listBlok;
    List<Owner> listOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kotak);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        // Initialize Object
        blok = (Spinner) findViewById(R.id.blok);
        kotak = (EditText) findViewById(R.id.kotak);
        owner = (Spinner) findViewById(R.id.owner);
        tanggalPanen = (TextView) findViewById(R.id.tanggal_panen);
        tanggalPanenPicker = (Button) findViewById(R.id.tanggal_panen_picker);
        keterangan = (EditText) findViewById(R.id.keterangan);
        simpan = (Button) findViewById(R.id.simpan);

        // Set Spinner Data
        getListBlok();
        getListOwner();

        // Set Listener
        tanggalPanenPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKotak();
            }
        });
        blok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBlok = (Blok) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOwner = (Owner) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showDateDialog() {
        int mYear, mMonth, mDay;

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String monthString = String.valueOf(monthOfYear + 1);
                        if (monthString.length() == 1) {
                            monthString = "0" + monthString;
                        }
                        tanggalPanen.setText(dayOfMonth + "/" + monthString + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void saveKotak() {
        KotakInterface service = KotakApiService.save();
        Call<ResponseBody> call = service.save(
                kotak.getText().toString(),
                selectedBlok.getKodeBlok(),
                selectedOwner.getIdOwner(),
                tanggalPanen.getText().toString(),
                keterangan.getText().toString(),
                session.getUserDetails().getBeekeper()
        );
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String isSuccess = response.body().string();
                        if (isSuccess.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            // Jika login gagal
                            Toast.makeText(getApplicationContext(), isSuccess, Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
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
    }

    private void getListBlok() {
        BlokInterface service = BlokApiService.getListBlok();
        Call<List<Blok>> call = service.getListBlok();
        Callback<List<Blok>> callback = new Callback<List<Blok>>() {
            @Override
            public void onResponse(Call<List<Blok>> call, Response<List<Blok>> response) {
                if (response.isSuccessful()) {
                    listBlok = response.body();
                    ArrayAdapter<Blok> blokAdapter = new ArrayAdapter<Blok>(getApplicationContext(), android.R.layout.simple_spinner_item, listBlok);

                    // Drop down layout style - list view
                    blokAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Set Adapter to spiner
                    blok.setAdapter(blokAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Blok>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                System.out.println("onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    private void getListOwner() {
        OwnerInterface service = OwnerApiService.getListOwner();
        Call<List<Owner>> call = service.getListOwner();
        Callback<List<Owner>> callback = new Callback<List<Owner>>() {
            @Override
            public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                if (response.isSuccessful()) {
                    listOwner = response.body();

                    ArrayAdapter<Owner> ownerAdapter = new ArrayAdapter<Owner>(getApplicationContext(), android.R.layout.simple_spinner_item, listOwner);

                    // Drop down layout style - list view
                    ownerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Set Adapter to spiner
                    owner.setAdapter(ownerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Owner>> call, Throwable t) {
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