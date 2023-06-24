package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Это калькулятор-тестовое задание для компании JazzTeam" +
                "в нём реализованы следующие функции. Существует класс для перевод и подсчета" +
                "значений в польскую запись. Функции: log,sqrt,^,-,+,*,/)" +
                "после каждого символа требуется поставить пробел с учетом разделения строки");
        String expression = "( 27 * 2 )";
        PolishNotation str = new PolishNotation();
        String polishNotation = str.toPolishNotation(expression);
        System.out.println("Польская запись:" + polishNotation);
        double result = str.calculate(polishNotation);
        System.out.println("Подсчет выражения:" + result);
    }
}