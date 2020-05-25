package org.fneto.copadeequipes.service;

import org.fneto.copadeequipes.domain.Equipe;
import org.fneto.copadeequipes.mapper.EquipeMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EquipeServiceTest {

    @Autowired
    private EquipeService equipeService;

    @MockBean
    private EquipeMapper equipeMapper;

    @Test
    void getEquipesTest() {
        Equipe equipe1 = new Equipe(new UUID(1, 2), "Equipe 1", "EQP1", 1);
        Equipe equipe2 = new Equipe(new UUID(2, 1), "Equipe 2", "EQP2", 3);
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe1);
        equipes.add(equipe2);
        BDDMockito.given(equipeMapper.getEquipes())
                .willReturn(equipes);

        List<Equipe> resposta = equipeService.getEquipes();
        assertEquals(equipes, resposta);
    }
}
