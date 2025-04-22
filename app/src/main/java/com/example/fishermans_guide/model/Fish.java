package com.example.fishermans_guide.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fish")
public class Fish {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public String habitat;
    public String season;
    public String bait;
    public int imageResId;
    public boolean isFavorite;
}