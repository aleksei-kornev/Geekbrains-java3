package ru.geekbrains;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws Exception {
        Tester tester = new Tester();
        tester.start(TestCase.class);
    }


}
