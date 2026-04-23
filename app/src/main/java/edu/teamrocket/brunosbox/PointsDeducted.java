package edu.teamrocket.brunosbox;

/**
 * Representa un round con descuento de puntos.
 * El arbitro puede descontar puntos por infracciones (formato: "1,8 - 10").
 */
class PointsDeducted implements Round {
    private static final String SCORE_SEPARATOR = "-";
    private static final String DEDUCTION_SEPARATOR = ",";
    private static final String WHITESPACE_REGEX = "\\s";
    private static final String EMPTY_STRING = "";
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;

    private final String roundScore;
    private byte redBoxerScore;
    private byte blueBoxerScore;

    /**
     * Crea un nuevo round con descuento de puntos.
     * @param roundScore puntuación con descuentos en formato "X,Y - Z" o "X - Y,Z"
     */
    PointsDeducted(String roundScore) {
        this.roundScore = roundScore.replaceAll(WHITESPACE_REGEX, EMPTY_STRING);
        this.parseBoxerRoundScore();
    }

    /**
     * Obtiene la puntuación del round sin espacios.
     * @return puntuación formateada sin espacios
     */
    String getRoundScore() {
        return this.roundScore;
    }

    /**
     * Analiza los puntos considerando descuentos en la puntuación.
     */
    private void parseBoxerRoundScore() {
        String[] scoresParts = getRoundScore().split(SCORE_SEPARATOR, 2);
        String redBoxerRoundScore = scoresParts[FIRST_PART];
        String blueBoxerRoundScore = scoresParts[SECOND_PART];

        if (hasDeduction(redBoxerRoundScore)) {
            this.redBoxerScore = extractScoreWithDeduction(redBoxerRoundScore);
            this.blueBoxerScore = Byte.parseByte(blueBoxerRoundScore);
        } else {
            this.redBoxerScore = Byte.parseByte(redBoxerRoundScore);
            this.blueBoxerScore = extractScoreWithDeduction(blueBoxerRoundScore);
        }
    }

    /**
     * Verifica si la puntuación contiene descuentos.
     * @param score puntuación a verificar
     * @return true si contiene descuentos, false en caso contrario
     */
    private boolean hasDeduction(String score) {
        return score.contains(DEDUCTION_SEPARATOR);
    }

    /**
     * Extrae la puntuación principal de una puntuación con descuentos.
     * El descuento es el número menor, el score real es el mayor.
     * @param scoreWithDeduction puntuación con descuentos
     * @return puntuación principal
     */
    private byte extractScoreWithDeduction(String scoreWithDeduction) {
        String[] parts = scoreWithDeduction.split(DEDUCTION_SEPARATOR);
        byte first = Byte.parseByte(parts[FIRST_PART]);
        byte second = Byte.parseByte(parts[FIRST_PART + 1]);
        return first >= 8 ? first : second;
    }

    @Override
    public byte getRedBoxerScore() {
        return this.redBoxerScore;
    }

    @Override
    public byte getBlueBoxerScore() {
        return this.blueBoxerScore;
    }

    @Override
    public String toString() {
        return this.getRedBoxerScore() + " - " + this.getBlueBoxerScore();
    }
}