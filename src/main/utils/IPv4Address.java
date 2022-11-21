package main.utils;

import main.contracts.utils.IPAddressable;

public class IPv4Address implements IPAddressable {
    private final byte[] address;

    public static final int PARTS = 4;

    public IPv4Address(byte[] address) {
        // Evita a entrada nula
        // Cancela a operação se não forem encontradas as 4 partes
        if (address == null || address.length != IPv4Address.PARTS) {
            throw new IllegalArgumentException("É necessário o endereço ter " + IPv4Address.PARTS + " partes.");
        }

        this.address = address;
    }

    public IPv4Address(String address) {
        // Evita a entrada nula
        if (address == null) {
            throw new IllegalArgumentException("É necessário o endereço ter " + IPv4Address.PARTS + " partes.");
        }

        // Separa as partes do endereço
        String[] parts = address.split("\\.");

        // Cancela a operação se não forem encontradas as 4 partes
        if (parts.length != IPv4Address.PARTS) {
            throw new IllegalArgumentException("É necessário o endereço ter " + IPv4Address.PARTS + " partes.");
        }

        byte[] result = new byte[IPv4Address.PARTS];

        // Transforma as partes em ‘bytes’
        for (int i = 0; i < IPv4Address.PARTS; i++) {
            int number = Integer.parseInt(parts[i]);

            if (number < 0 || number > 255) {
                throw new IllegalArgumentException("Cada parte do endereço IP deve estar entre 0 e 255.");
            }

            result[i] = (byte) number;
        }

        this.address = result;
    }

    public byte[] getBytes() {
        return this.address.clone();
    }

    public int[] getParts() {
        int[] parts = new int[IPv4Address.PARTS];

        for (int i = 0; i < IPv4Address.PARTS; i++) {
            parts[i] = this.address[i] & 0xff;
        }

        return parts;
    }

    @Override
    public String getFull() {
        String[] parts = new String[IPv4Address.PARTS];
        int[] numbers = this.getParts();

        for (int i = 0; i < IPv4Address.PARTS; i++) {
            parts[i] = String.valueOf(numbers[i]);
        }
        
        return String.join(".", parts);
    }

    @Override
    public String toString() {
        return this.getFull();
    }
}
