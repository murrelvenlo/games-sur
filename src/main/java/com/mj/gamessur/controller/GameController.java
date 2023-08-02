package com.mj.gamessur.controller;

import com.mj.gamessur.dto.GameDto;
import com.mj.gamessur.dto.GameUpdateDto;
import com.mj.gamessur.model.Game;
import com.mj.gamessur.service.GameServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Game", description = "Game management APIs")
@RestController
@RequestMapping("/api/game")
public class GameController
{
    @Autowired
    private GameServiceImpl gameService;

    @GetMapping("/get")
    public List<Game> getGames()
    {
        return gameService.findAllGame();
    }

    @Operation(
            summary = "Retrieve a Game by Id",
            description = "Get a Game object by specifying its id. The response is Game object with id, title, description and published status.",
            tags = { "games", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Game.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/get/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long gameId)
    {
        GameDto gameDto = this.gameService.getGameById(gameId);
        return new ResponseEntity<GameDto>(gameDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Game> createUser(@RequestBody Game game)
    {
        Game gameCreated = this.gameService.createGame(game);
        return new ResponseEntity<Game>(gameCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GameDto> updateGame(@PathVariable("id") Long gameId, @RequestBody GameUpdateDto gameUpdateDto)
    {
        GameDto updatedGameDto = gameService.updateGame(gameId, gameUpdateDto);
        if (updatedGameDto != null) {
            return ResponseEntity.ok(updatedGameDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable("id") Long gameId)
    {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok("Het spelletje is succesvol verwijderd!");
    }
}
