package com.meli.test.util;

import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS_WITHOUT_RELEVANCE;

public class SequenceUtils {
    /**
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
     *
     * @param dna
     * @return
     */
    public static String getRightLeftDiagonals(String[] dna){
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
            if(diagonal.length()>0)
                diagonal.append("|");

            diagonal.append(getRightLeftDiagonal(dna, i, j));
            i++;
            j = dna.length - 1;
        }

        return diagonal.toString();
    }

    /**
     *
     * @param dna
     * @param startRow
     * @param startColumn
     * @return
     */
    private static String getRightLeftDiagonal(String[] dna, int startRow, int startColumn) {
        StringBuilder diagonal = new StringBuilder();
        int k;
        int l;
        k = startRow;
        l = startColumn;
        while (k < dna.length && l >= 0) {
            diagonal.append(dna[k].charAt(l));
            k++;
            l--;
        }
        return diagonal.toString();
    }

    /**
     *
     * @param dna
     * @return
     */
    public static String getLeftRightDiagonals(String[] dna){
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
            if(diagonal.length()>0)
                diagonal.append("|");

            diagonal.append(getLeftRightDiagonal(dna, i, j));
            i++;
            j = 0;
        }

        return diagonal.toString();
    }

    /**
     *
     * @param dna
     * @param startRow
     * @param startColumn
     * @return
     */
    private static String getLeftRightDiagonal(String[] dna, int startRow, int startColumn) {
        StringBuilder diagonal = new StringBuilder();
        int k;
        int l;
        k = startRow;
        l = startColumn;
        while (k < dna.length && l < dna.length) {
            diagonal.append(dna[k].charAt(l));
            k++;
            l++;
        }
        return diagonal.toString();
    }
}
