package main.factories.utils;

import main.utils.IPv4Address;

public class IPv4Factory {
    private static int nextIndex = 0;

    public static IPv4Address next() {
        return from(getNextIndex());
    }

    public static IPv4Address from(int i) {
        String[] parts = new String[]{
                String.valueOf(i % 256),
                String.valueOf((i + 1) % 256),
                String.valueOf((i + 2) % 256),
                String.valueOf((i + 3) % 256),
        };
        return new IPv4Address(String.join(".", parts));
    }

    private static int getNextIndex() {
        return nextIndex++;
    }
}
