package com.example.fishermans_guide.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.fishermans_guide.model.Fish;

import java.util.List;

@Dao
public interface FishDao {
    @Query("SELECT * FROM fish ORDER BY name")
    LiveData<List<Fish>> getAllFish();

    @Query("SELECT * FROM fish WHERE name LIKE :q OR habitat LIKE :q")
    LiveData<List<Fish>> searchFish(String q);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Fish> fishes);

    @Update
    void updateFish(Fish fish);
}