package org.fneto.copadeequipes.mapper;

import org.fneto.copadeequipes.configuration.EquipeClient;
import org.fneto.copadeequipes.domain.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Component
public class EquipeMapper {

    @Autowired
    private EquipeClient equipeClient;
    private volatile Map<UUID, Equipe> equipes;
    private static final AtomicReference<ResponseStatusException> responseStatusException = new AtomicReference<>();

    public List<Equipe> getEquipes() {
        fetchEquipes();

        return new ArrayList<>(equipes.values());
    }

    public Deque<Equipe> montaSelecao(List<UUID> ids) {
        fetchEquipes();

        Deque<Equipe> selecao = new ArrayDeque<>();
        for (UUID id: ids) {
            selecao.add(equipes.get(id));
        }
        return selecao;
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
}
