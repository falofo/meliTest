package com.meli.test.util;

import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE;

public class SequenceUtils {

    private SequenceUtils() {
    }

    /**
     * Retorna las secuencias verticales a partir del ADN
     *
     * @param dna
     * @return
     */
    public static String getVertical(String[] dna){
        StringBuilder verticals = new StringBuilder();

        for (int j = 0; j < dna.length; j++) {
            if (verticals.length() > 0)
                verticals.append("|");
            for (String sequence : dna) {
                verticals.append(sequence.charAt(j));
            }
        }
        return verticals.toString();
    }

    /**
     * Retorna la secuencia de diagonales de Derecha a Izquierda
     *
     * @param dna
     * @return
     */
    public static String getRightLeftDiagonals(String[] dna){
        /**
         * Se calcula desde donde se iniciara el recorrido de la cadena de ADN, donde se excluyen los datos sin
         * relevancia (diagonales con un tamaño menor a NUMBER_OF_CONSECUTIVE_LETTERS)
         */
        int i = 0, j = NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE;
        StringBuilder diagonal = new StringBuilder();
        while(j < dna.length) {
            if(diagonal.length()>0)
                diagonal.append("|");

            diagonal.append(getRightLeftDiagonal(dna, i, j));

            i = 0;
            j++;
        }

        i = 1;
        j = dna.length - 1;

        while(i < dna.length - NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE) {
            diagonal.append("|");

            diagonal.append(getRightLeftDiagonal(dna, i, j));
            i++;
            j = dna.length - 1;
        }

        return diagonal.toString();
    }

    /**
     * Retorna la cadena de las Diagonales de derecha a Izquierda separadas por un |
     *
     * @param dna
     * @param row
     * @param column
     * @return
     */
    private static String getRightLeftDiagonal(String[] dna, int row, int column) {
        StringBuilder diagonal = new StringBuilder();
        while (row < dna.length && column >= 0) {
            diagonal.append(dna[row].charAt(column));
            row++;
            column--;
        }
        return diagonal.toString();
    }

    /**
     * Retorna la secuencia de diagonales de izquierda a derecha
     *
     * @param dna
     * @return
     */
    public static String getLeftRightDiagonals(String[] dna){
        /**
         * Se calcula desde donde se iniciara el recorrido de la cadena de ADN, donde se excluyen los datos sin
         * relevancia (diagonales con un tamaño menor a NUMBER_OF_CONSECUTIVE_LETTERS)
         */
        int i = 0, j = (dna.length - 1) - NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE;
        StringBuilder diagonal = new StringBuilder();
        while(j >= 0) {
            if(diagonal.length()>0)
                diagonal.append("|");

            diagonal.append(getLeftRightDiagonal(dna, i, j));

            i = 0;
            j--;
        }

        i = 1;
        j = 0;

        while(i < dna.length - NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE) {
            diagonal.append("|");

            diagonal.append(getLeftRightDiagonal(dna, i, j));
            i++;
            j = 0;
        }

        return diagonal.toString();
    }

    /**
     * Retorna la cadena de las diagonales de izquierda a derecha separadas por un |
     *
     * @param dna
     * @param row
     * @param column
     * @return
     */
    private static String getLeftRightDiagonal(String[] dna, int row, int column) {
        StringBuilder diagonal = new StringBuilder();
        while (row < dna.length && column < dna.length) {
            diagonal.append(dna[row].charAt(column));
            row++;
            column++;
        }
        return diagonal.toString();
    }
}
