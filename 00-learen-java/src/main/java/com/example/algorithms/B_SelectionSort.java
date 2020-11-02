package com.example.algorithms;

import static com.example.algorithms.SortTestHelper.*;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/2 19:22
 * 选择排序 O(n^2)
 * i 从 0 开始递增，以第 i 个元素为最小，从 i+1 开始找出比该值更小的元素，与之交换位置。
 * 重要性质：交换次数最少 O(n)
 */
public class B_SelectionSort {
    public static void sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            // 假设第 i 个元素为最小
            int minIndex = i;
            // 从 i+1 开始找出比该值更小的元素，与之交换位置。
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < (data[minIndex])) {
                    minIndex = j;
                }
            }
            swap(data, i, minIndex);
        }
    }

    public static void main(String[] args) {
        int[] data = generateRandomArray(10000, 1, 10000);
        printArray(data);
        testSort(B_SelectionSort.class, data);
        printArray(data);
    }
}
