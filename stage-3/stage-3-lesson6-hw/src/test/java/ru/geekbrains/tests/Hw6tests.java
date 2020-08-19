package ru.geekbrains.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.Hw6;

import java.util.Arrays;
import java.util.Collection;

//@RunWith(Parameterized.class)
public class Hw6tests {
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList(new Object[][][] {
//                {{1,2,4,1,7}, {1,7}},
//                {{1,2,4,1,7}, {1,7}},
//        });
//    }
//
//    int[] arr;
//    int[] expectedArr;
//
//    public Hw6tests (int[] expectedArr, int[] arr) {
//        System.out.printf("%d %d%n", expectedArr, arr);
//        this.expectedArr = expectedArr;
//        this.arr = arr;
//    }

    private static Hw6 hw6;

    @BeforeClass
    public static void init() {
        hw6 = new Hw6();
    }

    @Test
    public void getPartOfArraySuccessTest1() {
        Assert.assertArrayEquals(new int[]{1, 7}, hw6.getPartOfArray(new int[]{1, 2, 4, 1, 7}));
    }

    @Test
    public void getPartOfArraySuccessTest2() {
        Assert.assertArrayEquals(new int[]{1, 7}, hw6.getPartOfArray(new int[]{1, 2, 4, 4, 5, 4, 1, 7}));
    }

    @Test
    public void getPartOfArraySuccessTest3() {
        Assert.assertArrayEquals(new int[]{1, 7, 5}, hw6.getPartOfArray(new int[]{4, 1, 7}));
    }

    @Test
    public void checkArrFor1And41() {
        Assert.assertTrue(hw6.checkArrFor1And4(new int[]{4, 1}));
    }

    @Test
    public void checkArrFor1And42() {
        Assert.assertTrue(hw6.checkArrFor1And4(new int[]{1, 1, 1}));
    }

    @Test
    public void checkArrFor1And43() {
        Assert.assertTrue(hw6.checkArrFor1And4(new int[]{4, 4, 1}));
    }

    @Test
    public void checkArrFor1And44() {
        Assert.assertEquals(true, hw6.checkArrFor1And4(new int[]{4, 4, 4}));
    }

//   *** mass test version ***
//
//    @Test
//    public void getPartOfArraySuccessTest() {
//        Assert.assertArrayEquals(expectedArr, hw6.getPartOfArray(arr));
//    }
}
