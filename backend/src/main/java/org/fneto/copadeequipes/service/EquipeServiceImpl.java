package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.configuration.EquipeClient;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    EquipeClient equipeClient;

    private List<Equipe> equipes = null;

    @Override
    public List<Equipe> getEquipes() throws ExecutionException, InterruptedException {
        if (equipes == null) {
            fetchEquipes();
        }
        return equipes;
    }

    private void fetchEquipes() throws ExecutionException, InterruptedException {
        WebClient.ResponseSpec response = equipeClient.getClient()
                .get()
                .uri("/equipes.json")
                .retrieve();

        Future<List<Equipe>> equipesFuture = response
                .bodyToFlux(Equipe.class)
                .collectList()
                .toFuture();

        equipes = equipesFuture.get();
    }
}
