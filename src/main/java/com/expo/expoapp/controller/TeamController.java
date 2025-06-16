package com.expo.expoapp.controller;

import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.TeamRepository;
import com.expo.expoapp.service.TeamService;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    /*private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping
    public List<Team> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Team save(@RequestBody Team team, @AuthenticationPrincipal String email) {
        return service.save(team);
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Team updateById(@PathVariable Long id, @RequestBody Team team) {
        team.setTeamId(id);
        return service.save(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }*/
}
