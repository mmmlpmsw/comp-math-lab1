package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Residual {

    private Algorithm algorithm;
    private double[] residuals;

    public Residual(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.residuals = calculateResiduals(algorithm.getSavedLinearSystem(), algorithm.getSolutions());
    }

    public double[] getResiduals() {
        return residuals;
//        return calculateResiduals(algorithm.getSavedLinearSystem(), algorithm.getSolutions());
    }

    private double[] calculateResiduals(double[][] system, double[] solutions) {
        this.residuals = new double[solutions.length];
        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system.length; j++)
                residuals[i] += solutions[j] * system[i][j];
            residuals[i] -= system[i][system.length];
        }
      return residuals;
    }
}