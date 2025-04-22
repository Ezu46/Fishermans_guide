package com.example.fishermans_guide.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fishermans_guide.R;
import com.example.fishermans_guide.model.Fish;
import java.util.function.Consumer;

public class FishAdapter extends ListAdapter<Fish, FishAdapter.FishHolder> {

    private final Consumer<Fish> clickListener;
    private final Consumer<Fish> favClickListener;

    public FishAdapter(Consumer<Fish> clickListener, Consumer<Fish> favClickListener) {
        super(new DiffUtil.ItemCallback<Fish>() {
            @Override
            public boolean areItemsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.clickListener = clickListener;
        this.favClickListener = favClickListener;
    }

    @NonNull
    @Override
    public FishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fish, parent, false);
        return new FishHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FishHolder holder, int position) {
        Fish fish = getItem(position);
        holder.name.setText(fish.name);
        Glide.with(holder.image.getContext())
                .load(fish.imageResId)
                .fitCenter()
                .into(holder.image);

        holder.fav.setImageResource(
                fish.isFavorite ? R.drawable.ic_star_fiiled : R.drawable.ic_star);

        holder.itemView.setOnClickListener(v -> clickListener.accept(fish));
        holder.fav.setOnClickListener(v -> favClickListener.accept(fish));
    }

    static class FishHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        ImageButton fav;

        FishHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fishName);
            image = itemView.findViewById(R.id.fishThumb);
            fav = itemView.findViewById(R.id.favIcon);
        }
    }
}
