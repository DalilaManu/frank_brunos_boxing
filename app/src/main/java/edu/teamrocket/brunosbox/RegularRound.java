package edu.teamrocket.brunosbox;

/**
 * Representa un round regular en una pelea de boxeo.
 * Almacena los puntos de ambos boxeadores sin descuentos.
 */
class RegularRound implements Round {
    private static final String SCORE_SEPARATOR = "-";
    private static final String WHITESPACE_REGEX = "\\s";
    private static final String EMPTY_STRING = "";

    private final String roundScore;
    private byte redBoxerScore;
    private byte blueBoxerScore;

    /**
     * Crea un nuevo round regular.
     * @param roundScore puntuación del round en formato "X - Y"
     */
    RegularRound(String roundScore) {
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
     * Analiza y convierte los puntos de la puntuación del round a bytes.
     */
    private void parseBoxerRoundScore() {
        String[] scores = getRoundScore().split(SCORE_SEPARATOR, 2);
        this.redBoxerScore = Byte.parseByte(scores[0]);
        this.blueBoxerScore = Byte.parseByte(scores[1]);
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