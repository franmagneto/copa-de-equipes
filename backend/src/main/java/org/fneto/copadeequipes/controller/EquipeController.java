package org.fneto.copadeequipes.controller;

import org.fneto.copadeequipes.service.EquipeService;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public List<Equipe> getEquipes() throws ExecutionException, InterruptedException {
        return equipeService.getEquipes();
    }
}
