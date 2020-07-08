package solver;

public class ComplexMath {


    public static ComplexNumber addNumbers(ComplexNumber number1, ComplexNumber number2) {
        double real1 = number1.getReal();
        double imaginary1 = number1.getImaginary();
        double real2 = number2.getReal();
        double imaginary2 = number2.getImaginary();

        double real = real1 + real2;
        double imaginary = imaginary1 + imaginary2;
        return new ComplexNumber(real, imaginary);
    }

    public static ComplexNumber subtractNumbers(ComplexNumber number1, ComplexNumber number2) {
        double real1 = number1.getReal();
        double imaginary1 = number1.getImaginary();
        double real2 = number2.getReal();
        double imaginary2 = number2.getImaginary();

        double real = real1 - real2;
        double imaginary = imaginary1 - imaginary2;
        return new ComplexNumber(real, imaginary);
    }

    public static ComplexNumber inverseNumbers(ComplexNumber number1) {
        double real = number1.getReal();
        double imaginary = number1.getImaginary();

        return new ComplexNumber(real * -1, imaginary * -1);
    }


    public static ComplexNumber multiplyNumbers(ComplexNumber number1, ComplexNumber number2) {
        double real1 = number1.getReal();
        double imaginary1 = number1.getImaginary();
        double real2 = number2.getReal();
        double imaginary2 = number2.getImaginary();
        //(a+bi)(c+di)
        //(ac − bd) + (ad + bc)i
        double real = (real1 * real2) - (imaginary1 * imaginary2);
        double imaginary = (real1 * imaginary2) + (imaginary1 * real2);
        System.out.println(real + " " + imaginary);
        return new ComplexNumber(real, imaginary);
    }

    public static ComplexNumber divideNumbers(ComplexNumber number1, ComplexNumber number2) {
        double real1 = number1.getReal();
        //double imaginary1 = number1.getImaginary();
        double real2 = number2.getReal();
        double imaginary2 = number2.getImaginary();
        ComplexNumber congugate2 = new ComplexNumber(real2, (-1.0 * imaginary2));
        double multipleOfConjugate2 = (real2 * real2) + (imaginary2 * imaginary2);
        ComplexNumber multipleOfNum1AndNum2 = ComplexMath.multiplyNumbers(number1, congugate2);
        double real = multipleOfNum1AndNum2.getReal() / multipleOfConjugate2;
        double imaginary = multipleOfNum1AndNum2.getImaginary() / multipleOfConjugate2;
        return new ComplexNumber(real, imaginary);
    }

}
