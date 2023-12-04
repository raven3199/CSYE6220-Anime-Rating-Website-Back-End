package com.csye6220.ratingwebsitespringboot.Service.implementations;

import com.csye6220.ratingwebsitespringboot.DAO.interfaces.AnimeDAO;
import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeDAO animeDAO;

    @Transactional
    @Override
    public List<Anime> getAllAnimes() {
        return animeDAO.getAllAnimes();
    }

    @Transactional
    @Override
    public Anime getAnimeById(int id) {
        return animeDAO.getAnimeById(id);
    }

    @Transactional
    @Override
    public Anime getAnimeByName(String name) {
        return animeDAO.getAnimeByName(name);
    }

    @Transactional
    @Override
    public void saveAnime(Anime anime) {
        animeDAO.saveAnime(anime);
    }

    @Transactional
    @Override
    public void deleteAnime(int id) {
        animeDAO.deleteAnime(id);
    }
}
