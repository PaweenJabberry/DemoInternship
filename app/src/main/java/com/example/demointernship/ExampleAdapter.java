package com.example.demointernship;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    private ExampleViewHolder.OnExampleListener mOnExampleListener;

    public  static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public CardView mCardView;

        OnExampleListener onExampleListener;

        public ExampleViewHolder(@NonNull View itemView, OnExampleListener onExampleListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.text);
            mTextView2 = itemView.findViewById(R.id.text2);
            mCardView = itemView.findViewById(R.id.mCardView);
            this.onExampleListener = onExampleListener;
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onExampleListener.onExampleClick(getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 121,0,"Delete");
        }

        public interface OnExampleListener {
            void onExampleClick(int position);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList, ExampleViewHolder.OnExampleListener onExampleListener) {
        this.mExampleList = exampleList;
        this.mOnExampleListener= onExampleListener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnExampleListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currentItem = mExampleList.get(i);
        try {
            JSONObject obj = currentItem.getObj();
            exampleViewHolder.mTextView1.setText(obj.getString("title"));
            exampleViewHolder.mTextView2.setText(obj.getString("message"));
            Picasso.get().load(obj.getString("url")).into(exampleViewHolder.mImageView);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(ExampleItem exampleItem) {
        mExampleList.add(0, exampleItem);
        notifyDataSetChanged();
    }
}
