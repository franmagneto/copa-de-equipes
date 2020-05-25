package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.configuration.EquipeClient;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeClient equipeClient;
    private volatile Map<UUID, Equipe> equipes;
    private static final AtomicReference<ResponseStatusException> responseStatusException = new AtomicReference<>();

    @Override
    public List<Equipe> getEquipes() {
        fetchEquipes();

        return new ArrayList<>(equipes.values());
    }

    @Override
    public List<Equipe> getResultado(List<UUID> selecaoIds) {
        fetchEquipes();

        Deque<Equipe> rodada = montaSelecao(selecaoIds);
        while (rodada.size() > 2) {
            rodada = calculaRodada(rodada);
        }
        List<Equipe> resultado = new ArrayList<>(rodada);
        resultado.sort(Equipe::compareTo);
        return resultado;
    }

    private void fetchEquipes() throws ResponseStatusException {
        if (equipes != null) return;

        equipeClient.getClient()
                .get()
                .uri("/equipes.json")
                .retrieve()
                .bodyToFlux(Equipe.class)
                .collectMap(Equipe::getId, Function.identity(), LinkedHashMap::new)
                .subscribe(
                        equipesMap -> equipes = equipesMap,
                        e -> responseStatusException.set(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE))
                );

        while (equipes == null && responseStatusException.get() == null) Thread.onSpinWait();
        if (responseStatusException.get() != null) {
            throw responseStatusException.get();
        }
        responseStatusException.set(null);
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
        chave.sort(Equipe::compareTo);
        return chave.get(0);
    }
}
