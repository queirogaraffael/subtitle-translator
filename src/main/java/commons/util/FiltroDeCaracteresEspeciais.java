package commons.util;

public class FiltroDeCaracteresEspeciais {
    public static String filter(String word) {
        if (word == null || word.trim().isEmpty()) {
            return null;
        }

        String filtered = word.replaceAll("[^\\p{L}'']", "");

        if (filtered.endsWith("'s") || filtered.endsWith("´s")) {
            filtered = filtered.substring(0, filtered.length() - 2);
        } else if (filtered.endsWith("'") || filtered.endsWith("´")) {
            filtered = filtered.substring(0, filtered.length() - 1);
        }

        if (filtered.isEmpty()) {
            return null;
        }

        return filtered;
    }
}
