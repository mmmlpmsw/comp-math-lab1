package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class LinearSystem {
    private static final double EPS = 1e-14d;
    private Equation[] matrix;

    public LinearSystem (Equation[] matrix) {
            this.matrix = matrix;
    }

    private Equation getEquation(int index) {
        return matrix[index];
    }

    public int getNumberOfUnknowns() {
        return matrix.length;
    }

    public double getEquationCoefficient(int equationIndex, int index) {
        return getEquation(equationIndex).getCoefficient(index);
    }

    public void setEquationCoefficient(int equationIndex, int index, double value) {
       getEquation(equationIndex).setCoefficient(index, value);
    }

    public double findCoefficient(double a, double b) {
        return -b/a;
    }
}
