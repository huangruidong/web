package com.xinzhen.xznongshang.module.main.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.main.dataMode.InviteGridItem;
import com.xinzhen.xznongshang.utils.Utils;

import java.util.List;

/**
 * Created by sense on 2018/3/19.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHoder> {
    public List<InviteGridItem> mData;

    public GridAdapter(List<InviteGridItem> data) {
        this.mData = data;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoder(View.inflate(Utils.getCtx(), R.layout.grid_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.text.setText(mData.get(position).getText());
        Drawable drawable = mData.get(position).getDrawable();
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        holder.text.setCompoundDrawables(null, drawable, null, null);
        if (mListener != null) {
            mListener.onItemClick(holder.itemView, position);
        }
    }

    private OnItemClickListener mListener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHoder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.grid_item_text);
        }
    }
}
