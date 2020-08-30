package com.agunahwanabsin.sitl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.adapter.view.KotakAdapterViewHolder;
import com.agunahwanabsin.sitl.library.CommonLibrary;
import com.agunahwanabsin.sitl.model.Kotak;

import java.util.List;

public class KotakAdapter extends RecyclerView.Adapter<KotakAdapterViewHolder> {
    Context mContext;
    List<Kotak> mListKotak;
    private OnListListener mOnListListener;

    public KotakAdapter(Context mContext, OnListListener mOnListListener) {
        this.mContext = mContext;
        this.mOnListListener = mOnListListener;
    }

    public void setData(List<Kotak> mListKotak) {
        this.mListKotak = mListKotak;
    }

    @NonNull
    @Override
    public KotakAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_kotak, parent, false);
        KotakAdapterViewHolder viewHolder = new KotakAdapterViewHolder(view, mOnListListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KotakAdapterViewHolder holder, int position) {
        String blok = "Blok : " + mListKotak.get(position).getKodeBlok();
        String kotak = "Kotak : " + mListKotak.get(position).getKodeKotak();
        String tanggalPanen = "Tanggal Panen : " + CommonLibrary.formattingDate(mListKotak.get(position).getEstimasiTanggalPanen());
        String jumlah = String.valueOf(mListKotak.get(position).getJumlahObject());

        holder.txtBlok.setText(blok);
        holder.txtKotak.setText(kotak);
        holder.txtJumlahObject.setText(jumlah);
        holder.txtTanggalPanen.setText(tanggalPanen);
    }

    @Override
    public int getItemCount() {
        return (mListKotak != null ? mListKotak.size() : 0);
    }
}
