package ru.geekbrains;

import java.io.DataOutputStream;

public class Main {
    private volatile static char currentChar = 'A';
    public static DataOutputStream out = null;

    public static void main(String[] args) throws InterruptedException {
        //задание №1
        Main monitor = new Main();
        Thread t1 = new Thread(() -> {
            printChar(monitor, 'A', 'B');
        });
        Thread t2 = new Thread(() -> {
            printChar(monitor, 'B', 'C');
        });
        Thread t3 = new Thread(() -> {
            printChar(monitor, 'C', 'A');
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    private static void printChar(Object monitor, char letter1, char letter2) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != letter1) {
                        monitor.wait();
                    }
                    System.out.print(currentChar);
                    currentChar = letter2;
                    monitor.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
