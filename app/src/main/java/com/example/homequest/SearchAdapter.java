package com.example.homequest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HomeViewHolder> {
    private Context context;
    private List<HomeS> homeList;

    public SearchAdapter(Context context, List<HomeS> homeList) {
        this.context = context;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyler, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeS home = homeList.get(position);
        holder.nameTextView.setText(home.getName());
        holder.contactTextView.setText(home.getContact());
        holder.imageView.setImageResource(home.getImageResId());

        // Handle click on item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowActivity.class);
            intent.putExtra("name", home.getName());
            intent.putExtra("contact", home.getContact());
            intent.putExtra("imageResId", home.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, contactTextView;
        ImageView imageView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.home_name);
            contactTextView = itemView.findViewById(R.id.home_contact);
            imageView = itemView.findViewById(R.id.home_image);
        }
    }
}

