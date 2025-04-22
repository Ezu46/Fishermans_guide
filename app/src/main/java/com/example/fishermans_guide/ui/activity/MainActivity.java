package com.example.fishermans_guide.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishermans_guide.R;
import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.ui.adapter.FishAdapter;
import com.example.fishermans_guide.ui.viewmodel.FishViewModel;

public class MainActivity extends AppCompatActivity {
    private FishViewModel vm;
    private FishAdapter adapter;
    private SearchView searchView;
    private Spinner spinnerHabitat, spinnerSeason;
    private boolean showingFavorites = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar + SearchView
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        searchView = findViewById(R.id.searchView);

        // Спиннеры
        spinnerHabitat = findViewById(R.id.spinnerHabitat);
        spinnerSeason = findViewById(R.id.spinnerSeason);
        ArrayAdapter<CharSequence> habAdapter = ArrayAdapter.createFromResource(
                this, R.array.habitats, android.R.layout.simple_spinner_item);
        habAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHabitat.setAdapter(habAdapter);
        spinnerSeason.setAdapter(ArrayAdapter.createFromResource(
                this, R.array.seasons, android.R.layout.simple_spinner_item));

        // RecyclerView + Adapter
        RecyclerView rv = findViewById(R.id.fishRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FishAdapter(
                fish -> onFishClick(fish),
                fish -> onFavoriteToggle(fish)
        );
        rv.setAdapter(adapter);

        // ViewModel
        vm = new ViewModelProvider(this).get(FishViewModel.class);

        // Лисенеры фильтрации
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String q) {
                updateList();
                return true;
            }
        });
        AdapterView.OnItemSelectedListener sel = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> p, View v, int i, long l) {
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> p) {}
        };
        spinnerHabitat.setOnItemSelectedListener(sel);
        spinnerSeason.setOnItemSelectedListener(sel);

        // Первичная загрузка
        updateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_show_favorites) {
            showingFavorites = !showingFavorites;
            item.setIcon(showingFavorites
                    ? R.drawable.ic_star_fiiled
                    : R.drawable.ic_star);
            updateList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onFishClick(Fish fish) {
        startActivity(new Intent(this, DetailActivity.class)
                .putExtra("fishId", fish.id));
    }

    private void onFavoriteToggle(Fish fish) {
        fish.isFavorite = !fish.isFavorite;
        vm.update(fish);
    }

    private void updateList() {
        String q = searchView.getQuery().toString().trim();
        String h = spinnerHabitat.getSelectedItem().toString();
        String s = spinnerSeason.getSelectedItem().toString();

        if (showingFavorites) {
            vm.getFavorites().observe(this, fishes -> adapter.submitList(fishes));
        } else if (q.isEmpty() && h.equals("Все") && s.equals("Все")) {
            vm.getAllFish().observe(this, fishes -> adapter.submitList(fishes));
        } else {
            vm.filter(q, h, s).observe(this, fishes -> adapter.submitList(fishes));
        }
    }
}
