package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EquipeService {

    List<Equipe> getEquipes() throws ExecutionException, InterruptedException;
}
