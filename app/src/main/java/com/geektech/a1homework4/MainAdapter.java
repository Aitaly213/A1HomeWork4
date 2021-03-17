package com.geektech.a1homework4;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    List<ContactModel> arrayList;
    public Context context;
    private ItemClickListener listener;

    public MainAdapter(List<ContactModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item,parent,false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(arrayList.get(position));
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.imageView2);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.delete){
                            arrayList.remove(position);
                            notifyItemChanged(position);
                            notifyItemRemoved(position);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }





    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AppCompatTextView textView1;
        AppCompatTextView textView2;
        ImageView imageView1;
        ImageView imageView2;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(this);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.phoneNumber);
            imageView1 = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.menu1);

        }

        public void onBind(ContactModel data){
            textView1.setText(data.getName());
            textView2.setText(data.getPhone());

            if (data.getImage() != null){
                Glide.with(context).load(data.getImage())
                        .apply(RequestOptions
                        .circleCropTransform())
                        .into(imageView1);
            }else {
                Glide.with(context).load(R.drawable.user2)
                        .apply(RequestOptions
                        .circleCropTransform())
                        .into(imageView1);
            }

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onItemClick(getAdapterPosition());
            }
        }

    }
    public void setOnClickListener(ItemClickListener mListener){
        this.listener = mListener;
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

}
