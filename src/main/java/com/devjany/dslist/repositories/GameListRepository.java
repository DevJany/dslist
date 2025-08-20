package com.devjany.dslist.repositories;

import com.devjany.dslist.entities.Game;
import com.devjany.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
