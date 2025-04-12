package commons.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FiltroDeCaracteresEspeciaisTest {

    @Parameterized.Parameter
    public String entrada;

    @Parameterized.Parameter(value = 1)
    public String esperado;

    @Parameterized.Parameter(value = 2)
    public String cenario;

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{

                {"dog's", "dog", "Palavra com apóstrofo no final"},
                {"hello's", "hello", "Palavra com sequência 's no final"},
                {"dog", "dog", "Palavra válida sem caracteres especiais"},
                {"dog!123", "dog", "Palavra com caracteres não alfabéticos"},
                {"@#$%", null, "Palavra composta apenas por símbolos especiais"},
                {"cats'", "cats", "Palavra com apóstrofo no final"},
                {"cats!´", "cats", "Palavra com apóstrofo e caracteres não válidos"},
                {"hello world", "helloworld", "Palavra com espaço (não válido)"},
                {"", null, "Entrada vazia"},

                {"ação", "ação", "Palavra com acento agudo (português)"},
                {"café", "café", "Palavra com acento agudo (português)"},
                {"jabuticaba", "jabuticaba", "Palavra sem caracteres especiais (português)"},
                {"pão", "pão", "Palavra com til (português)"},
                {"tensão", "tensão", "Palavra com acento circunflexo (português)"},

                {"über", "über", "Palavra com u-umlaut (alemão)"},
                {"straße", "straße", "Palavra com ß (alemão)"},
                {"größer", "größer", "Palavra com o-umlaut (alemão)"},
                {"äpfel", "äpfel", "Palavra com ä (alemão)"},
                {"könig", "könig", "Palavra com o-umlaut (alemão)"}
        });
    }

    @Test
    public void deveFiltrarPalavraCorretamente() {
        // Cenário: Descrição do caso de teste (passado como parâmetro)
        // Ação: Chama o método filter
        String resultado = FiltroDeCaracteresEspeciais.filter(entrada);

        // Validação: O resultado deve ser o esperado
        assertEquals(esperado, resultado);
    }
}
