package org.fneto.copadeequipes.domain;

import lombok.Getter;
import lombok.Setter;
import org.fneto.copadeequipes.util.ComparadorAlfanumerico;

import java.util.UUID;

@Getter @Setter
public class Equipe implements Comparable<Equipe> {

    private UUID id;
    private String nome;
    private String sigla;
    private Integer gols;

    private static ComparadorAlfanumerico comparador = new ComparadorAlfanumerico();

    @Override
    public int compareTo(Equipe equipe) {
        int gols = equipe.getGols() - this.getGols();
        if (gols != 0) return gols;

        return comparador.compare(this.getNome(), equipe.getNome());
    }
}
