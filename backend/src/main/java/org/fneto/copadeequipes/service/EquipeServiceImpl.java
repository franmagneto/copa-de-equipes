package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;
import org.fneto.copadeequipes.mapper.EquipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeMapper equipeMapper;

    @Override
    public List<Equipe> getEquipes() {
        return equipeMapper.getEquipes();
    }

    @Override
    public List<Equipe> getResultado(List<UUID> selecaoIds) {
        Deque<Equipe> rodada = equipeMapper.montaSelecao(selecaoIds);
        while (rodada.size() > 2) {
            rodada = calculaRodada(rodada);
        }
        List<Equipe> resultado = new ArrayList<>(rodada);
        resultado.sort(Equipe::compareTo);
        return resultado;
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
