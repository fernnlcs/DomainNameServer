package test.utils;

import main.utils.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

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
        Assertions.assertThat(outContent.toString()).isEqualTo("\n");

        // Fechar a captura
        System.setOut(originalOut);
    }

    @Test
    void waitFor() {
        // Capturar a saída do console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Logger logger = new Logger();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.log("Exemplo de mensagem.");
                logger.report();
            }
        }, 100, 100);

        logger.waitFor(() -> System.out.print("#"));
        String firstOutput = (String) logger.waitFor(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            timer.cancel();
            return outContent.toString();
        });
        Assertions.assertThat(firstOutput).isEqualTo("#");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertThat(outContent.toString()).contains("> Exemplo de mensagem.");

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
