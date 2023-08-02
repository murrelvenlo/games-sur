package com.mj.gamessur.service;

import com.mj.gamessur.dto.GameDto;
import com.mj.gamessur.dto.GameUpdateDto;
import com.mj.gamessur.model.Game;
import com.mj.gamessur.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService
{
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper)
    {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Game createGame(Game game)
    {
        return gameRepository.save(game);
    }

    @Override
    public List<Game> findAllGame()
    {
        return gameRepository.findAllByOrderByName();
    }

    @Override
    public GameDto getGameById(Long gameId)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        Game game = gameOptional
                .orElseThrow(() ->
                        new NoSuchElementException("Het spelletje met de Id: " + gameId));
        return modelMapper.map(game, GameDto.class);
    }

    @Override
    public GameDto updateGame(Long gameId, GameUpdateDto gameUpdateDto)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent())
        {
            Game existingGame = gameRepository.findById(gameId).get();
            existingGame.setName(gameUpdateDto.getName());
            existingGame.setDescription(gameUpdateDto.getDescription());
            existingGame.setExplanation(gameUpdateDto.getExplanation());

            Game updatedGame = gameRepository.save(existingGame);

            return modelMapper.map(updatedGame, GameDto.class);
        }
        return null;

//        // Update the list of teams, I will need it later
////        List<Team> teams = new ArrayList<>();
////        if (gameDto.getTeams() != null) {
////            for (TeamDto teamDto : gameDto.getTeams()) {
////                Team team;
////                if (teamDto.getId() != null) {
////                    Optional<Team> teamOptional = teamRepository.findById(teamDto.getId());
////                    team = teamOptional.orElseThrow(() ->
////                            new NoSuchElementException("Team not found with ID: " + teamDto.getId()));
////                } else {
////                    team = new Team();
////                }
////                // Map properties from TeamDto to Team entity
////                team.setName(teamDto.getName());
////                team.setDescription(teamDto.getDescription());
////                // Map other properties as needed
////
////                teams.add(team);
////            }
////        }
////        game.setTeams(teams);
//
//
//        // Save the updated Game entity back to the database
//        Game updatedGame = gameRepository.save(game);
//
//        return modelMapper.map(updatedGame, GameUpdateDto.class);
    }

    @Override
    public ResponseEntity<Integer> removeGame(Long gameId)
    {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent())
        {
            Game game = optionalGame.get();
            gameRepository.deleteById(gameId);

            return new ResponseEntity<>(gameRepository.findAll().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(gameRepository.findAll().size(), HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteGame(Long id)
    {
        Game game = gameRepository.findById(id)
                .orElseThrow(()-> new ResourceAccessException("Het spelletje met de id: " + id + " is niet gevonden!"));

        gameRepository.deleteById(id);
    }
}
