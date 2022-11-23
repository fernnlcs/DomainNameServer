package main.utils;

import java.util.LinkedList;
import java.util.Queue;

public class Logger {

    private final Queue<String> messages = new LinkedList<>();
    private boolean isLocked = false;
    private boolean isWaiting = false;

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

    private void printReport() {
        System.out.print("\n" + this.getReport());
    }

    public void report() {
        if (this.isLocked) {
            this.isWaiting = true;
        } else {
            printReport();
        }
    }

    public int size() {
        return this.messages.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        this.isLocked = false;
        if (this.isWaiting) {
            this.report();
        }
    }

    public void waitFor(Runnable runnable) {
        this.lock();

        try {
            runnable.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.unlock();
    }
}
