package main.factories.utils;

import main.utils.URI;

public class URIFactory {
    private static int nextIndex = 0;

    public static URI next() {
        return from(getNextIndex());
    }

    public static URI from(int i) {
        return new URI("www.site" + i + ".com");
    }

    private static int getNextIndex() {
        return nextIndex++;
    }
}
