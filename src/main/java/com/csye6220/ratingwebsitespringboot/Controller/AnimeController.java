package com.csye6220.ratingwebsitespringboot.Controller;

import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.AnimeService;
import com.csye6220.ratingwebsitespringboot.Util.ImageUtil;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @PostMapping("/add")
    public Map<String, String> animeSave(@RequestBody Anime addAnime) {
        Anime anime = null;
        Map<String, String> response = new HashMap<>();
        System.out.println(addAnime.getImg_content());
        try {
            anime = animeService.getAnimeByName(addAnime.getAnime_name());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        if (anime != null) {
            System.out.println("Anime already exist");
            response.put("result", "already exist");
        } else {
            String img_file_name = ImageUtil.getImageName();
            String path = "src/main/resources/images/" + img_file_name + "." + addAnime.getImg_type();
            ImageUtil.GenerateImage(addAnime.getImg_content(), path);
            addAnime.setImg_path_name(path);
            addAnime.setImg_content("");
            animeService.saveAnime(addAnime);
            response.put("result", "success");
        }
        return response;
    }

    @GetMapping("/all")
    public Map<Integer, List<Anime>> getAllAnimes() throws Exception {
        List<Anime> list = animeService.getAllAnimes();
        Map<Integer, List<Anime>> map = new HashMap<>();
        map.put(1, new ArrayList<Anime>());
        map.put(2, new ArrayList<Anime>());
        map.put(3, new ArrayList<Anime>());
        map.put(4, new ArrayList<Anime>());
        map.put(5, new ArrayList<Anime>());
        map.put(6, new ArrayList<Anime>());
        map.put(7, new ArrayList<Anime>());

        for(Anime anime : list) {
            String img_path = anime.getImg_path_name();
            String img_type = anime.getImg_type();
            int update_day = anime.getUpdate_day();
            String base64Content = ImageUtil.getImageAsBase64(img_path, img_type);
            anime.setImg_content(base64Content);
            map.get(update_day).add(anime);
        }

        return map;
    }


}
