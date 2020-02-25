package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Algorithm;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Determinant;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Residual;

public class OutputCombiner {

    private LinearSystem linearSystem;
    private Algorithm algorithm;
    private Determinant determinant;
    private Residual residual;


    OutputCombiner(LinearSystem linearSystem) {
        this.linearSystem = linearSystem;
        this.algorithm = new Algorithm(linearSystem);
        this.determinant = new Determinant(linearSystem);
        this.residual = new Residual(algorithm);
    }
    //todo вывод решений и невязок
    public boolean combineOutput() {
        if (determinant.getDeterminant() == 0) {
            System.out.println("Can't solve it.");
            return false;
        }

        System.out.println("Determinant: " + determinant.getDeterminant());



        return true;
    }
}
