package com.example.fishermans_guide.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fishermans_guide.R;
import com.example.fishermans_guide.ui.activity.DetailActivity;
import com.example.fishermans_guide.ui.adapter.FishAdapter;
import com.example.fishermans_guide.ui.viewmodel.FishViewModel;

public class MainActivity extends AppCompatActivity {

    private FishViewModel fishViewModel;
    private FishAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AppBar + Search
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.searchView);

        // RecyclerView + Adapter
        recyclerView = findViewById(R.id.fishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FishAdapter(fish -> {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra("fishId", fish.id);
            startActivity(i);
        });
        recyclerView.setAdapter(adapter);

        // ViewModel
        fishViewModel = new ViewModelProvider(this).get(FishViewModel.class);
        fishViewModel.getAllFish().observe(this, adapter::submitList);

        // Поиск
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String newText) {
                String q = newText.trim();
                if (q.isEmpty()) {
                    fishViewModel.getAllFish().observe(MainActivity.this, adapter::submitList);
                } else {
                    fishViewModel.search(q).observe(MainActivity.this, adapter::submitList);
                }
                return true;
            }
        });
    }
}