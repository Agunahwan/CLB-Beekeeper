package com.agunahwanabsin.sitl.adapter.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;

public class TanggalPengecekanAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtTanggalPengecekan;
    public TextView txtBeekeeper;
    public ImageView imgMore;
    OnListListener onListListener;

    public TanggalPengecekanAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtTanggalPengecekan = (TextView) itemView.findViewById(R.id.tanggal_pengecekan);
        txtBeekeeper = (TextView) itemView.findViewById(R.id.beekeeper);
        imgMore = (ImageView) itemView.findViewById(R.id.imageMore);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
