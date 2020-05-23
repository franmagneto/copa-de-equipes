package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.configuration.EquipeClient;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    EquipeClient equipeClient;

    @Override
    public Flux<Equipe> getEquipes() {
        return equipeClient.getClient()
                .get()
                .uri("/equipes.json")
                .retrieve()
                .bodyToFlux(Equipe.class);
    }
}
