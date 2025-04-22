package com.example.fishermans_guide.data;

import android.content.Context;
import androidx.room.*;

import com.example.fishermans_guide.data.FishDao;
import com.example.fishermans_guide.data.NoteDao;
import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.model.Note;

@Database(entities = {Fish.class, Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FishDao fishDao();
    public abstract NoteDao noteDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                                AppDatabase.class, "fishermans_db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}