package com.example.be1_lesson5_uploadfilemusic.service;

import com.example.be1_lesson5_uploadfilemusic.model.Music;

import java.nio.channels.MulticastChannel;
import java.util.List;

public interface IMusicService {
    List<Music> findAll();
    void save(Music music);
    Music findById(int id);
    void remove(int id);
}
