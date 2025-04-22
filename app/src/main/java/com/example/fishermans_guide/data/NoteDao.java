package com.example.fishermans_guide.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.fishermans_guide.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes WHERE fishId = :fishId ORDER BY timestamp DESC")
    LiveData<List<Note>> getNotesForFish(int fishId);

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);
}