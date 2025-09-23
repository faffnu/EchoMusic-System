package com.fang.service;

import com.fang.entity.dto.SongMusicDTO;

import java.util.List;

public interface NewMusicTypeService {
    public List<SongMusicDTO> getMusicByTag(String tag);
}
