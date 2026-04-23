package edu.teamrocket.brunosbox;

public class RoundFactory {

    public static Round getRound(String roundScore) {

        if (roundScore == null) {
            return null;
        }

        // Normaliza o input para evitar problemas com espaços, vírgulas e maiúsculas
        String key = normalize(roundScore);

        switch (key) {

            // Regular Round
            case "10-9":
            case "9-10":
                return new RegularRound(roundScore);

            // Knockdown Round
            case "10-8":
            case "8-10":
                return new KnockdownRound(roundScore);

            // Points Deducted
            case "18-10":
            case "10-81":
                return new PointsDeducted(roundScore);

            default:
                return null;
        }
    }

    /**
     * Normaliza a string removendo espaços, vírgulas e deixando tudo minúsculo.
     * Ex.: "10 - 8 ,1" → "1081"
     */
    private static String normalize(String s) {
        return s.toLowerCase()
                .replace(" ", "")
                .replace(",", "");
    }
}

