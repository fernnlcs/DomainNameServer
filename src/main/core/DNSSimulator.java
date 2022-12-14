package main.core;

import main.factories.utils.IPv4Factory;
import main.factories.utils.URIFactory;
import main.structures.HashTable;
import main.utils.HashTableViewer;
import main.utils.IPv4Address;
import main.utils.Logger;
import main.utils.URI;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class DNSSimulator {

    private final HashTable<URI, IPv4Address> table;
    private final Logger logger;
    private final Timer timer = new Timer();

    public DNSSimulator(Logger logger) {
        this.logger = logger;
        this.table = new HashTable<>();
    }

    private void preset() {
        // Programamos 25 sites a cada 10 segundos
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                addSites(25);
                logger.report();
            }
        }, 0, 10000);

        this.logger.report();
    }

    private void end() {
        this.timer.cancel();
    }

    private static String present() {

        return """
                \t\tSERVIDOR DNS
                \t\t------------
                NÚMERO | Cadastrar N sites aleatórios
                C | Cadastrar/atualizar site no servidor
                V | Visualizar tabela hash dos sites cadastrados
                P | Pesquisar IP do site pela sua URI
                R | Remover site do servidor
                Q | Sair
                """;
    }

    private void addSites(int quantity) {
        for (int i = 0; i < quantity; i++) {
            URI uri = URIFactory.next();
            IPv4Address ip = IPv4Factory.next();
            this.table.add(uri, ip);
        }

        this.logger.log(quantity + " sites cadastrados.");
    }

    private void addSite(Scanner scanner) {
        System.out.println("| Adicionar site...");

        try {
            System.out.print("URI: ");
            URI uri = new URI(scanner.nextLine());

            System.out.print("IP: ");
            IPv4Address ip = new IPv4Address(scanner.nextLine());
            this.table.add(uri, ip);
            this.logger.log("Site cadastrado: " + uri + " (" + ip + ")");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private String view() {
        HashTableViewer<URI, IPv4Address> viewer = new HashTableViewer<>(this.table);
        return viewer.view();
    }

    private IPv4Address findSite(Scanner scanner) throws NoSuchElementException, IllegalArgumentException {
        System.out.println("| Buscar IP do site...");

        System.out.print("URI: ");
        URI uri = new URI(scanner.nextLine());

        return this.table.find(uri);
    }

    private void removeSite(Scanner scanner) throws NoSuchElementException {
        System.out.println("| Remover site...");

        System.out.print("URI: ");
        URI uri = new URI(scanner.nextLine());

        this.table.remove(uri);
        this.logger.log("Site removido: " + uri);
    }

    private void interact(Scanner scanner) {
        System.out.println(present() + "\n");
        String input;

        this.logger.report();

        do {
            System.out.print("\n>> COMANDO: ");
            input = scanner.nextLine();
            input = input.toUpperCase();

            switch (input) {
                case "C":
                    this.logger.waitFor(() -> this.addSite(scanner));
                    break;

                case "V":
                    this.logger.waitFor(() -> System.out.println(this.view()));
                    break;

                case "P":
                    this.logger.waitFor(() -> {
                        System.out.print("IP: ");
                        try {
                            IPv4Address result = this.findSite(scanner);
                            System.out.println(result);
                        } catch (NoSuchElementException | IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    });

                    break;

                case "R":
                    this.logger.waitFor(() -> {
                        try {
                            this.removeSite(scanner);
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    break;

                case "Q":
                    break;

                default:
                    try {
                        int steps = Integer.parseInt(input);
                        this.logger.waitFor(() -> this.addSites(steps));
                    } catch (NumberFormatException e) {
                        System.out.println("Opção desconhecida. Tente novamente.");
                    }
                    break;
            }

            this.logger.report();
        } while (!input.equals("Q"));
    }

    public void run(Scanner scanner) {
        this.preset();
        this.interact(scanner);
        this.end();
    }
}
