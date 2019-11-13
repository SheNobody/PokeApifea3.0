package com.example.pokeapi.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeapi.R;
import com.example.pokeapi.data.PokemonShort;

public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView pokemonImage;
    private TextView pokemonName;
    private PokemonItemListener listener;

    public ViewHolder(@NonNull View itemView, PokemonItemListener listener) {
        super(itemView);
        pokemonImage = itemView.findViewById(R.id.pokemonImage);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        this.listener = listener;
    }

    public void decorateWith(PokemonShort model) {
        pokemonName.setText(model.getName());
        Glide.with(itemView.getContext())
                .load(model.getUrl())
                .centerInside()
                .into(pokemonImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPokemonClicked(getAdapterPosition());
            }
        });
    }
}