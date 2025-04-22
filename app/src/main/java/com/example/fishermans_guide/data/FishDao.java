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

    @Query("""
  SELECT * FROM fish
   WHERE
     (:name IS NULL OR name LIKE :name) AND
     (:habitat IS NULL OR habitat = :habitat) AND
     (:season IS NULL OR season = :season)
   ORDER BY name                """)
    LiveData<List<Fish>> filterFish(String name, String habitat, String season);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Fish> fishes);

    @Update
    void updateFish(Fish fish);


    @Query("SELECT * FROM fish WHERE isFavorite = 1 ORDER BY name")
    LiveData<List<Fish>> getFavoriteFish();
}