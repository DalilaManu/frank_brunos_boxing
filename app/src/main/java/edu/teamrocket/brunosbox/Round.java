package edu.teamrocket.brunosbox;

/**
 * Representa un round en una pelea de boxeo.
 * Define el contrato para obtener los puntos de cada boxeador.
 */
public interface Round {
    /**
     * Obtiene la puntuación del boxeador rojo.
     * @return puntuación del boxeador rojo
     */
    byte getRedBoxerScore();

    /**
     * Obtiene la puntuación del boxeador azul.
     * @return puntuación del boxeador azul
     */
    byte getBlueBoxerScore();
}