package com.csye6220.ratingwebsitespringboot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operations", uniqueConstraints = {@UniqueConstraint(columnNames = {"anime_id", "user_id"})})
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "anime_id")
    private Integer anime_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "credit")
    private Double credit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(Integer anime_id) {
        this.anime_id = anime_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Operation() {
        this.anime_id = 0;
        this.user_id = 0;
        this.credit = (double) 0;
    }

    @Override
    public String toString() {
        return "Operation: [id=" + id + ", anime_id=" + anime_id + ", user_id=" + user_id + ", credit=" + credit + "]";
    }
}
