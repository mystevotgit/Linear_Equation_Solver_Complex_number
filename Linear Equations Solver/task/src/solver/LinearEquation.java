package solver;
import java.io.PrintWriter;


public class LinearEquation {
    private Matrix matrix;
    private String outputFile;
    LinearEquation(Matrix matrix, String outputFile) {
        this.matrix = matrix;
        this.outputFile = outputFile;
    }

    public void resolveEquation() {
        String result = "";
        matrix.reduceMatrixDownWards();
        if(matrix.noSolution()) {
            result = "No Solution";
        } else if(matrix.manySolutions()) {
            result = "Infinitely many solutions";
        } else {
            matrix.reduceMatrixUpwards();
            matrix.undoSwap();
            result = matrix.getResult();
        }
        try(PrintWriter printer = new PrintWriter(outputFile)) {
               printer.println(result);
        }catch (Exception e) {
            System.out.printf("An error occurred %s", e.getMessage());
        }
    }
}