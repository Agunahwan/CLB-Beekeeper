package com.agunahwanabsin.sitl.ui.jadwal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.JadwalAdapter;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.api.instance.JadwalInterface;
import com.agunahwanabsin.sitl.api.model.RequestJadwal;
import com.agunahwanabsin.sitl.api.services.JadwalApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.library.CommonLibrary;
import com.agunahwanabsin.sitl.library.ConstantObject;
import com.agunahwanabsin.sitl.model.Beekeper;
import com.agunahwanabsin.sitl.model.Jadwal;
import com.agunahwanabsin.sitl.ui.jadwal.pemeriksaan.CheckingActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalFragment extends Fragment implements OnListListener {

    Context mContext;
    View view;
    TextView txtTanggal;
    RecyclerView rvJadwal;
    ProgressDialog loading;
    SessionManager session;
    JadwalAdapter jadwalAdapter;
    List<Jadwal> listJadwal;

    public static JadwalFragment newInstance() {
        return new JadwalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jadwal_fragment, container, false);

        txtTanggal = (TextView) view.findViewById(R.id.text_jadwal);
        rvJadwal = (RecyclerView) view.findViewById(R.id.jadwal_recycler);

        mContext = this.getActivity().getApplicationContext();
        rvJadwal.setLayoutManager(new LinearLayoutManager(mContext));
        rvJadwal.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        jadwalAdapter = new JadwalAdapter(mContext, this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Session Manager
        session = new SessionManager(mContext);

        Beekeper beekeper = session.getUserDetails();

        // Set Tanggal
        String tanggal = CommonLibrary.getToday();
        txtTanggal.setText(tanggal);

        // Set loading
        loading = new ProgressDialog(mContext);
        loading.setMessage(getString(R.string.msg_loading));
//        loading.show();

        loadListData(view, beekeper.getIdBeekeper());
    }

    private void loadListData(final View fragmentView, int idBeekeper) {
        try {
            RequestJadwal requestJadwal = new RequestJadwal(idBeekeper);
            JadwalInterface service = JadwalApiService.getListJadwal();
            Call<ResponseBody> call = service.getListJadwal(requestJadwal);
            Callback<ResponseBody> callback = new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        loading.dismiss();
                        try {
                            String responseString = response.body().string();
                            JSONArray jsonRESULTS = new JSONArray(responseString);
                            if (jsonRESULTS != null && !jsonRESULTS.toString().equals("[]")) {
                                Type listType = new TypeToken<List<Jadwal>>() {
                                }.getType();
                                listJadwal = new Gson().fromJson(String.valueOf(jsonRESULTS), listType);

                                if (listJadwal.size() > 0) {
                                    jadwalAdapter.setData(listJadwal);
                                    jadwalAdapter.notifyDataSetChanged();

                                    rvJadwal.setAdapter(jadwalAdapter);
                                } else {
                                    Toast.makeText(mContext, "Data kosong", Toast.LENGTH_SHORT).show();
                                }
                            } else {
//                                String error_message = jsonRESULTS.getString(getString(R.string.prompt_failed_response));
                                Toast.makeText(mContext, "Response kosong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException | IOException e) {
                            Toast.makeText(mContext, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    } else {
                        loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                    loading.dismiss();
                }
            };
            call.enqueue(callback);
        } catch (Exception ex) {
            Toast.makeText(mContext, "Error: " + ex.toString(), Toast.LENGTH_LONG).show();
            Log.e("debug", "onFailure: ERROR > " + ex.toString());
        }
    }

    @Override
    public void OnListClick(int position) {
        Jadwal jadwal = listJadwal.get(position);
        Intent intent = new Intent(mContext, CheckingActivity.class);
        intent.putExtra(ConstantObject.ID_JADWAL_PENGECEKAN, jadwal.getIdJadwalPengecekan());
        intent.putExtra(ConstantObject.ID_HASIL_PENGECEKAN, jadwal.getIdHasilPengecekan());
        intent.putExtra(ConstantObject.KODE_KOTAK, jadwal.getKodeKotak());
        startActivity(intent);
    }
}