package com.mingyuan.summer.test;

public class Test {

    public static void main(String[] args) {

        StringBuffer a = new StringBuffer("1");
        StringBuffer b = new StringBuffer("2");

        operate(a, b);

        System.out.println(a + "," + b);

    }

    private static void operate(StringBuffer x, StringBuffer y) {

        x.append(y);
        y = x;

        System.out.println(x);
        System.out.println(y);
    }


}
