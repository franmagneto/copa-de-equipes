package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;

import java.util.List;
import java.util.UUID;

public interface EquipeService {

    List<Equipe> getEquipes();
    List<Equipe> getResultado(List<UUID> selecaoIds);
}
