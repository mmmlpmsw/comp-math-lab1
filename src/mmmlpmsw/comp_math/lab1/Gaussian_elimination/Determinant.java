package mmmlpmsw.comp_math.lab1.Gaussian_elimination;

public class Determinant {

    private LinearSystem system;

    public Determinant(LinearSystem system) {
        this.system = system;
    }

    public double getDeterminant() {
        return calculateMatrix(getArray(system));
    }

    private double calculateMatrix(double[][] matrix) {
        double calcResult = 0d;
        if (matrix.length == 2) {
            calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else {
            int flag = 1;
            for (int i = 0; i < matrix.length; i ++) {
                if (i % 2 == 1) flag =- 1;
                else flag = 1;

                calcResult += flag * matrix[0][i] * calculateMatrix(getMinor(matrix,0, i));
            }
        }
        return calcResult;
    }

    private double[][] getMinor(double[][] matrix, int row, int column) {
        int minorLength = matrix.length - 1;
        double[][] minor = new double [minorLength][minorLength];

        //эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dI = 0;
        int dJ = 0;
        for (int i = 0; i <= minorLength; i ++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j ++) {
                if (i == row) dI = 1;
                else {
                    if (j == column) dJ = 1;
                    else minor[i-dI][j-dJ] = matrix[i][j];
                }
            }
        }

        return minor;

    }

    private double[][] getArray (LinearSystem system) {
        double [][] array = new double[system.getNumberOfUnknowns()][system.getNumberOfUnknowns() + 1];
        for (int i = 0; i < system.getNumberOfUnknowns(); i ++) {
            for (int j = 0; j < system.getNumberOfUnknowns() + 1; j ++) {
                array[i][j] = system.getEquationCoefficient(i, j);
            }
        }
        return array;
    }

}