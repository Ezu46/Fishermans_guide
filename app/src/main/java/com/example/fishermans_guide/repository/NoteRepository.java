package com.example.fishermans_guide.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.fishermans_guide.data.AppDatabase;
import com.example.fishermans_guide.data.NoteDao;
import com.example.fishermans_guide.model.Note;

import java.util.List;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao noteDao;
    public NoteRepository(Context ctx) {
        noteDao = AppDatabase.getInstance(ctx).noteDao();
    }
    public LiveData<List<Note>> getNotes(int fishId) {
        return noteDao.getNotesForFish(fishId);
    }
    public void insert(Note note) {
        Executors.newSingleThreadExecutor()
                .execute(() -> noteDao.insert(note));
    }
    public void delete(Note note) {
        Executors.newSingleThreadExecutor()
                .execute(() -> noteDao.delete(note));
    }
}