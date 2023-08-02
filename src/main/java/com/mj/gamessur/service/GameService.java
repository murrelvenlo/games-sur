package com.mj.gamessur.service;

import com.mj.gamessur.dto.GameDto;
import com.mj.gamessur.dto.GameUpdateDto;
import com.mj.gamessur.model.Game;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameService
{
    Game createGame(Game game);

    List<Game> findAllGame();
    GameDto getGameById(Long id);
    public GameDto updateGame(Long id, GameUpdateDto gameUpdateDto);
    ResponseEntity<Integer> removeGame(Long gameId);
    void deleteGame(Long id);

}
