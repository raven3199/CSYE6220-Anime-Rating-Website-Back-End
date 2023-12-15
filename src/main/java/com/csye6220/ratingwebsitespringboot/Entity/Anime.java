package com.csye6220.ratingwebsitespringboot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "animes", uniqueConstraints = {@UniqueConstraint(columnNames = "anime_name")})
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "anime_name")
    private String anime_name;

    @Column(name = "img_path_name")
    private String img_path_name;

    @Column(name = "total_credit")
    private Double total_credit;

    @Column(name = "total_times")
    private Integer total_times;

    @Column(name = "update_day")
    private Integer update_day;

    @Column(name = "img_content")
    private String img_content;

    @Column(name = "img_type")
    private String img_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnime_name() {
        return anime_name;
    }

    public void setAnime_name(String anime_name) {
        this.anime_name = anime_name;
    }

    public String getImg_path_name() {
        return img_path_name;
    }

    public void setImg_path_name(String img_path_name) {
        this.img_path_name = img_path_name;
    }

    public Double getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(Double total_credit) {
        this.total_credit = total_credit;
    }

    public Integer getTotal_times() {
        return total_times;
    }

    public void setTotal_times(Integer total_times) {
        this.total_times = total_times;
    }

    public Anime() {
        this.anime_name = "";
        this.img_path_name = "";
        this.total_credit = (double) 0;
        this.total_times = 0;
        this.update_day = 0;
        this.img_type = "";
        this.img_content = "";
    }

    public Integer getUpdate_day() {
        return update_day;
    }

    public void setUpdate_day(Integer update_day) {
        this.update_day = update_day;
    }

    public String getImg_content() {
        return img_content;
    }

    public void setImg_content(String img_content) {
        this.img_content = img_content;
    }

    public String getImg_type() {
        return img_type;
    }

    public void setImg_type(String img_type) {
        this.img_type = img_type;
    }

    @Override
    public String toString() {
        return "Anime: [id=" + id + ", anime_name=" + anime_name + ", img_path_name=" + img_path_name
                + ", total_credit=" + total_credit + ", total_times=" + total_times + ", update_day="
                + update_day + ", img_type=" + img_type + "]";
    }
}
