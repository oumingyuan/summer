package com.mingyuan.summer.test;

public class MyBoolean {

    /**
     * 0-100
     * <p>
     * 0-59   不及格
     * 60-79   合格
     * 80-100 优秀
     *
     * @param args
     */

    public static void main(String[] args) {

        int score = 78;

        if (score >= 0 && score < 60) {
            System.out.println("不及格");
        }
        if (score >= 60 && score < 80) {
            System.out.println("合格");
        }
        if (score >= 80 && score <= 100) {
            System.out.println("优秀");
        }
        if (score < 0 || score > 100) {
            System.out.println("分数异常");
        }


    }


}
