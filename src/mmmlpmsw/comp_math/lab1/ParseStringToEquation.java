package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Equation;

public class ParseStringToEquation {
    public static Equation trimStringToEquation(int count, String coefsString) throws InputParseException {
        try {
            coefsString.trim();
            String[] coefficients = coefsString.split(" ");
            double[] equationCoefs = new double[count + 1];
            if (coefficients.length - 1 == count)
                for (int j = 0; j < coefficients.length; j++) {
                    Double coefficient = Double.parseDouble(coefficients[j]);
                    equationCoefs[j] = coefficient;
                }
            else throw new InputParseException("System is incorrect");
            return new Equation(equationCoefs);
        } catch (NumberFormatException e) {
            throw new InputParseException("System is incorrect.");
        }
    }

    public static void check(int numberOfUnknowns) throws InputParseException {
        if (numberOfUnknowns > 20 || numberOfUnknowns < 1) {
            throw new InputParseException("Incorrect size of array");
        }

    }
}

