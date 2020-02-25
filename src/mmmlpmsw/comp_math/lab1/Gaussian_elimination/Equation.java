package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Equation {
    private double[] equationCoefficients;
    //todo remove public after tests
    public Equation (double[] equationCoefficients) {
        this.equationCoefficients = equationCoefficients;
    }

    public double getCoefficient(int index) {
        return equationCoefficients[index];
    }

    public void setCoefficient(int index, double value) {
        equationCoefficients[index] = value;
    }


}
