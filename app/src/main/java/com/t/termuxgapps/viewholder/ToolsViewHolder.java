package com.t.termuxgapps.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t.termuxgapps.R;

public class ToolsViewHolder extends RecyclerView.ViewHolder {

    View mview;
    ImageView imageView;
    TextView post_desc;
    TextView post_title;

    public ToolsViewHolder(View itemView) {
        super(itemView);
        mview = itemView;

        //listener set on ENTIRE ROW, you may set on individual components within a row.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

    }

    public void setTitle(String title) {
        post_title = (TextView) mview.findViewById(R.id.post_title);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        post_desc = (TextView) mview.findViewById(R.id.post_desc);
        post_desc.setText(desc);
    }

    public void setImage(Context ctx, String image) {
        imageView = (ImageView) mview.findViewById(R.id.post_image);
        Picasso.get().load(image).into(imageView);
    }


    private ToolsViewHolder.ClickListener mClickListener;

    //Interface to send callbacks...
    public interface ClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ToolsViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}

