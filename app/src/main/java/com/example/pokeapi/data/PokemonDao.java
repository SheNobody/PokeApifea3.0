package com.example.pokeapi.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDao {
    @Query("SELECT * FROM PokemonShort")
    List<PokemonShort> getAll();
    @Query("SELECT * FROM PokemonShort WHERE id = :first")
    PokemonShort findByName(String first);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertAll(PokemonShort... users);
    @Delete
    void delete(PokemonShort user);
}