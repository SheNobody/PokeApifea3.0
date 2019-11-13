package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokeapi.adapter.PokemonAdapter;
import com.example.pokeapi.adapter.PokemonItemListener;
import com.example.pokeapi.data.PokemonShort;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.pokeapi.utils.RequestUtils.createUrl;
import static com.example.pokeapi.utils.RequestUtils.makeHttpRequest;

public class MainActivity extends AppCompatActivity implements PokemonItemListener {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        adapter = new PokemonAdapter(new ArrayList<PokemonShort>(), this);
        recyclerView.setAdapter(adapter);
    }

    private void updatePokemonList(List<PokemonShort> pokemonShortList) {
        adapter.setItems(pokemonShortList);
    }

    @Override
    public void onPokemonClicked(int position) {
        Toast.makeText(this, "Positionl: " + position, Toast.LENGTH_SHORT).show();
    }

    private List<PokemonShort> parsePokemonList(String jsonStr) {
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            ArrayList<PokemonShort> pokemonShortList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                String url = jsonArray.getJSONObject(i).getString("url");
                String name = jsonArray.getJSONObject(i).getString("name");
                pokemonShortList.add(new PokemonShort(url, name));
            }
            return pokemonShortList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class PokeAsyncTask extends AsyncTask<Void, Void, List<PokemonShort>> {


        @Override
        protected List<PokemonShort> doInBackground(Void... voids) {
            URL url = createUrl("https://pokeapi.co/api/v2/pokemon?offset=0&limit=150");

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<PokemonShort> pokemonShortList) {
            adapter.setItems(pokemonShortList);
        }
    }
}
