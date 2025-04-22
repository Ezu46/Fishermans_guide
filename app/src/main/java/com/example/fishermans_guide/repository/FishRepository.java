package com.example.fishermans_guide.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.fishermans_guide.data.AppDatabase;
import com.example.fishermans_guide.data.FishDao;
import com.example.fishermans_guide.model.Fish;

import java.util.List;
import java.util.concurrent.Executors;

public class FishRepository {
    private final FishDao fishDao;
    public FishRepository(Context ctx) {
        fishDao = AppDatabase.getInstance(ctx).fishDao();
    }
    public LiveData<List<Fish>> getAllFish() {
        return fishDao.getAllFish();
    }
    public LiveData<List<Fish>> searchFish(String q) {
        return fishDao.searchFish("%"+q+"%");
    }
    public void update(Fish fish) {
        Executors.newSingleThreadExecutor()
                .execute(() -> fishDao.updateFish(fish));
    }
}