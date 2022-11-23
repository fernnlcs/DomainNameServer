package test.utils;

import main.utils.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoggerTest {
    @Test
    void isEmpty() {
        Logger logger = new Logger();
        Assertions.assertThat(logger.isEmpty()).isEqualTo(true);
    }

    @Test
    void isNotEmpty() {
        Logger logger = new Logger();
        logger.log("Exemplo de mensagem.");
        Assertions.assertThat(logger.isEmpty()).isEqualTo(false);
    }

    @Test
    void getSize0() {
        Logger logger = new Logger();
        Assertions.assertThat(logger.size()).isEqualTo(0);
    }

    @Test
    void getSize1() {
        Logger logger = new Logger();
        logger.log("Exemplo de mensagem.");
        Assertions.assertThat(logger.size()).isEqualTo(1);
    }

    @Test
    void getSize5() {
        Logger logger = new Logger();
        logger.log("Exemplo de mensagem #1.");
        logger.log("Exemplo de mensagem #2.");
        logger.log("Exemplo de mensagem #3.");
        logger.log("Exemplo de mensagem #4.");
        logger.log("Exemplo de mensagem #5.");
        Assertions.assertThat(logger.size()).isEqualTo(5);
    }

    @Test
    void printReport() {
        // Capturar a saída do console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Logger logger = new Logger();
        logger.report();

        // Gerar relatório vazio (apenas o rótulo de tempo, sem mensagens)
        Assertions.assertThat(outContent.toString()).isEqualTo("");

        // Fechar a captura
        System.setOut(originalOut);
    }

    @Test
    void filledReport() {
        Logger logger = new Logger();
        logger.log("Exemplo de mensagem.");

        // Gerar relatório vazio (apenas o rótulo de tempo, sem mensagens)
        Assertions.assertThat(logger.getReport()).isEqualTo("> Exemplo de mensagem.\n");
    }
}
