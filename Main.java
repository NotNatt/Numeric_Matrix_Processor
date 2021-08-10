package processor;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Scanner read = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat( "#.##" );
    public static void main(String[] args) {
        printMenu();
    }

    /**
     * Addition
     */
    public static void add(double[][] matrix1) {
        int numRows1 = matrix1.length;
        int numCols1 = matrix1[0].length;

        System.out.print("Enter size of second matrix: ");
        int numRows2 = read.nextInt();
        int numCols2 = read.nextInt();

        if (numRows1 == numRows2 && numCols1 == numCols2) {
            double[][] matrix2 = new double[numRows2][numCols2];

            System.out.println("Enter second matrix:");
            for (int i = 0; i < numRows2; i++) {
                for (int j = 0; j < numCols2; j++) {
                    matrix2[i][j] = read.nextDouble();
                }
            }

            System.out.println("The result is:");
            for (int i = 0; i < numRows2; i++) {
                for (int j = 0; j < numCols2; j++) {
                    System.out.print(df.format(matrix1[i][j] + matrix2[i][j]) + " ");
                }
                System.out.println();
            }

        }
        else {
            System.out.println("The operation cannot be performed.");
        }

        System.out.println();
        printMenu();
    }

    /**
     * Multiplication by a Constant
     */
    public static void multiplyByNum(double[][] matrix) {
        int numRows1 = matrix.length;
        int numCols1 = matrix[0].length;

        System.out.print("Enter constant: ");
        double c = read.nextDouble();
        System.out.println("The result is:");
        for (int i = 0; i < numRows1; i++) {
            for (int j = 0; j < numCols1; j++) {
                System.out.print(df.format(c * matrix[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println();
        printMenu();
    }

    /**
     * Matrix on Matrix Mult.
     */
    public static void matrixMultiplication(double[][] matrix1) {
        int numRows1 = matrix1.length;
        int numCols1 = matrix1[0].length;

        System.out.print("Enter size of second matrix: ");
        int numRows2 = read.nextInt();
        int numCols2 = read.nextInt();

        if (numCols1 == numRows2) {
            double[][] matrix2 = new double[numRows2][numCols2];

            System.out.println("Enter second matrix:");
            for (int i = 0; i < numRows2; i++) {
                for (int j = 0; j < numCols2; j++) {
                    matrix2[i][j] = read.nextDouble();
                }
            }

            System.out.println("The result is:");
            for (int i = 0; i < numRows1; i++) {
                double num = 0;
                for (int k = 0; k < numCols2; k++) {
                    for (int j = 0; j < numCols1; j++) {
                        num += matrix1[i][j] * matrix2[j][k];
                    }
                    System.out.print(df.format(num) + " ");
                    num = 0;
                }

                System.out.println();
            }

        }
        else {
            System.out.println("The operation cannot be performed.");
        }

        System.out.println();
        printMenu();
    }

    /**
     * Matrix Transposition
     */
    public static void transpose() {
        System.out.println();
        System.out.println("1. Main Diagonal\n2. Side Diagonal\n3. Vertical Line\n4. Horizontal Line");

        int option = read.nextInt();
        switch (option) {
            case 1: transMain(readMatrix(0));
                break;
            case 2: transSide(readMatrix(0));
                break;
            case 3: transVert(readMatrix(0));
                break;
            case 4: transHoriz(readMatrix(0));
                break;
        }

        System.out.println();
        printMenu();
    }

    public static void transMain(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[j][i]) + " ");
            }
            System.out.println();
        }
    }

    public static void transSide(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = matrix[0].length - 1; i >= 0 ; i--) {
            for (int j = matrix.length - 1; j >= 0 ; j--) {
                System.out.print(df.format(matrix[j][i]) + " ");
            }
            System.out.println();
        }
    }

    public static void transHoriz(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = matrix.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static void transVert(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length - 1; j >= 0 ; j--) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static void determinant(double[][] matrix) {

        System.out.println("The result is:\n" + determine(matrix, matrix.length));
        System.out.println();
        printMenu();
    }

    public static double determine(double[][] matrix, int size) {
        if (size <= 1) {
            return matrix[0][0];
        }
        else if (size == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }
        else {
            double num = 0;
            for (int i = 0; i < size; i++) {
                num += (matrix[0][i] * Math.pow(-1, i) * determine(subMatrix(matrix, 0, i, size - 1), size - 1));
            }
            return num;
        }
    }

    public static double[][] subMatrix(double[][] matrix, int row, int col, int size) {
        double[][] subMatrix = new double[size][size];
        int q = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            if (q == row) {
                q = i + 1;
            }
            for (int j = 0; j < size; j++) {
                if (j == col) {
                    k = j + 1;
                }
                subMatrix[i][j] = matrix[q][k];
                k++;
            }
            q++;
        }

        return subMatrix;
    }

    public static void printInverse(double[][] matrix) {
        int size = matrix.length;

        double det = determine(matrix, size);
        double[][] inverse = invert(matrix, (1 / det));
        if (det != 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(df.format(inverse[i][j]) + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("This matrix doesn't have an inverse.");
        }
        System.out.println();
        printMenu();
    }

    public static double[][] invert(double[][] matrix, double oneOverDet) {
        int size = matrix.length;
        double[][] adj = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adj[j][i] = oneOverDet * Math.pow(-1, (i + j)) *
                        determine(subMatrix(matrix, i, j, size - 1), size - 1);
            }
        }

        return adj;
    }

    /**
     * Prints the menu
     */
    public static void printMenu() {
        System.out.println("1. Add Matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n" +
                "4. Transpose matrix\n5. Calculate a determinant\n6. Inverse Matrix\n0. Exit");
        System.out.print("Your choice: ");
        int options = read.nextInt();
        switch (options) {
            case 1: add(readMatrix(1));
                break;
            case 2: multiplyByNum(readMatrix(0));
                break;
            case 3: matrixMultiplication(readMatrix(1));
                break;
            case 4: transpose();
                break;
            case 5: determinant(readMatrix(0));
                break;
            case 6: printInverse(readMatrix(0));
                break;
        }
    }

    /**
     * Reads a matrix
     * @param numMatrix is the matrix number we are gathering. 0 means 1 and only 1 matrix being read. 1 is the first
     *                  of at least 2 matrices. 2 is the second of at least 2, etc.
     * @return the matrix as an array
     */
    public static double[][] readMatrix(int numMatrix) {
        System.out.print("Enter size of " + (numMatrix == 0 ? "" : numMatrix == 1 ? "first " : "second ") + "matrix: ");
        int numRows = read.nextInt();
        int numCols = read.nextInt();

        double[][] matrix = new double[numRows][numCols];

        System.out.println("Enter " + (numMatrix == 0 ? "" : numMatrix == 1 ? "first " : "second ") + "matrix:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = read.nextDouble();
            }
        }

        return matrix;
    }
}
