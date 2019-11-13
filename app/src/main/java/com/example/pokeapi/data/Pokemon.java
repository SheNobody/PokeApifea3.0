package com.example.pokeapi.data;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokeapi.R;

import java.util.List;

public class Pokemon extends AppCompatActivity {

    private int id;
    private int height;
    private int baseExpirience;
    private int weight;
    private List<String> types;
    String url;
    String name;


    public int getIntId(String url) {
        try {
            return Integer.parseInt(getStringId(url));
        } catch (Exception e) {
            return 0;
        }
    }

    public String getStringId(String url) {
        String substring = url.replace("https://pokeapi.co/api/v2/pokemon", "");
        String[] array = substring.split("/");
        return array[1];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
    }
}
