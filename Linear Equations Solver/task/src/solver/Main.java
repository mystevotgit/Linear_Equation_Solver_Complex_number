package solver;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        String input = args[0];
        String outputFile = args[1];
        Matrix matrix = getValues(input);
        LinearEquation equation = new LinearEquation(matrix, outputFile);
        Command solveEquation = new GetResultCommand(equation);
        solveEquation.execute();
    }



    public static Matrix getValues(String in) {
        File inputFile = new File(in);
        ComplexNumber[] arr;
        try( Scanner scanner = new Scanner(inputFile);){
            int numVariable = scanner.nextInt();
            int numEquation = scanner.nextInt();
            Matrix matrix = new Matrix(numEquation);
            arr = new ComplexNumber[numVariable + 1];

            for (int i = 0; i < numEquation; i++) {
                for (int j = 0; j <= numVariable; j++) {
                    arr[j] = new ComplexNumber(scanner.next());
                }
                matrix.setMatrixRow(new Row(Arrays.copyOf(arr, numVariable + 1)), i);
                if(scanner.hasNext()) {
                    scanner.next();
                }
            }
            return matrix;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

















