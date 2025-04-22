package com.example.fishermans_guide.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.repository.FishRepository;

import java.util.List;

public class FishViewModel extends AndroidViewModel {
    private final FishRepository repo;
    private final LiveData<List<Fish>> allFish;

    public FishViewModel(@NonNull Application app) {
        super(app);
        repo = new FishRepository(app);
        allFish = repo.getAllFish();
    }
    public LiveData<List<Fish>> getAllFish() {
        return repo.getAllFish();
    }
    public LiveData<List<Fish>> search(String q) { return repo.searchFish(q); }
    public void update(Fish fish) { repo.update(fish); }
    public LiveData<List<Fish>> filter(String name, String habitat, String season) {
        return repo.filterFish(name, habitat, season);
    }
    public LiveData<List<Fish>> getFavorites() {
        return repo.getFavoriteFish();
    }
}