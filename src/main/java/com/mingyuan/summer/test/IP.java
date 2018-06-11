package com.mingyuan.summer.test;


public class IP {


    public static void main(String[] args) {


        int score = 98;

        if (score < 0) {
            System.out.println("违法");
        } else if (score < 60) {
            System.out.println("不及格");
        } else if (score < 80) {
            System.out.println("合格");
        } else if (score <= 100) {
            System.out.println("优秀");
        } else if (score > 100) {
            System.out.println("违法");
        }


    }
}
