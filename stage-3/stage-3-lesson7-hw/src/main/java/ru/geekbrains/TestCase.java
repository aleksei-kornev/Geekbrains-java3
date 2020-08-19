package ru.geekbrains;

public class TestCase {
    @BeforeSuite
    public void init() {
        System.out.println("BeforSuite method");
    }

    @Test(priority = 3)
    public void test1() {
        System.out.println("test 1");
    }

    @Test(priority = 7)
    public void test2() {
        System.out.println("test 2");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("test 3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("test 4");
    }

    public void method() {
        System.out.println("method");
    }

    @AfterSuite
    public void stop() {
        System.out.println("AfterSuite method");
    }
}
