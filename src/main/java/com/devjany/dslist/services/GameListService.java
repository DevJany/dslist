package com.devjany.dslist.services;


import com.devjany.dslist.dto.GameDTO;
import com.devjany.dslist.dto.GameListDTO;
import com.devjany.dslist.dto.GameMinDTO;
import com.devjany.dslist.entities.Game;
import com.devjany.dslist.repositories.GameListRepository;
import com.devjany.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        var result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}
