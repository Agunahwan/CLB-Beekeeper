package com.agunahwanabsin.sitl.ui.kotak;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.KotakAdapter;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.api.instance.BlokInterface;
import com.agunahwanabsin.sitl.api.instance.KotakInterface;
import com.agunahwanabsin.sitl.api.instance.OwnerInterface;
import com.agunahwanabsin.sitl.api.services.BlokApiService;
import com.agunahwanabsin.sitl.api.services.KotakApiService;
import com.agunahwanabsin.sitl.api.services.OwnerApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.CommonLibrary;
import com.agunahwanabsin.sitl.library.ConstantObject;
import com.agunahwanabsin.sitl.model.Blok;
import com.agunahwanabsin.sitl.model.Kotak;
import com.agunahwanabsin.sitl.model.Owner;
import com.agunahwanabsin.sitl.ui.absensi.AbsensiActivity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class KotakFragment extends Fragment implements OnListListener {

    // Global variable
    Context mContext;

    // View Object
    View view;
    Button btnAddKotak;
    TextView txtTanggal;
    RecyclerView rvKotak;

    SessionManager session;
    KotakAdapter kotakAdapter;
    List<Kotak> listKotak;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kotak, container, false);

        // Set Context
        mContext = this.getActivity().getApplicationContext();

        // Initialize Object
        btnAddKotak = (Button) view.findViewById(R.id.add_kotak);
        txtTanggal = (TextView) view.findViewById(R.id.text_jadwal);
        rvKotak = (RecyclerView) view.findViewById(R.id.kotak_recycler);

        mContext = this.getActivity().getApplicationContext();
        rvKotak.setLayoutManager(new LinearLayoutManager(mContext));
        rvKotak.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        kotakAdapter = new KotakAdapter(mContext, this);

        // Set Listener
        btnAddKotak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToAddKotak();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Session Manager
        session = new SessionManager(mContext);

        // Set Tanggal
        String tanggal = CommonLibrary.getToday();
        txtTanggal.setText(tanggal);

        loadListKotak();
    }

    private void loadListKotak() {
        KotakInterface service = KotakApiService.getListKotak();
        Call<List<Kotak>> call = service.getListKotak();
        Callback<List<Kotak>> callback = new Callback<List<Kotak>>() {
            @Override
            public void onResponse(Call<List<Kotak>> call, Response<List<Kotak>> response) {
                if (response.isSuccessful()) {
                    listKotak = response.body();
                    if (listKotak.size() > 0) {
                        kotakAdapter.setData(listKotak);
                        kotakAdapter.notifyDataSetChanged();

                        rvKotak.setAdapter(kotakAdapter);
                    } else {
                        Toast.makeText(mContext, "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Kotak>> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    private void moveToAddKotak() {
        Intent intent = new Intent(mContext, AddKotakActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnListClick(int position) {
        Kotak kotak = listKotak.get(position);
        if (kotak.getJumlahObject() > 0) {
            Intent intent = new Intent(mContext, TanggalPengecekanActivity.class);
            intent.putExtra(ConstantObject.KODE_KOTAK, kotak.getKodeKotak());
            startActivity(intent);
        }
    }
}