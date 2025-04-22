package com.example.fishermans_guide.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fishermans_guide.R;
import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.model.Note;
import com.example.fishermans_guide.ui.adapter.NoteAdapter;
import com.example.fishermans_guide.ui.viewmodel.FishViewModel;
import com.example.fishermans_guide.ui.viewmodel.NoteViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DetailActivity extends AppCompatActivity {
    private FishViewModel fishVM;
    private NoteViewModel noteVM;
    private Fish currentFish;
    // view refs...

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_detail);
        int fishId = getIntent().getIntExtra("fishId", -1);

        fishVM = new ViewModelProvider(this).get(FishViewModel.class);
        noteVM = new ViewModelProvider(this).get(NoteViewModel.class);

        // findViewById для всех view...

        fishVM.getAllFish().observe(this, list -> {
            for (Fish f : list) if (f.id==fishId) {
                currentFish = f;
                bindFish(f);
                break;
            }
        });

        NoteAdapter noteAdapter = new NoteAdapter();
        RecyclerView nr = findViewById(R.id.notesRecycler);
        nr.setLayoutManager(new LinearLayoutManager(this));
        nr.setAdapter(noteAdapter);

        noteVM.getNotes(fishId).observe(this, noteAdapter::submitList);

        findViewById(R.id.addNoteFab).setOnClickListener(v -> {
            showAddNoteDialog(fishId);
        });

        findViewById(R.id.favButton).setOnClickListener(v -> {
            currentFish.isFavorite = !currentFish.isFavorite;
            fishVM.update(currentFish);
            // менять иконку
        });
    }

    private void bindFish(Fish f) {
        Glide.with(this).load(f.imageResId).into((ImageView)findViewById(R.id.detailImage));
        ((TextView)findViewById(R.id.detailName)).setText(f.name);
        ((TextView)findViewById(R.id.detailDesc)).setText(f.description);
        ((TextView)findViewById(R.id.detailHabitat)).setText(f.habitat);
        ((TextView)findViewById(R.id.detailSeason)).setText(f.season);
        ((TextView)findViewById(R.id.detailBait)).setText(f.bait);
        // favButton.setImage...
    }

    private void showAddNoteDialog(int fishId) {
        final EditText input = new EditText(this);
        new MaterialAlertDialogBuilder(this)
                .setTitle("Добавить заметку")
                .setView(input)
                .setPositiveButton("Сохранить", (d,i) -> {
                    String txt = input.getText().toString().trim();
                    if (!txt.isEmpty()) {
                        Note note = new Note();
                        note.fishId = fishId;
                        note.text = txt;
                        note.timestamp = System.currentTimeMillis();
                        noteVM.insert(note);
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }
}