package main.utils;

import main.contracts.Identifiable;
import main.structures.HashTable;

import java.util.LinkedList;

public class HashTableViewer<Key extends Identifiable, Value> {

    private final HashTable<Key, Value> table;

    public HashTableViewer(HashTable<Key, Value> table) {
        this.table = table;
    }

    public String view() {
        String[] lines = new String[this.table.getTableSize()];

        for (int i = 0; i < this.table.getTableSize(); i++) {
            StringBuilder line = new StringBuilder();
            line.append("[").append(i).append("] -> ");

            LinkedList<HashTable<Key, Value>.Node> page = this.table.getPage(i);

            for (HashTable<Key, Value>.Node node : page) {
                line.append(node.toString()).append(" -> ");
            }

            line.append("λ");

            lines[i] = line.toString();
        }

        return String.join("\n", lines);
    }
}
