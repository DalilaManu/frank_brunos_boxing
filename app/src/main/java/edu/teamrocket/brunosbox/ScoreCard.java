package edu.teamrocket.brunosbox;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa la tarjeta de puntuación de un árbitro en una pelea de boxeo.
 * Almacena los rounds y calcula los puntajes finales de ambos boxeadores.
 */
public class ScoreCard {
    private static final byte INITIAL_SCORE = 0;
    private static final String EMPTY_STRING = "";

    private final String color;
    private String redCorner = EMPTY_STRING;
    private String blueCorner = EMPTY_STRING;
    private String[] judgeScoreCard;
    private Byte redBoxerFinalScore = INITIAL_SCORE;
    private Byte blueBoxerFinalScore = INITIAL_SCORE;

    private List<Round> rounds = new ArrayList<>();
    
    /**
     * Crea una nueva tarjeta de puntuación.
     * @param color color identificador del árbitro
     */
    public ScoreCard(String color) {
        this.color = color;
    }

    /**
     * Establece el nombre del boxeador de la esquina roja.
     * @param boxerName nombre del boxeador
     */
    public void setRCorner(String boxerName) {
        this.redCorner = boxerName;
    }

    /**
     * Establece la puntuación del juez.
     * @param scoreCard array con las puntuaciones de cada round
     */
    private void setJudgeScoreCard(String[] scoreCard) {
        this.judgeScoreCard = scoreCard;
    }

    /**
     * Establece el nombre del boxeador de la esquina azul.
     * @param boxerName nombre del boxeador
     */
    public void setBCorner(String boxerName) {
        this.blueCorner = boxerName;
    }

    /**
     * Obtiene el número de rounds registrados.
     * @return cantidad de rounds
     */
    public byte getNumRounds() {
        return (byte) this.rounds.size();
    }

    /**
     * Obtiene lista inmutable de rounds.
     * @return lista de rounds
     */
    public List<Round> getRounds() {
        return Collections.unmodifiableList(this.rounds);
    }

    /**
     * Añade un round a la tarjeta de puntuación.
     * @param round round a añadir
     */
    private void addRound(Round round) {
        this.rounds.add(round);
    }

    /**
     * Genera representación en texto de la tarjeta de puntuación.
     * @return texto con detalles de rounds y puntuaciones finales
     */
    @Override
    public String toString() {
        return "\n\t\t\t   " + this.color
                + "\n\t\t" + this.blueCorner
                + "\t" + this.redCorner
                + "\n\t\t\t"
                + this.getNumRounds() + " rounds\n"
                + this.generateRoundsView()
                + "\n\t FINAL SCORE: "
                + this.getRedBoxerFinalScore()
                + " - "
                + this.getBlueBoxerFinalScore()
                + " FINAL SCORE";
    }

    /**
     * Carga la puntuación del juez y crea los rounds correspondientes.
     * @param judgeScoreCard array con las puntuaciones de cada round
     */
    public void loadJudgeScoreCard(String[] judgeScoreCard) {
        this.setJudgeScoreCard(judgeScoreCard);

        for (String roundScore : this.judgeScoreCard) {
            Optional<Round> round = Optional.ofNullable(RoundFactory.getRound(roundScore));
            round.ifPresent(this::addRound);
        }
    }

    /**
     * Obtiene la puntuación final del boxeador rojo.
     * Se calcula sumando los puntos de todos los rounds.
     * @return puntuación final del boxeador rojo
     */
    public byte getRedBoxerFinalScore() {
        if (this.redBoxerFinalScore == INITIAL_SCORE) {
            this.redBoxerFinalScore = calculateTotalScore(Round::getRedBoxerScore);
        }
        return this.redBoxerFinalScore;
    }

    /**
     * Obtiene la puntuación final del boxeador azul.
     * Se calcula sumando los puntos de todos los rounds.
     * @return puntuación final del boxeador azul
     */
    public int getBlueBoxerFinalScore() {
        if (this.blueBoxerFinalScore == INITIAL_SCORE) {
            this.blueBoxerFinalScore = calculateTotalScore(Round::getBlueBoxerScore);
        }
        return this.blueBoxerFinalScore;
    }

    /**
     * Calcula la puntuación total usando una función de puntuación.
     * @param scoreExtractor función para extraer el score del round
     * @return puntuación total
     */
    private byte calculateTotalScore(java.util.function.Function<Round, Byte> scoreExtractor) {
        return this.getRounds()
            .stream()
            .map(scoreExtractor)
            .map(Byte::intValue)
            .reduce((int) INITIAL_SCORE, Integer::sum)
            .byteValue();
    }

    /**
     * Genera una vista formateada de todos los rounds.
     * Muestra puntos por round y totales acumulados.
     * @return representación en texto de los rounds
     */
    private String generateRoundsView() {
        StringBuilder roundsView = new StringBuilder();
        roundsView.append("\tRound \t Score \t Round \t Score \t Round\n")
                  .append("\tScore \t Total \t       \t Total \t Score");

        byte roundNum = 1;
        byte redBoxerScoreTotal = INITIAL_SCORE;
        byte blueBoxerScoreTotal = INITIAL_SCORE;

        for (Round round : this.rounds) {
            redBoxerScoreTotal += round.getRedBoxerScore();
            blueBoxerScoreTotal += round.getBlueBoxerScore();

            roundsView.append("\n\t")
                .append(round.getRedBoxerScore())
                .append("\t\s")
                .append(redBoxerScoreTotal)
                .append("\t\s\s")
                .append(roundNum++)
                .append("\t\s")
                .append(blueBoxerScoreTotal)
                .append("\t\s")
                .append(round.getBlueBoxerScore());
        }
        return roundsView.toString();
    }
}