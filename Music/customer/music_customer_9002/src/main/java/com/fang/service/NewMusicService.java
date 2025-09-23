package com.fang.service;

import com.fang.entity.dto.SongMusicDTO;

import java.util.List;

public interface NewMusicService {
    public List<SongMusicDTO> newMusic();

    public List<SongMusicDTO> newMusicByType(String type);

    public List<SongMusicDTO> getType();
}
