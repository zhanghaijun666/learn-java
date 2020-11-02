package com.example.algorithms;

import static com.example.algorithms.SortTestHelper.*;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/2 19:23
 * 希尔排序 —— 插入排序的延伸，不稳定，最差 O(n^2)
 * 将插入排序中的步长 1 换成一个增量序列
 */
public class D_ShellSort {
    public static void sort(int[] data) {
        // 计算增量序列: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;
        while (h < data.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < data.length; i++) {
                int x = data[i];
                int j;
                for (j = i; j >= h && x < (data[j - h]); j = j - h) {
                    data[j] = data[j - h];
                }
                data[j] = x;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int[] data1 = generateRandomArray(10000, 1, 10000);
        testSort(D_ShellSort.class, data1);
    }

}
