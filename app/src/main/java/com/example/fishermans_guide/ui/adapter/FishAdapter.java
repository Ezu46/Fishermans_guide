package com.example.fishermans_guide.ui.adapter;

import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;
import com.bumptech.glide.Glide;
import com.example.fishermans_guide.R;
import com.example.fishermans_guide.model.Fish;

import java.util.function.Consumer;

public class FishAdapter extends ListAdapter<Fish, FishAdapter.FishHolder> {
    private final Consumer<Fish> clickListener;

    public FishAdapter(Consumer<Fish> clickListener) {
        super(new DiffUtil.ItemCallback<Fish>() {
            public boolean areItemsTheSame(Fish a, Fish b) { return a.id == b.id; }
            public boolean areContentsTheSame(Fish a, Fish b) { return a.equals(b); }
        });
        this.clickListener = clickListener;
    }

    @NonNull @Override
    public FishHolder onCreateViewHolder(@NonNull ViewGroup p, int i) {
        View v = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_fish, p, false);
        return new FishHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FishHolder h, int pos) {
        Fish fish = getItem(pos);
        h.name.setText(fish.name);
        Glide.with(h.image.getContext())
                .load(fish.imageResId)
                .into(h.image);
        h.itemView.setOnClickListener(v -> clickListener.accept(fish));
    }

    static class FishHolder extends RecyclerView.ViewHolder {
        TextView name; ImageView image;
        FishHolder(@NonNull View v) {
            super(v);
            name  = v.findViewById(R.id.fishName);
            image = v.findViewById(R.id.fishThumb);
        }
    }
}