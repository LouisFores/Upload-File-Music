package com.example.be1_lesson5_uploadfilemusic.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicForm {
    private int id;
    private String name;
    private String author;
    private String kind;
    private MultipartFile audioMusic;

    public MusicForm(int id, String name, String author, String kind, MultipartFile audioMusic) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.kind = kind;
        this.audioMusic = audioMusic;
    }

    public MusicForm(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public MultipartFile getAudioMusic() {
        return audioMusic;
    }

    public void setAudioMusic(MultipartFile audioMusic) {
        this.audioMusic = audioMusic;
    }
}
