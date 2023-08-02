package com.mj.gamessur.dto;

import com.mj.gamessur.model.Team;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class GameCreationDTO
{
    private Long id;
    private String name;
    private String description;
    private String explanation;

    @OneToMany
    private List<Team> teams = new ArrayList<>();


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
