package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Algorithm;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Residual;
import mmmlpmsw.comp_math.lab1.utils.OutputFormatter;

import static java.lang.Math.abs;

public class OutputCombiner {

    private Algorithm algorithm;
    private Residual residual;
    private LinearSystem linearSystem;
    private static final double EPS = 1e-11d;
    public static final int ACCURACY = 3;

    public OutputCombiner(LinearSystem linearSystem) {
        this.algorithm = new Algorithm(linearSystem);
        this.residual = new Residual(algorithm);
        this.linearSystem = linearSystem;
    }

    public boolean combineOutput() {
        if (algorithm.getDeterminant() == 0) {
            System.out.println("Can't solve it - detA = 0.");
            return false;
        }

        System.out.println("Determinant: " + OutputFormatter.format(algorithm.getDeterminant()));

        System.out.println("Triangular matrix: ");
        for (int i = 0; i < linearSystem.getNumberOfUnknowns(); i ++) {
            for (int j = 0; j < linearSystem.getNumberOfUnknowns() + 1; j ++) {
                if (abs(linearSystem.getEquationCoefficient(i, j)) < EPS)
                    System.out.print("0,000");
                else
                    System.out.printf("%.3f", linearSystem.getEquationCoefficient(i, j));
                System.out.print("  ");
            }
            System.out.println();
        }

        double[] solutions = algorithm.getSolutions();

        System.out.println("Solutions: ");
        for (int i = 1; i < solutions.length + 1; i ++) {
            System.out.print("x" + i + " = ");
            System.out.println( Math.round(solutions[i - 1]*1000)/1000.0);
        }

        double[] residuals = residual.getResiduals();
        System.out.println("Residuals: ");
        for (int i = 0; i < residuals.length; i ++)
            System.out.println(OutputFormatter.format(residuals[i], ACCURACY));
        return true;
    }
}
