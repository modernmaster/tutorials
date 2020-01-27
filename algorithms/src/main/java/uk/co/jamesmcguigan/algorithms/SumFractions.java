package uk.co.jamesmcguigan.algorithms;

public class SumFractions {
    public String calculate(int numerator1, int denominator1, int numerator2, int denominator2) {
        int commonDenominator = denominator1 * denominator2;
        int numeratorResult = (commonDenominator / denominator1) * numerator1
                + (commonDenominator / denominator2) * numerator2;
        return getSimplified(numeratorResult, commonDenominator);
    }

    private String getSimplified(int numerator, int denominator) {
        int currentNumerator = numerator;
        int currentDenomiator = denominator;
        while (currentNumerator % 2 == 0 && currentDenomiator % 2 == 0) {
            currentDenomiator = denominator / 2;
            currentNumerator = numerator / 2;
        }
        if (Math.abs(currentNumerator) >= currentDenomiator) {
            return getSimplifiedFractionWithWhole(currentNumerator, currentDenomiator);
        }
        return String.format("%s/%s", currentNumerator, currentDenomiator);
    }

    private String getSimplifiedFractionWithWhole(int currentNumerator, int currentDenomiator) {
        int wholeNumber = Math.round(currentNumerator / currentDenomiator);
        int simplifiedNumerator = currentNumerator % currentDenomiator;
        return String.format("%s %s/%s", wholeNumber, Math.abs(simplifiedNumerator), currentDenomiator);
    }
}
