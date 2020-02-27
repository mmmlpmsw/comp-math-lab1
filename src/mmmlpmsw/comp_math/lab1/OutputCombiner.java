package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Algorithm;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Determinant;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Residual;
import mmmlpmsw.comp_math.lab1.utils.ResidualFormatter;

public class OutputCombiner {

    private Algorithm algorithm;
    private Determinant determinant;
    private Residual residual;
    private LinearSystem linearSystem;

    public OutputCombiner(LinearSystem linearSystem) {
        this.algorithm = new Algorithm(linearSystem);
        this.determinant = new Determinant(linearSystem);
        this.residual = new Residual(algorithm);
        this.linearSystem = linearSystem;
    }

    public boolean combineOutput() {
        if (algorithm.getDeterminant() == 0) {
            System.out.println("Can't solve it - detA = 0x");
            return false;
        }

        System.out.println("Determinant: " + algorithm.getDeterminant());

        for (int i = 0; i < linearSystem.getNumberOfUnknowns(); i ++) {
            for (int j = 0; j < linearSystem.getNumberOfUnknowns() + 1; j ++) {
                System.out.printf("%.3f", linearSystem.getEquationCoefficient(i, j));
                System.out.print("  ");
            }
            System.out.println();
        }

        double[] solutions = algorithm.getSolutions();

        for (int i = 1; i < solutions.length + 1; i ++)
            System.out.println("x" + i + " = " + solutions[i - 1]);

        double[] residuals = residual.getResiduals();
        System.out.println("Residuals: ");
        for (int i = 0; i < residuals.length; i ++) {
            if (residuals[i] != 0)
                System.out.println(ResidualFormatter.format(residuals[i]));
            else
                System.out.println(residuals[i]);
        }
        return true;
    }
}
