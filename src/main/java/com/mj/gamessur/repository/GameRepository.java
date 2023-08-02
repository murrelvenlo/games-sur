package com.mj.gamessur.repository;

import com.mj.gamessur.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>
{
    List<Game> findAllByOrderByName();
}
