package test.utils;

import main.utils.IPv4Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IPv4AddressTest {
    @ParameterizedTest
    @MethodSource("validStrings")
    void constructFromValidStrings(String text, int[] parts) {
        IPv4Address address = new IPv4Address(text);
        Assertions.assertThat(address.getParts()).containsExactly(parts);
    }

    @ParameterizedTest
    @MethodSource("validBytes")
    void constructFromValidBytes(byte[] bytes, int[] parts) {
        IPv4Address address = new IPv4Address(bytes);
        Assertions.assertThat(address.getParts()).containsExactly(parts);
    }

    @Test
    void constructFromNull() {
        Assertions.assertThatThrownBy(() -> new IPv4Address((String) null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new IPv4Address((byte[]) null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("invalidStrings")
    void constructFromInvalidStrings(String text) {
        Assertions.assertThatThrownBy(() -> new IPv4Address(text)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("invalidBytes")
    void constructFromInvalidBytes(byte[] bytes) {
        Assertions.assertThatThrownBy(() -> new IPv4Address(bytes)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validStringsAndBytes")
    void getBytes(String text, byte[] bytes) {
        IPv4Address address = new IPv4Address(text);
        Assertions.assertThat(address.getBytes()).containsExactly(bytes);
    }

    @ParameterizedTest
    @MethodSource("validStringsAndBytes")
    void toString(String text, byte[] bytes) {
        IPv4Address address = new IPv4Address(bytes);
        Assertions.assertThat(address.toString()).isEqualTo(text);
    }

    static Stream<Arguments> validStrings() {
        return Stream.of(
                Arguments.of("192.168.0.0", new int[]{192, 168, 0, 0}),
                Arguments.of("192.168.0.1", new int[]{192, 168, 0, 1}),
                Arguments.of("192.168.0.127", new int[]{192, 168, 0, 127}),
                Arguments.of("192.168.0.128", new int[]{192, 168, 0, 128}),
                Arguments.of("192.168.0.129", new int[]{192, 168, 0, 129}),
                Arguments.of("192.168.0.254", new int[]{192, 168, 0, 254}),
                Arguments.of("192.168.0.255", new int[]{192, 168, 0, 255})
        );
    }

    static Stream<Arguments> validBytes() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 0},
                        new int[]{192, 168, 0, 0}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 1},
                        new int[]{192, 168, 0, 1}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 127},
                        new int[]{192, 168, 0, 127}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 128},
                        new int[]{192, 168, 0, 128}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 129},
                        new int[]{192, 168, 0, 129}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 254},
                        new int[]{192, 168, 0, 254}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 255},
                        new int[]{192, 168, 0, 255})
        );
    }

    static Stream<Arguments> invalidStrings() {
        return Stream.of(
                Arguments.of("192.168.0.256"),
                Arguments.of("abc.168.0.1"),
                Arguments.of("192.168.0"),
                Arguments.of("192.168.0.1.0"),
                Arguments.of("1920.168.0.1270"),
                Arguments.of("1921680128"),
                Arguments.of("")
        );
    }

    static Stream<Arguments> invalidBytes() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0}),
                Arguments.of(new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 128, (byte) 0}),
                Arguments.of(new byte[]{(byte) 1921680128}),
                Arguments.of(new byte[]{})
        );
    }

    static Stream<Arguments> validStringsAndBytes() {
        return Stream.of(
                Arguments.of("192.168.0.0",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 0}),
                Arguments.of("192.168.0.1",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 1}),
                Arguments.of("192.168.0.127",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 127}),
                Arguments.of("192.168.0.128",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 128}),
                Arguments.of("192.168.0.129",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 129}),
                Arguments.of("192.168.0.254",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 254}),
                Arguments.of("192.168.0.255",
                        new byte[]{(byte) 192, (byte) 168, (byte) 0, (byte) 255})
        );
    }

}
