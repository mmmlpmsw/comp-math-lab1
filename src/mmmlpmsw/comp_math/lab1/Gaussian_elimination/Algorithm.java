package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Algorithm {
    private LinearSystem linearSystem;
    private double[][] savedLinearSystem;
    final int numberOfUnknowns;
    private double[] solutions;

    public Algorithm (LinearSystem linearSystem) {
        this.linearSystem = linearSystem;
        this.savedLinearSystem = saveLinearSystem();
        this.numberOfUnknowns = linearSystem.getNumberOfUnknowns();
        this.solutions = solve();
    }

    public double[] solve() {
        straightRun();
        return returnRun();
    }

    private void straightRun () {
        for (int k = 0; k < numberOfUnknowns; k ++) {
            if (linearSystem.getEquationCoefficient(k, k) == 0) {
                loop : for (int i = 0; i < numberOfUnknowns; i ++) {
                    if (linearSystem.getEquationCoefficient(i, i) != 0) {
                        swap(linearSystem, 0, i);
                        break loop;
                    }
                }
            }
            for (int i = k + 1; i < numberOfUnknowns; i ++) {
                double coefficient = linearSystem.findCoefficient(linearSystem.getEquationCoefficient(k, k),
                        linearSystem.getEquationCoefficient(i, k));
                calculate(linearSystem, i, k, coefficient);
            }
        }
    }

    private double[] returnRun() {
        double[] solutions = new double[numberOfUnknowns];
        solutions[numberOfUnknowns - 1] =
                linearSystem.getEquationCoefficient(numberOfUnknowns - 1, numberOfUnknowns) /
                linearSystem.getEquationCoefficient(numberOfUnknowns - 1, numberOfUnknowns - 1);

        for (int k = numberOfUnknowns - 2; k >= 0; k --) {
            for (int i = k + 1; i < numberOfUnknowns; i ++) {
                solutions[k] += -linearSystem.getEquationCoefficient(k, i) * solutions[i];
            }
            solutions[k] += linearSystem.getEquationCoefficient(k, numberOfUnknowns);
            solutions[k] /= linearSystem.getEquationCoefficient(k, k);
        }
        return solutions;
    }

    private void swap(LinearSystem linearSystem, int index1, int index2) {
        for (int i = 0; i < linearSystem.getNumberOfUnknowns() + 1; i ++) {
            double t = linearSystem.getEquationCoefficient(index1, i);
            linearSystem.setEquationCoefficient(index1, i, linearSystem.getEquationCoefficient(index2, i));
            linearSystem.setEquationCoefficient(index2, i, t);
        }
    }

    private void calculate(LinearSystem system, int strIndex1, int strIndex2, double coef) {
        for (int i = 0; i < system.getNumberOfUnknowns() + 1; i ++) {
            double current = system.getEquationCoefficient(strIndex1, i);
            system.setEquationCoefficient(strIndex1, i,
                    system.getEquationCoefficient(strIndex2, i) * coef + current);
        }
    }

    private double[][] saveLinearSystem () {
        double [][] system = new double[numberOfUnknowns][numberOfUnknowns + 1];
        for (int i = 0; i < numberOfUnknowns; i ++) {
            for (int j = 0; j < numberOfUnknowns + 1; j ++) {
                system[i][j] = linearSystem.getEquationCoefficient(i, j);
            }
        }
        return system;
    }

    public double[][] getSavedLinearSystem() {
        return savedLinearSystem;
    }

    public double[] getSolutions() {
        return solutions;
    }
}
