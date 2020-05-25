package org.fneto.copadeequipes.controller;

import org.fneto.copadeequipes.domain.Equipe;
import org.fneto.copadeequipes.service.EquipeService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@WebFluxTest(EquipeController.class)
public class EquipeControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EquipeService equipeService;

    @Test
    void getEquipesTest() {
        Equipe equipe1 = new Equipe(new UUID(1, 2), "Equipe 1", "EQP1", 1);
        Equipe equipe2 = new Equipe(new UUID(2, 1), "Equipe 2", "EQP2", 3);
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe1);
        equipes.add(equipe2);
        BDDMockito.given(equipeService.getEquipes())
                .willReturn(equipes);

        webTestClient.get()
                .uri("/equipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(equipesList -> equipes.get(0), equalTo(equipe1));
    }

    @Test
    void postSelecaoTest() {
        Equipe equipe1 = new Equipe(new UUID(1, 2), "Equipe 1", "EQP1", 1);
        Equipe equipe2 = new Equipe(new UUID(2, 1), "Equipe 2", "EQP2", 3);
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe1);
        equipes.add(equipe2);
        List<UUID> parametros = Arrays.asList(new UUID[] { new UUID(1, 2), new UUID(2, 1) });
        when(equipeService.getResultado(parametros))
                .thenReturn(equipes);

        webTestClient.post()
                .uri("/equipes/selecao")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(parametros)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(equipesList -> equipes.get(0), equalTo(equipe1));
    }
}
