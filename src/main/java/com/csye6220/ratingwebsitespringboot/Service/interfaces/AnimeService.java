package com.csye6220.ratingwebsitespringboot.Service.interfaces;

import com.csye6220.ratingwebsitespringboot.Entity.Anime;

import java.util.List;

public interface AnimeService {
    List<Anime> getAllAnimes();

    Anime getAnimeById(int id);

    Anime getAnimeByName(String name);

    void saveAnime(Anime anime);

    void deleteAnime(int id);
}
