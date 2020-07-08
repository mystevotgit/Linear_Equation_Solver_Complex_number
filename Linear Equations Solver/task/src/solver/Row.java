package solver;

import java.util.Arrays;

public class Row {
    private ComplexNumber[] row;
    Row(ComplexNumber[] arr) {
        row = arr;
    }

    Row(Row row) {
        this.row = new ComplexNumber[row.length()];
        for (int i = 0; i < row.length(); i++) {
            this.row[i] = row.getValue(i);
        };
    }

    public void multiplyRow(ComplexNumber num) {
        for (int i = 0; i < row.length; i++) {
            row[i] = ComplexMath.multiplyNumbers(row[i] , num);
        }
    }

    public int length(){
        return row.length;
    }

    public ComplexNumber getValue(int index) {
        return row[index];
    }

    public void addToRow(Row row) {
        for (int i = 0; i < this.row.length; i++) {
            this.row[i] = ComplexMath.addNumbers(row.getValue(i), this.row[i]);
        }
    }

    public void normaliseRow(int index) {
        ComplexNumber divisor = row[index];
        for (int i = 0; i < row.length; i++) {
            row[i] = ComplexMath.divideNumbers(row[i], divisor);
        }
    }

    public boolean isAllZeros() {
        for (ComplexNumber number: row) {
            if(number.getReal() != 0.0 && number.getImaginary() != 0.0){
                return false;
            }
        }
        return true;
    }

    public  void setRowValue(ComplexNumber value, int index) {
        row[index] = value;
    }

    public void displayRow() {
        System.out.println(Arrays.toString(row));
    }

}












