package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Matrix {
    private Row[] matrix;
    private ArrayList<Map> refactorOperations = new ArrayList<Map>();
    private int numVariables;

    Matrix( int variables) {
        matrix = new Row[variables];
        numVariables = variables ;
    }

    public void setMatrixRow(Row row, int index) {
        matrix[index] = row;
    }

    public void displayMatrix() {
        for(Row row: matrix) {
            row.displayRow();
        }
    }

    private void swap(Map operation) {
        Integer layer1;
        Integer layer2;
        Integer operationType = (Integer) operation.get("Type");

        switch (operationType){
            case 1:
                layer1 = (Integer) operation.get("Row1");
                layer2 = (Integer) operation.get("Row2");
                swapRow(layer1, layer2);
                return;
            case 2:
                layer1 = (Integer) operation.get("Col1");
                layer2 = (Integer) operation.get("Col2");
                swapCol(layer1, layer2);
                return;
            default: return;
        }
    }

    private void reverseSwap(Map operation){
        Integer layer1 = (Integer) operation.get("Col1");;
        Integer layer2 = (Integer) operation.get("Col2");
        swapRow(layer1, layer2);
        swapCol(layer1, layer2);
    }

    private void swapRow(int row1Index , int row2Index) {
        Row temp = matrix[row1Index];
        matrix[row1Index] = matrix[row2Index];
        matrix[row2Index] = temp;
    }

    private void swapCol(int col1Index, int col2Index) {
        for (int i = 0; i < matrix.length; i++) {
            ComplexNumber tempValue1 = matrix[i].getValue(col1Index);
            ComplexNumber tempValue2 = matrix[i].getValue(col2Index);
            matrix[i].setRowValue(tempValue2, col1Index);
            matrix[i].setRowValue(tempValue1, col2Index);
        }
    }


    private void refactorMatrix(int indexI, int indexJ){
        Map<String, Integer> operation;


        // transverse down the column
        for (int i = indexI + 1; i < matrix.length; i++) {
            if(matrix[i].getValue(indexI).getImaginary() != 0 && matrix[i].getValue(indexI).getReal() != 0) {
                operation = new HashMap<String, Integer>();
                operation.put("Type", 1);
                operation.put("Row1", indexI);
                operation.put("Row2", i);
                swap(operation);
                return;
            }
        }
        // transverse across row

        for (int j = indexJ; j < matrix[indexI].length() - 1; j++) {
            if(matrix[indexI].getValue(j).getImaginary() != 0 && matrix[indexI].getValue(j).getReal() != 0) {
                operation = new HashMap<String, Integer>();
                operation.put("Type", 2);
                operation.put("Col1", indexJ);
                operation.put("Col2", j);
                refactorOperations.add(operation);
                swap(operation);
                return;
            }
        }

        // transverse diagonally
        for (int j = indexJ + 1; j < matrix[j].length() -1; j++) {
            for (int i = indexI + 1; i < matrix.length; i++) {
                if(matrix[i].getValue(j).getImaginary() != 0 && matrix[i].getValue(j).getReal() != 0) {
                    operation = new HashMap<String, Integer>();
                    operation.put("Type", 2);
                    operation.put("Col1", indexJ);
                    operation.put("Col2", j);
                    refactorOperations.add(operation);
                    swap(operation);
                    operation = new HashMap<String, Integer>();
                    operation.put("Type", 1);
                    operation.put("Row1", indexI);
                    operation.put("Row2", i);
                    swap(operation);
                    return;
                }
            }
        }

    }

    public void reduceMatrixDownWards() {
        ComplexNumber multiplier;
        // reduce matrix downwards
        for (int i = 0; i < numVariables; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i == j && matrix[i].getValue(j).getReal() == 0 && matrix[i].getValue(j).getImaginary() == 0) {
                    refactorMatrix(i,j);
                }
                if(i == j) {
                    matrix[i].normaliseRow(i);
                }
                Row indexRow = new Row(matrix[i]);

                if(i < j){
                    multiplier =  ComplexMath.divideNumbers(ComplexMath.inverseNumbers(matrix[j].getValue(i)), indexRow.getValue(i));
                    System.out.printf("%s * R%d + R%d -> R%d \n", multiplier.getComplexNumber(), i + 1, j + 1, j + 1);
                    indexRow.multiplyRow(multiplier);
                    matrix[j].addToRow(indexRow);
                }
            }
        }
    }

    public void reduceMatrixUpwards() {
        // reduce matrix upwards
        ComplexNumber multiplier, inverseNumber;

        for (int i = numVariables - 1; i >= 0; i--) {
            for (int j = numVariables - 1; j >= 0; j--) {
                if(i == j) {
                    matrix[i].normaliseRow(i);
                }
                Row indexRow = new Row(matrix[i]);
                if(i > j){
                    inverseNumber = ComplexMath.inverseNumbers(matrix[j].getValue(i));
                    multiplier = ComplexMath.divideNumbers(inverseNumber, indexRow.getValue(i)) ;
                    System.out.printf("%s * R%d + R%d -> R%d \n", multiplier.getComplexNumber(), i + 1, j + 1, j + 1);
                    indexRow.multiplyRow(multiplier);
                    matrix[j].addToRow(indexRow);
                }
            }
        }
    }



    public String getResult() {
        String result = "";
        ComplexNumber variable;
        for (int i = 0; i < numVariables; i++) {
            variable = matrix[i].getValue(numVariables);
            result+= variable.getComplexNumber() + "\n";
        }
        return result;
    }

    public void undoSwap() {
        for (int i = refactorOperations.size() - 1; i >= 0; i--) {
            reverseSwap(refactorOperations.get(i));
        }
    }

    public boolean noSolution() {
        System.out.println("NUMBERS OF VARIABLES IS " + numVariables + " " + matrix.length);
        if(numVariables == matrix.length){
            return false;
        }
        for (int i = numVariables; i < matrix.length; i++) {
            if(!matrix[i].isAllZeros()){
                return true;
            }
        }
        return false;
    }
    
    public boolean manySolutions() {
        for (int i = 0; i < numVariables; i++) {
            if(matrix[i].isAllZeros()) {
                return true;
            }
        }
        return false;
    }





}
