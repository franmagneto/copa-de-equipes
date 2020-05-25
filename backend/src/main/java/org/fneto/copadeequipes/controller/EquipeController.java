package org.fneto.copadeequipes.controller;

import org.fneto.copadeequipes.domain.Equipe;
import org.fneto.copadeequipes.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public List<Equipe> getEquipes() {
        return equipeService.getEquipes();
    }

    @PostMapping
    @RequestMapping("/selecao")
    public List<Equipe> postSelecao(@RequestBody List<UUID> selecaoIds) {
        return equipeService.getResultado(selecaoIds);
    }
}
