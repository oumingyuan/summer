package com.mingyuan.summer.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Max {

    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();

        list.add(1210);
        list.add(1213);
        list.add(12122);
        list.add(1219);

        list.add(1120);
        list.add(1213);
        list.add(1212);
        list.add(119);

        System.out.println(Collections.max(list));


    }


    /**
     * 计算最大值
     *
     * @param a 第一个值
     * @param b 第二个值
     * @param c 第三个值
     * @return 最大的值
     */
    static int findMax(int a, int b, int c) {
        int m;


        if (a > b) {
            m = a;

            if (m > c) {
                m = m;
            } else {
                m = c;
            }

        } else {
            m = b;
            if (m > c) {
                m = m;
            } else {
                m = c;
            }
        }


        return m;
    }


}
