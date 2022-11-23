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

public class DNSSimulator {

    private final HashTable<URI, IPv4Address> table;
    private final Logger logger;

    public DNSSimulator(Logger logger) {
        this.logger = logger;
        this.table = new HashTable<>();
    }

    public static String present() {

        return """
                \t\tSERVIDOR DNS
                NÚMERO | Cadastrar N sites aleatórios
                C | Cadastrar/atualizar site no servidor
                V | Visualizar tabela hash dos sites cadastrados
                P | Pesquisar IP do site pela sua URI
                R | Remover site do servidor
                Q | Sair
                """;
    }

    public void addSites(int quantity) {
        for (int i = 0; i < quantity; i++) {
            URI uri = URIFactory.next();
            IPv4Address ip = IPv4Factory.next();
            this.table.add(uri, ip);

            this.logger.log("Site cadastrado: " + uri + " (" + ip + ")");
        }
    }

    public void addSite(Scanner scanner) {
        System.out.println("| Adicionar site...");

        System.out.print("URI: ");
        URI uri = new URI(scanner.nextLine());

        System.out.print("IP: ");
        IPv4Address ip = new IPv4Address(scanner.nextLine());

        this.table.add(uri, ip);
        this.logger.log("Site cadastrado: " + uri + " (" + ip + ")");
    }

    public String view() {
        HashTableViewer<URI, IPv4Address> viewer = new HashTableViewer<>(this.table);
        return viewer.view();
    }

    public IPv4Address findSite(Scanner scanner) throws NoSuchElementException {
        System.out.println("| Buscar IP do site...");

        System.out.print("URI: ");
        URI uri = new URI(scanner.nextLine());

        return this.table.find(uri);
    }

    public void removeSite(Scanner scanner) throws NoSuchElementException {
        System.out.println("| Remover site...");

        System.out.print("URI: ");
        URI uri = new URI(scanner.nextLine());

        this.table.remove(uri);
        this.logger.log("Site removido: " + uri);
    }

    public void interact(Scanner scanner) {
        System.out.println(present() + "\n");

        String input = scanner.nextLine();
        input = input.toUpperCase();

        while (!input.equals("Q")) {

            switch (input) {
                case "C":
                    this.addSite(scanner);
                    break;

                case "V":
                    System.out.println(this.view());
                    break;

                case "P":
                    System.out.print("IP: ");
                    try {
                        IPv4Address result = this.findSite(scanner);
                        System.out.println(result);
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "R":
                    try {
                        this.removeSite(scanner);
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    try {
                        int steps = Integer.parseInt(input);
                        this.addSites(steps);
                    } catch (NumberFormatException e) {
                        System.out.println("Opção desconhecida. Tente novamente.");
                    }
                    break;
            }

            this.logger.report();

            input = scanner.nextLine();
            input = input.toUpperCase();
        }
    }
}
