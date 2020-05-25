package org.fneto.copadeequipes.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ComparadorAlfanumericoTest {

    @Test
    void ordenaCorretamente() {
        List<String> lista = Arrays.asList(
                "Bla 4",
                "Bla bla",
                "Bla 3",
                "Bla bla1",
                "Bla 12"
        );

        lista.sort(new ComparadorAlfanumerico());

        assertNotNull(lista);
        assertEquals(lista.get(0), "Bla 3");
        assertEquals(lista.get(1), "Bla 4");
        assertEquals(lista.get(2), "Bla 12");
        assertEquals(lista.get(3), "Bla bla");
        assertEquals(lista.get(4), "Bla bla1");

    }
}
