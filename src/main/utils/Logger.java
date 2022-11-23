package main.utils;

import java.util.LinkedList;
import java.util.Queue;

public class Logger {

    private final Queue<String> messages = new LinkedList<>();

    public Logger() {
    }

    public void log(String information) {
        if (information != null) {
            this.messages.add(information);
        }
    }

    public String getReport() {
        StringBuilder result = new StringBuilder();

        while (!this.messages.isEmpty()) {
            result.append("> ").append(this.messages.remove().trim()).append("\n");
        }

        return result.toString();
    }

    public void report() {
        System.out.print(this.getReport());
    }

    public int size() {
        return this.messages.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }
}
