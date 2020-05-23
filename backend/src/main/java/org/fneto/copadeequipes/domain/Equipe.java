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
}
