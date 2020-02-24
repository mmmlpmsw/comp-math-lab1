import mmmlpmsw.comp_math.lab1.Algorithm;
import mmmlpmsw.comp_math.lab1.Determinant;
import mmmlpmsw.comp_math.lab1.Equation;
import mmmlpmsw.comp_math.lab1.LinearSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int size = in.nextInt();
//        in.nextLine();
//        double[][] matrix = new double[size][size + 1];
//        double[][] mt = new double[size][size + 1];
//
//        for (int i = 0; i < size; i++) {
//            String s = in.nextLine();
//            s.trim();
//            String[] aaaa = s.split(" ");
//            //todo проверка на кол-во в массиве
//            for (int j = 0; j < size + 1; j++) {
//                try {
//                    mt[i][j] = matrix[i][j] = Double.parseDouble(aaaa[j]);
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        Equation[] equations = new Equation[3];
        equations[0] = new Equation(new double[]{1, 4, 5, 6});
        equations[1] = new Equation(new double[]{7, 3, 3, 7});
        equations[2] = new Equation(new double[]{1, 5, 9, 0});


        LinearSystem system = new LinearSystem(equations);
        Algorithm algorithm = new Algorithm(system);
        double[] a = algorithm.solve();
        for (int i = 1; i < a.length + 1; i ++) {
            System.out.println("x" + i + " = " + a[i - 1]);
        }

        Determinant d = new Determinant(system);
        System.out.println(d.getDeterminant());
    }
}
