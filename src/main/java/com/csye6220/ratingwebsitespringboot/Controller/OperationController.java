package com.csye6220.ratingwebsitespringboot.Controller;

import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Entity.Operation;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.AnimeService;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.OperationService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private AnimeService animeService;

    @PostMapping("/rate")
    public Map<String, String> operationSave(@RequestBody Operation addOperation) {
        Operation operation = null;
        Map<String, String> response = new HashMap<>();

        try {
            operation = operationService.getOperationByUser(addOperation.getUser_id(), addOperation.getAnime_id());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        if (operation != null) {
            System.out.println("Operation already exist");
            response.put("result", "already exist");
        } else {
            operationService.saveOperation(addOperation);
            Anime anime = animeService.getAnimeById(addOperation.getAnime_id());
            anime.setTotal_credit(anime.getTotal_credit() + addOperation.getCredit());
            anime.setTotal_times(anime.getTotal_times() + 1);
            animeService.saveAnime(anime);
            response.put("result", "success");
        }
        return response;
    }

    @PostMapping("/find")
    public Map<String, String> getAlreadyCredit(@RequestBody Operation addOperation) {
        Operation operation = null;
        Map<String, String> response = new HashMap<>();

        try {
            operation = operationService.getOperationByUser(addOperation.getUser_id(), addOperation.getAnime_id());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        if(operation != null) {
            response.put("exist", "true");
            response.put("rate", String.valueOf(operation.getCredit()));
            response.put("operation_id", String.valueOf(operation.getId()));
        } else {
            response.put("exist", "false");
        }
        return response;
    }

    @PostMapping("/update")
    public Map<String, String> updateRating(@RequestBody Operation addOperation) {
        Operation operation = null;

        Map<String, String> response = new HashMap<>();

        try {
            operation = operationService.getOperationById(addOperation.getId());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        double diff = addOperation.getCredit() - operation.getCredit();
        Anime anime = animeService.getAnimeById(addOperation.getAnime_id());
        anime.setTotal_credit(anime.getTotal_credit() + diff);
        animeService.saveAnime(anime);
        operationService.saveOperation(addOperation);
        response.put("result", "success");

        return response;
    }
}
