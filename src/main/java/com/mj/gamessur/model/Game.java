package com.mj.gamessur.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String explanation;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Team> teams;

    public Game()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getExplanation()
    {
        return explanation;
    }

    public void setExplanation(String explanation)
    {
        this.explanation = explanation;
    }

    public List<Team> getTeams()
    {
        return teams;
    }

    public void setTeams(List<Team> teams)
    {
        this.teams = teams;
    }
}
