package com.agunahwanabsin.sitl.adapter.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;

public class JadwalAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtOwner;
    public TextView txtBlokKotak;
    public TextView txtKeterangan;
    OnListListener onListListener;

    public JadwalAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtOwner = (TextView) itemView.findViewById(R.id.text_owner);
        txtBlokKotak = (TextView) itemView.findViewById(R.id.text_blok_kotak);
        txtKeterangan = (TextView) itemView.findViewById(R.id.text_description);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
