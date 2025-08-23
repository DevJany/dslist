package com.devjany.dslist.controllers;

import com.devjany.dslist.dto.GameDTO;
import com.devjany.dslist.dto.GameListDTO;
import com.devjany.dslist.dto.GameMinDTO;
import com.devjany.dslist.dto.ReplacementDTO;
import com.devjany.dslist.entities.Game;
import com.devjany.dslist.services.GameListService;
import com.devjany.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        List<GameListDTO> result = gameListService.findAll();
        return result;
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        List<GameMinDTO> result = gameService.findByList(listId);
        return result;
    }

    @PostMapping("/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
