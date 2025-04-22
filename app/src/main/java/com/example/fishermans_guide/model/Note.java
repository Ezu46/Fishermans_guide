package com.example.fishermans_guide.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

import com.example.fishermans_guide.model.Fish;

@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = Fish.class,
                parentColumns = "id",
                childColumns = "fishId",
                onDelete = CASCADE),
        indices = {@Index("fishId")})
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int fishId;
    public String text;
    public long timestamp;
}