package org.fneto.copadeequipes.controller;

import org.fneto.copadeequipes.service.EquipeService;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public Flux<Equipe> listaEquipes() {
        return equipeService.getEquipes();
    }
}
