package com.example.fishermans_guide.ui.adapter;

import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;
import com.example.fishermans_guide.R;
import com.example.fishermans_guide.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    public NoteAdapter() {
        super(new DiffUtil.ItemCallback<Note>() {
            public boolean areItemsTheSame(Note a, Note b) { return a.id == b.id; }
            public boolean areContentsTheSame(Note a, Note b) { return a.equals(b); }
        });
    }

    @NonNull @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup p, int i) {
        View v = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_note, p, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder h, int pos) {
        Note n = getItem(pos);
        h.text.setText(n.text);
        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                .format(new Date(n.timestamp));
        h.timestamp.setText(date);
    }

    static class NoteHolder extends RecyclerView.ViewHolder {
        TextView text, timestamp;
        NoteHolder(@NonNull View v) {
            super(v);
            text      = v.findViewById(R.id.noteText);
            timestamp = v.findViewById(R.id.noteTime);
        }
    }
}