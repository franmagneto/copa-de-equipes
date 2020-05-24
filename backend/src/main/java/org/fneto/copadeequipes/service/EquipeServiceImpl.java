package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.configuration.EquipeClient;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    EquipeClient equipeClient;

    private Map<UUID, Equipe> equipes;

    @Override
    public List<Equipe> getEquipes() throws ExecutionException, InterruptedException {
        if (equipes == null) {
            fetchEquipes();
        }
        return new ArrayList<>(equipes.values());
    }

    @Override
    public List<Equipe> getResultado(List<UUID> selecaoIds) {
        Deque<Equipe> rodada = montaSelecao(selecaoIds);
        while (rodada.size() > 2) {
            rodada = calculaRodada(rodada);
        }
        return new ArrayList<>(rodada);
    }

    private void fetchEquipes() throws ExecutionException, InterruptedException {
        WebClient.ResponseSpec response = equipeClient.getClient()
                .get()
                .uri("/equipes.json")
                .retrieve();

        Future<Map<UUID, Equipe>> equipesFuture = response
                .bodyToFlux(Equipe.class)
                .collectMap(Equipe::getId, Function.identity(), LinkedHashMap::new)
                .toFuture();

        equipes = equipesFuture.get();
    }

    private Deque<Equipe> montaSelecao(List<UUID> ids) {
        Deque<Equipe> selecao = new ArrayDeque<>();
        for (UUID id: ids) {
            selecao.add(equipes.get(id));
        }
        return selecao;
    }

    private Deque<Equipe> calculaRodada(Deque<Equipe> selecao) {
        Deque<Equipe> proximaRodada = new ArrayDeque<>();
        while (!selecao.isEmpty()) {
            List<Equipe> chave = Arrays.asList(selecao.removeFirst(), selecao.removeLast());
            proximaRodada.add(vencedorChave(chave));
        }
        return proximaRodada;
    }

    private Equipe vencedorChave(List<Equipe> chave) {
        chave.sort(Equipe::comparador);
        return chave.get(0);
    }
}
