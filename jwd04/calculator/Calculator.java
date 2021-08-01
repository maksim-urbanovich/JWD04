package com.urb.maximus.jwd04.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Calculator {
    static Scanner scanner = new Scanner(System.in);
    static final String GREETING = "Welcome to Basic Calculator";
    static final String OPERATIONS = "(+, -, *, /, pow, sqrt)";

    public static void main(String[] args) {
        String operation;
        double a;
        double b = 0;
        double result;
        BigDecimal resultBigDecimal;

        greeting();
        while (true) {
            showMenu();
            operation = getOperation();
            checkExit(operation);
            a = getNumber();
            if (!operation.equals("sqrt")) {
                b = getNumber();
            }
            result = calculate(operation, a, b);
            resultBigDecimal = preciseResult(result);
            printResult(resultBigDecimal);
        }

    }

    public static void greeting () {
        System.out.println(GREETING);
    }

    public static void showMenu() {
        System.out.println("Choose an operation: " + OPERATIONS + " or q - quit");
    }

    public static String getOperation() {
        String operation;

        if (scanner.hasNext()) {
            operation = scanner.next();
        }
        else {
            System.out.println("Incorrect operation. Try again, please.");
            scanner.next();
            operation = getOperation();
        }

        return operation;
    }

    static void checkExit(String operation) {
        if (operation.equals("q")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }

    public static double getNumber() {
        System.out.print("Enter a number: ");
        double number;
        if (scanner.hasNextDouble()) {
            number = scanner.nextDouble();
        }
        else {
            System.out.println("This is not a number. Try again, please.");
            scanner.next();
            number = getNumber();
        }
        return number;
    }

    public static double calculate(String operation, double a, double b) {
        double result = 0.0;
        switch (operation) {
            case "+":
                result = sum(a, b);
                break;
            case "-":
                result = dif(a, b);
                break;
            case "*":
                result = mult(a, b);
                break;
            case "/":
                result = div(a, b);
                break;
            case "pow":
                result = pow(a, b);
                break;
            case "sqrt":
                result = sqrt(a);
                break;
            default:
                System.out.println("Unknown operation");
                break;
        }
        return result;
    }

    public static double sum(double a, double b) {
        return  a + b;
    }

    public static double dif(double a, double b) {
        return  a - b;
    }

    public static double mult(double a, double b) {
        return  a * b;
    }

    public static double div(double a, double b) {
        double res = 0;
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        res = a / b;
        return  res;
    }

    public static double pow(double a, double b) {
        return  Math.pow(a, b);
    }

    public static double sqrt(double a) {
        double res = 0;
        if (a < 0) {
            System.out.println("Negative number for sqrt");
            }
        else {
            res = Math.sqrt(a);
        }
        return  res;
    }

    public static BigDecimal preciseResult(double result) {
        BigDecimal bigDecimal = new BigDecimal(result);
        return bigDecimal.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public static void printResult(BigDecimal resultBigDecimal) {
        System.out.println(resultBigDecimal);
    }
}
