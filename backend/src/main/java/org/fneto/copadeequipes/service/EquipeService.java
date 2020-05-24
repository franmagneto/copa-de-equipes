package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface EquipeService {

    List<Equipe> getEquipes() throws ExecutionException, InterruptedException;
    List<Equipe> getResultado(List<UUID> selecaoIds);
}
