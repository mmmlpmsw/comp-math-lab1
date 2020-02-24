package mmmlpmsw.comp_math.lab1;

public class Equation {
    private double[] equationCoefficients;
//    private int numberOfUnknowns;
    //todo remove public after tests
    public Equation (double[] equationCoefficients) {
        this.equationCoefficients = equationCoefficients;
//        this.numberOfUnknowns = equationCoefficients.length - 1;
    }

    public double getCoefficient(int index) {
        return equationCoefficients[index];
    }

    public void setCoefficient(int index, double value) {
        equationCoefficients[index] = value;
    }


}
