package uk.co.jamesmcguigan.algorithms;

public class SumFractions {
    public String calculate(final int numerator1, final int denominator1,
                            final int numerator2, final int denominator2) {
        int commonDenominator = denominator1 * denominator2;
        int numeratorResult = (commonDenominator / denominator1) * numerator1
                + (commonDenominator / denominator2) * numerator2;
        return getSimplified(numeratorResult, commonDenominator);
    }

    private String getSimplified(final int numerator, final int denominator) {
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

    private String getSimplifiedFractionWithWhole(final int currentNumerator, final int currentDenomiator) {
        int wholeNumber = Math.round(currentNumerator / currentDenomiator);
        int simplifiedNumerator = currentNumerator % currentDenomiator;
        return String.format("%s %s/%s", wholeNumber, Math.abs(simplifiedNumerator), currentDenomiator);
    }
}
