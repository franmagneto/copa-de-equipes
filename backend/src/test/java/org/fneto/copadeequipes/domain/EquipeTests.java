package org.fneto.copadeequipes.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EquipeTests {

    @Test
    void serializacaoTest() throws JsonProcessingException {
        Equipe equipe = new Equipe(new UUID(1, 2), "Equipe 1", "EQP1", 1);
        ObjectMapper om = new ObjectMapper();
        String equipeJson = om.writeValueAsString(equipe);
        // TODO: Usar jsonpath
        assertEquals(equipeJson, "{\"id\":\"00000000-0000-0001-0000-000000000002\",\"nome\":\"Equipe 1\",\"sigla\":\"EQP1\",\"gols\":1}");
    }
}
