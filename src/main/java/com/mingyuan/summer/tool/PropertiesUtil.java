package com.mingyuan.summer.tool;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesUtil {





    /**
     * @param key key
     * @return value
     */

    /*
    public static String getProperties(String key) {


        Properties properties = new Properties();

        try {
            FileInputStream fis;

            Properties props = System.getProperties(); //获得系统属性集
            String osName = props.getProperty("os.name"); //操作系统名称

            if ("Windows 10".equals(osName)) {
                fis = new FileInputStream("src/main/resources/properties/config.properties");
            } else {
                fis = new FileInputStream("src/main/resources/properties/prd_config.properties");
            }


            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);


    }


    public static void main(String[] args) {

        System.out.println(getProperties("pdf_path"));

    }

    */


}
