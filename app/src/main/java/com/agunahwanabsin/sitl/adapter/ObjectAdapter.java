package com.agunahwanabsin.sitl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.adapter.instance.OnListListener;
import com.agunahwanabsin.sitl.adapter.view.ObjectAdapterViewHolder;
import com.agunahwanabsin.sitl.model.ObjectKotak;

import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapterViewHolder> {
    Context mContext;
    List<ObjectKotak> mListObject;
    private OnListListener mOnListListener;

    public ObjectAdapter(Context mContext, OnListListener mOnListListener) {
        this.mContext = mContext;
        this.mOnListListener = mOnListListener;
    }

    public void setData(List<ObjectKotak> mListObject) {
        this.mListObject = mListObject;
    }

    @NonNull
    @Override
    public ObjectAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_object, viewGroup, false);
        ObjectAdapterViewHolder viewHolder = new ObjectAdapterViewHolder(view, mOnListListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectAdapterViewHolder objectAdapterViewHolder, int i) {
        String objectKotak = mListObject.get(i).getObject();

        objectAdapterViewHolder.txtObject.setText(objectKotak);
    }

    @Override
    public int getItemCount() {
        return (mListObject != null ? mListObject.size() : 0);
    }
}
