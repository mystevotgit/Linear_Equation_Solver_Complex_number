package solver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(String imaginaryNumber) {
        Pattern pattern = Pattern.compile("((?:-|\\+)?\\d+(?:\\.\\d*)?)((?:-|\\+)\\d+\\.?\\d*i)");
        Matcher m = pattern.matcher(imaginaryNumber);
        if(m.matches()){
            real = Double.parseDouble(m.group(1));
            imaginary = Double.parseDouble(m.group(2).substring(0, m.group(2).length() - 1));
        }else{
            if(imaginaryNumber.contains("i")) {
                real = 0.0;
                System.out.println(imaginaryNumber);
                imaginary = Double.parseDouble(imaginaryNumber.substring(0, imaginaryNumber.length() - 1));
            }else{
                imaginary = 0.0;
                real = Double.parseDouble(imaginaryNumber);
            }
        }
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = Math.round(real * 10000.0) / 10000.0;
        this.imaginary = Math.round(imaginary * 10000.0) / 10000.0;
    }

    public double getImaginary() {
        return imaginary;
    }


    public double getReal() {
        return real;
    }

    public String toString() {
        return "ComplexNumber{real=" + real + ",imaginary=" + imaginary + "}";
    }

    public String  getComplexNumber() {
        if(imaginary == 0.0) {
            return Double.toString(real);
        }
        if(real == 0.0) {
            return Double.toString(imaginary) + "i";
        }
        return imaginary > 0.0 ? real + "+" + imaginary + "i" : real + "" + imaginary + "i";
    }

}
