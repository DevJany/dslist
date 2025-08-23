package com.devjany.dslist.services;


import com.devjany.dslist.dto.GameDTO;
import com.devjany.dslist.dto.GameListDTO;
import com.devjany.dslist.dto.GameMinDTO;
import com.devjany.dslist.entities.Game;
import com.devjany.dslist.projections.GameMinProjections;
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

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        var result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjections> list = gameRepository.searchByList(listId);

        GameMinProjections obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}
