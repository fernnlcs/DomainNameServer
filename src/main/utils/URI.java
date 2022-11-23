package main.utils;

import main.contracts.Identifiable;
import main.contracts.utils.NameAddressable;

public class URI implements NameAddressable, Identifiable {
    private String subdomain;
    private String topLevelDomain;
    private String[] secondLevelDomains;

    public static final String DEFAULT_SUBDOMAIN = "www";

    public URI(String subdomain, String topLevelDomain, String[] secondLevelDomains) {
        this.setSubdomain(subdomain);
        this.setTopLevelDomain(topLevelDomain);
        this.setSecondLevelDomains(secondLevelDomains);
    }

    public URI(String subdomain, String topLevelDomain, String secondLevelDomain) {
        this.setSubdomain(subdomain);
        this.setTopLevelDomain(topLevelDomain);
        this.setSecondLevelDomains(new String[]{secondLevelDomain});
    }

    public URI(String topLevelDomain, String[] secondLevelDomains) {
        this.setSubdomain(URI.DEFAULT_SUBDOMAIN);
        this.setTopLevelDomain(topLevelDomain);
        this.setSecondLevelDomains(secondLevelDomains);
    }

    public URI(String topLevelDomain, String secondLevelDomain) {
        this.setSubdomain(URI.DEFAULT_SUBDOMAIN);
        this.setTopLevelDomain(topLevelDomain);
        this.setSecondLevelDomains(new String[]{secondLevelDomain});
    }

    public URI(String fullAddress) {
        if (fullAddress == null) {
            throw new IllegalArgumentException("As partes do endereço não devem ser nulas.");
        }

        String[] parts = fullAddress.split("/")[0].split("\\.");

        String subdomain;
        String topLevelDomain;
        String[] secondLevelDomains;

        switch (parts.length) {
            case 4 -> {
                subdomain = parts[0];
                topLevelDomain = parts[1];
                secondLevelDomains = new String[]{parts[2], parts[3]};
            }

            case 3 -> {
                subdomain = parts[0];
                topLevelDomain = parts[1];
                secondLevelDomains = new String[]{parts[2]};
            }

            case 2 -> {
                subdomain = URI.DEFAULT_SUBDOMAIN;
                topLevelDomain = parts[0];
                secondLevelDomains = new String[]{parts[1]};
            }

            default -> throw new IllegalArgumentException("Formato de URI não reconhecido.");
        }

        this.subdomain = subdomain;
        this.topLevelDomain = topLevelDomain;
        this.secondLevelDomains = secondLevelDomains;
    }

    public void setSubdomain(String subdomain) {
        if (subdomain == null || subdomain.equals("")) {
            throw new IllegalArgumentException("O subdomínio não deve ser nulo ou estar em branco.");
        }
        this.subdomain = subdomain;
    }

    public void setTopLevelDomain(String topLevelDomain) {
        if (topLevelDomain == null || topLevelDomain.equals("")) {
            throw new IllegalArgumentException("O domínio de topo não deve ser nulo ou estar em branco.");
        }
        this.topLevelDomain = topLevelDomain;
    }

    public void setSecondLevelDomains(String[] secondLevelDomains) {
        if (secondLevelDomains == null) {
            throw new IllegalArgumentException("O domínio de segundo nível não deve ser nulo.");
        }

        for (String part : secondLevelDomains) {
            if (part == null || part.equals("")) {
                throw new IllegalArgumentException("As partes do domínio de segundo nível não devem ser nulas.");
            }
        }

        if (secondLevelDomains.length < 1 || secondLevelDomains.length > 2) {
            throw new IllegalArgumentException("O domínio de segundo nível precisa ter uma ou duas partes.");
        }

        this.secondLevelDomains = secondLevelDomains;
    }

    @Override
    public String getFull() {
        return String.join(".",
                String.join(".", subdomain, topLevelDomain),
                String.join(".", this.secondLevelDomains));
    }

    @Override
    public String toString() {
        return this.getFull();
    }

    @Override
    public int getHash() {
        return Math.abs(this.getFull().hashCode());
    }

    @Override
    public String getIdentifier() {
        return this.getFull();
    }
}
