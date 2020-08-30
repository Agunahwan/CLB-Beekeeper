package com.agunahwanabsin.sitl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.adapter.view.JadwalAdapterViewHolder;
import com.agunahwanabsin.sitl.model.Jadwal;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapterViewHolder> {

    Context mContext;
    List<Jadwal> mListJadwal;
    private OnListListener mOnListListener;

    public JadwalAdapter(Context mContext, OnListListener mOnListListener) {
        this.mContext = mContext;
        this.mOnListListener = mOnListListener;
    }

    public void setData(List<Jadwal> mListJadwal) {
        this.mListJadwal = mListJadwal;
    }

    @NonNull
    @Override
    public JadwalAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_jadwal, viewGroup, false);
        JadwalAdapterViewHolder viewHolder = new JadwalAdapterViewHolder(view, mOnListListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapterViewHolder jadwalAdapterViewHolder, int i) {
        String owner = mListJadwal.get(i).getOwner();
        String blokKotak = mListJadwal.get(i).getKodeBlok() + " (" + mListJadwal.get(i).getKodeKotak() + ")";
        String keterangan = "Selesai " + String.valueOf(mListJadwal.get(i).getJumlahPengecekan()) + " pengecekan object dari total " + String.valueOf(mListJadwal.get(i).getJumlahData()) + " object";

        jadwalAdapterViewHolder.txtOwner.setText(owner);
        jadwalAdapterViewHolder.txtBlokKotak.setText(blokKotak);
        jadwalAdapterViewHolder.txtKeterangan.setText(keterangan);
    }

    @Override
    public int getItemCount() {
        return (mListJadwal != null ? mListJadwal.size() : 0);
    }
}
