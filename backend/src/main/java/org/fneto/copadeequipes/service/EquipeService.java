package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;
import reactor.core.publisher.Flux;

public interface EquipeService {

    Flux<Equipe> getEquipes();
}
