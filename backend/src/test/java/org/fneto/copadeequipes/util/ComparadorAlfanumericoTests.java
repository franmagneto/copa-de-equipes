package org.fneto.copadeequipes.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ComparadorAlfanumericoTests {

    @Test
    void ordenaCorretamenteTest() {
        List<String> lista = Arrays.asList(
                "Bla 4",
                "Bla bla",
                "Bla 3",
                "Bla bla1",
                "Ble",
                "Bla 12"
        );

        lista.sort(new ComparadorAlfanumerico());

        assertNotNull(lista);
        assertEquals("Bla 3", lista.get(0));
        assertEquals("Bla 4", lista.get(1));
        assertEquals("Bla 12", lista.get(2));
        assertEquals("Bla bla", lista.get(3));
        assertEquals("Bla bla1", lista.get(4));
        assertEquals("Ble", lista.get(5));

    }
}
