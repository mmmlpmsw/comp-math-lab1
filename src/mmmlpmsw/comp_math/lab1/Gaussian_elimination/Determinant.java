package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Determinant {

    private LinearSystem system;

    Determinant(LinearSystem system) {
        this.system = system;
    }

    double calculateDeterminant(int count) {
        double determinant = 1;
        for (int i = 0; i < system.getNumberOfUnknowns(); i ++) {
            determinant *= system.getEquationCoefficient(i, i);
        }
        determinant *= count;
        return determinant;
    }



//    private double calculateMatrix(double[][] matrix) {
//        double result = 0;
//        if (matrix.length == 2)
//            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
//
//            int flag = 1;
//            for (int i = 0; i < matrix.length; i ++) {
//                result += flag * matrix[0][i] * calculateMatrix(getMatrixWithoutRowAndColumn(matrix,0, i));
//                flag = -flag;
//            }
//        return result;
//    }
//
//    private double[][] getMatrixWithoutRowAndColumn(double[][] matrix, int row, int column) {
//        double[][] minor = new double [matrix.length - 1][matrix.length - 1];
//        int offsetRow = 0;
//        int offsetCol = 0;
//        for (int i = 0; i < matrix.length; i ++) {
//            offsetCol = 0;
//            for (int j = 0; j < matrix.length; j ++)
//                if (i == row) offsetRow = 1;
//                else if (j == column) offsetCol = 1;
//                    else minor[i - offsetRow][j - offsetCol] = matrix[i][j];
//        }
//        return minor;
//    }
//
//    private double[][] getArray (LinearSystem system) {
//        double [][] array = new double[system.getNumberOfUnknowns()][system.getNumberOfUnknowns() + 1];
//        for (int i = 0; i < system.getNumberOfUnknowns(); i ++)
//            for (int j = 0; j < system.getNumberOfUnknowns() + 1; j ++)
//                array[i][j] = system.getEquationCoefficient(i, j);
//        return array;
//    }
}