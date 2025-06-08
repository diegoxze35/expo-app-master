package com.expo.expoapp.controller;

import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.TeamRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository repository;

    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Team> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Team save(@RequestBody Team team) {
        return repository.save(team);
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Team updateById(@PathVariable Long id, @RequestBody Team team) {
        team.setTeamId(id);
        return repository.save(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
