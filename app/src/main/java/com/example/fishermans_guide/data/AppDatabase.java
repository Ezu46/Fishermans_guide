package com.example.fishermans_guide.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.model.Note;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Fish.class, Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FishDao fishDao();
    public abstract NoteDao noteDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "fishermans_db"
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        List<Fish> fishes = DataInitializer.getPrepopulateFishList();
                                        INSTANCE.fishDao().insertAll(fishes);
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}