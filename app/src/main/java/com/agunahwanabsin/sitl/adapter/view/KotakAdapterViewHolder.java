package com.agunahwanabsin.sitl.adapter.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;

public class KotakAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtBlok;
    public TextView txtKotak;
    public TextView txtJumlahObject;
    public TextView txtTanggalPanen;
    public ImageView imgMore;
    OnListListener onListListener;

    public KotakAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtBlok = (TextView) itemView.findViewById(R.id.blok);
        txtKotak = (TextView) itemView.findViewById(R.id.kotak);
        txtJumlahObject = (TextView) itemView.findViewById(R.id.jumlah_object);
        txtTanggalPanen = (TextView) itemView.findViewById(R.id.tanggal_panen);
        imgMore = (ImageView) itemView.findViewById(R.id.imageMore);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
