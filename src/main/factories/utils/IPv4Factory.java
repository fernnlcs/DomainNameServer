package main.factories.utils;

import main.utils.IPv4Address;

public class IPv4Factory {
    private static int nextIndex = 0;

    public static IPv4Address next() {
        return from(getNextIndex());
    }

    public static IPv4Address from(int i) {
        final int max = (int) Math.pow(2, 8);

        int[] defaults = new int[]{192, 168, 0, 1};

        int[] values = new int[IPv4Address.PARTS];
        for (int j = 0; j < IPv4Address.PARTS; j++) {
            int value = i;
            value /= (int) Math.pow(max, IPv4Address.PARTS - 1 - j);
            value += defaults[j];
            value %= max;
            values[j] = value;
        }

        String[] parts = new String[]{
                String.valueOf(values[0]),
                String.valueOf(values[1]),
                String.valueOf(values[2]),
                String.valueOf(values[3]),
        };
        return new IPv4Address(String.join(".", parts));
    }

    private static int getNextIndex() {
        return nextIndex++;
    }
}
