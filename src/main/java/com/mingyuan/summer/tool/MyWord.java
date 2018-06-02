package com.mingyuan.summer.tool;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MyWord {

    public static void main(String[] args) {

//        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径

        //这是需要获取的文件夹路径
        String path = "C:\\Users\\oumin\\Desktop\\snow\\";
        List<String> name_list = getFile(path, 0);

//        System.out.println(name_list);
//        System.out.println(name_list.size());


        List<List<String>> list_list = new LinkedList<>();

        for (String name : name_list) {

            List<String> info_list = getElement(name);
            list_list.add(info_list);

            System.out.println(info_list);


        }

        System.out.println(list_list.size());


    }


    public static List<String> getElement(String name) {

//        String name = "安徽巧美滋有限公司.doc";


        String a = getInfoFromWord(name);

        String[] aa = a.split("\r");


        List<String> info_list = new LinkedList<>();

        for (String element : aa) {

            String element_new = StringUtils.trim(element); // abc


            if ("".equals(element_new)) {


            } else if ("\n".equals(element_new)) {


            } else {

                if (element_new.contains(" ")) {

                    element_new = element_new.replaceFirst(" ", "/");

                    element_new = element_new.replace(" ", "");


                }

                info_list.add(element_new);


            }

        }

//        System.out.println(info_list);


        return info_list;
    }


    private static String getInfoFromWord(String name) {


        String buffer = null;
        WordExtractor ex = null;
        try {
            InputStream is = new FileInputStream(new File("C:\\Users\\oumin\\Desktop\\snow\\" + name));
            ex = new WordExtractor(is);
            buffer = ex.getText();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (ex == null) {

                } else {
                    ex.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return buffer;

    }


    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    private static List<String> getFile(String path, int deep) {

        List<String> name_list = new LinkedList<>();

        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字
//                System.out.println(array[i].getName());
                name_list.add(array[i].getName());
                // 输出当前文件的完整路径
                // System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                // System.out.println(array[i].getPath());
            } else if (array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

//                System.out.println(array[i].getName());
                name_list.add(array[i].getName());
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(), deep + 1);
            }
        }
        return name_list;
    }


}
