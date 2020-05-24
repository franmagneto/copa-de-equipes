package org.fneto.copadeequipes.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Equipe {

    private UUID id;
    private String nome;
    private String sigla;
    private Integer gols;

    public static int comparador(Equipe equipe1, Equipe equipe2) {
        int gols = equipe2.getGols() - equipe1.getGols();
        if (gols != 0) return gols;

        return equipe1.getNome().compareTo(equipe2.getNome());
    }
}
