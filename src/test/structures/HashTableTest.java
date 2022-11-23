package test.structures;

import main.factories.utils.IPv4Factory;
import main.factories.utils.URIFactory;
import main.structures.HashTable;
import main.utils.IPv4Address;
import main.utils.URI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashTableTest {
    @Test
    void addFirstValue() {
        URI googleURI = new URI("www.google.com");
        IPv4Address googleIP = new IPv4Address("4.4.4.4");

        HashTable<URI, IPv4Address> table = new HashTable<>();
        table.add(googleURI, googleIP);

        Assertions.assertThat(table.find((new URI("www.google.com"))).toString())
                .isEqualTo((new IPv4Address("4.4.4.4")).toString());
        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(1);
        Assertions.assertThat(table.getTableSize()).isEqualTo(1);
    }

    @Test
    void add4Values() {
        final int quantity = 4;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getTableSize()).isEqualTo(1);
    }

    @Test
    void add5Values() {
        final int quantity = 5;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add6Values() {
        final int quantity = 6;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add10Values() {
        final int quantity = 10;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add11Values() {
        final int quantity = 11;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add15Values() {
        final int quantity = 15;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add21Values() {
        final int quantity = 21;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add31Values() {
        final int quantity = 31;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add75Values() {
        final int quantity = 75;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add100Values() {
        final int quantity = 100;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.next(), IPv4Factory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void update() {
        HashTable<URI, IPv4Address> table = new HashTable<>();

        table.add(URIFactory.from(0), IPv4Factory.from(0));
        Assertions.assertThat(table.find(URIFactory.from(0)).toString()).isEqualTo(IPv4Factory.from(0).toString());

        table.add(URIFactory.from(0), IPv4Factory.from(1));
        Assertions.assertThat(table.find(URIFactory.from(0)).toString()).isEqualTo(IPv4Factory.from(1).toString());
    }

    @Test
    void remove() {
        final int quantity = 11;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.from(i), IPv4Factory.from(i));
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);

        table.remove(URIFactory.from(7));
        table.remove(URIFactory.from(4));

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity - 2);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

}
