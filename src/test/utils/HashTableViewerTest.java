package test.utils;

import main.factories.utils.IPv4Factory;
import main.factories.utils.URIFactory;
import main.structures.HashTable;
import main.utils.HashTableViewer;
import main.utils.IPv4Address;
import main.utils.URI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashTableViewerTest {
    @Test
    void view2Nodes() {
        final int quantity = 2;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.from(i), IPv4Factory.from(i));
        }

        HashTableViewer<URI, IPv4Address> viewer = new HashTableViewer<>(table);
        Assertions.assertThat(viewer.view()).isEqualTo("[0] -> www.site0.com -> www.site1.com -> λ");
    }

    @Test
    void view11Nodes() {
        final int quantity = 11;
        HashTable<URI, IPv4Address> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(URIFactory.from(i), IPv4Factory.from(i));
        }

        String expected = """
                [0] -> www.site1.com -> www.site3.com -> www.site5.com -> www.site7.com -> www.site9.com -> www.site10.com -> λ
                [1] -> www.site0.com -> www.site2.com -> www.site4.com -> www.site6.com -> www.site8.com -> λ""";

        HashTableViewer<URI, IPv4Address> viewer = new HashTableViewer<>(table);
        Assertions.assertThat(viewer.view()).isEqualTo(expected);
    }

}
