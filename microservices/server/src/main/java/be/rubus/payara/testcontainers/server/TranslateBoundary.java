package be.rubus.payara.testcontainers.server;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TranslateBoundary {

    public String getPattern(String lang) {
        String result;
        switch (lang) {
            case "fr":
                result = "Bonjour %s";
                break;
            case "bg":
                result = "Здравейте %s";
                break;
            case "es":
                result = "Hola %s";
                break;
            case "nl":
                result = "Hallo %s";
                break;
            default:
                result = "Hello %s";
        }
        return result;
    }
}
