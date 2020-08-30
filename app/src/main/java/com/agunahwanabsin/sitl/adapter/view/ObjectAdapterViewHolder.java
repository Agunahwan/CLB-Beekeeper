package com.agunahwanabsin.sitl.adapter.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;

public class ObjectAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtObject;
    OnListListener onListListener;

    public ObjectAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtObject = (TextView) itemView.findViewById(R.id.text_object);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
