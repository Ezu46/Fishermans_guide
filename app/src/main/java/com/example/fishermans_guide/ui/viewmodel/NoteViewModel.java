package com.example.fishermans_guide.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.fishermans_guide.model.Note;
import com.example.fishermans_guide.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository repo;

    public NoteViewModel(@NonNull Application app) {
        super(app);
        repo = new NoteRepository(app);
    }
    public LiveData<List<Note>> getNotes(int fishId) {
        return repo.getNotes(fishId);
    }
    public void insert(Note note) { repo.insert(note); }
    public void delete(Note note) { repo.delete(note); }
}