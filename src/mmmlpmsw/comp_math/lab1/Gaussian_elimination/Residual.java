package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Residual {

    private Algorithm algorithm;

    public Residual(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public double[] getResiduals() {
        return calculateResiduals(algorithm.getSavedLinearSystem(), algorithm.getSolutions());
    }

    private double[] calculateResiduals(double[][] system, double[] solutions) {
        double[] residuals = new double[system.length];
        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system.length; j++)
                residuals[i] += solutions[j] * system[i][j];
            residuals[i] -= system[i][system.length];
        }
      return residuals;
    }
}