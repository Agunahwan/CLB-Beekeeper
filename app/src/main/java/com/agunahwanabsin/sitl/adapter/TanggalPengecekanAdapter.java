package com.agunahwanabsin.sitl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.adapter.view.TanggalPengecekanAdapterViewHolder;
import com.agunahwanabsin.sitl.library.CommonLibrary;
import com.agunahwanabsin.sitl.model.TanggalPengecekan;

import java.util.List;

public class TanggalPengecekanAdapter extends RecyclerView.Adapter<TanggalPengecekanAdapterViewHolder> {
    Context mContext;
    List<TanggalPengecekan> mListTanggalPengecekan;
    private OnListListener mOnListListener;

    public TanggalPengecekanAdapter(Context mContext, OnListListener mOnListListener) {
        this.mContext = mContext;
        this.mOnListListener = mOnListListener;
    }

    public void setData(List<TanggalPengecekan> mListTanggalPengecekan) {
        this.mListTanggalPengecekan = mListTanggalPengecekan;
    }

    @NonNull
    @Override
    public TanggalPengecekanAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tanggal_pengecekan, parent, false);
        TanggalPengecekanAdapterViewHolder viewHolder = new TanggalPengecekanAdapterViewHolder(view, mOnListListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TanggalPengecekanAdapterViewHolder holder, int position) {
        String tanggal = CommonLibrary.formattingDate(mListTanggalPengecekan.get(position).getTanggalPengecekan());
        String beekeeper = "Beekeeper : " + mListTanggalPengecekan.get(position).getBeekeper();

        holder.txtTanggalPengecekan.setText(tanggal);
        holder.txtBeekeeper.setText(beekeeper);
    }

    @Override
    public int getItemCount() {
        return (mListTanggalPengecekan != null ? mListTanggalPengecekan.size() : 0);
    }
}
