package com.example.algorithms;

import static com.example.algorithms.SortTestHelper.*;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/2 19:18
 * 冒泡排序 O(n^2)
 * 循环 n 趟，每一趟依次比较每个元素与下一个元素的大小，把最大的元素“沉”到最后
 */
public class A_BubbleSort {

    public static void main(String[] args) {
        int[] data = generateRandomArray(10, 1, 10000);
        printArray(data);
        testSort(A_BubbleSort.class, data);
        printArray(data);
    }

    public static void sort(int[] data) {
        // 循环 n 趟
        for (int i = 0; i < data.length; i++) {
            // 每一趟，下标从 1 开始递增，依次与前一个元素比较大小，大的交换到后面
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1]>(data[j])) {
                    swap(data, j - 1, j);
                }
            }
        }
    }
}
