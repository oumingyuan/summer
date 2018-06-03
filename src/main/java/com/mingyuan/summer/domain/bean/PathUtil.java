package com.mingyuan.summer.domain.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:properties/config.properties")//注意路径
public class PathUtil {


    public String getPdfPath() {

        String osName = System.getProperties().getProperty("os.name");

        if ("Windows 10".equals(osName)) {
            return testPdfPath;
        } else {

            return productPdfPath;

        }

    }


    public String getPdfPathTemplate() {
        String osName = System.getProperties().getProperty("os.name");

        if ("Windows 10".equals(osName)) {
            return testPdfPathTemplate;
        } else {

            return productPdfPathTemplate;

        }
    }


    @Value("${test.pdfPath}")
    private String testPdfPath;

    @Value("${test.pdfPathTemplate}")
    private String testPdfPathTemplate;

    @Value("${product.pdfPath}")
    private String productPdfPath;

    @Value("${product.pdfPathTemplate}")
    private String productPdfPathTemplate;


}
