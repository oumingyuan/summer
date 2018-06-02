package com.mingyuan.summer.text;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.ArrayUtils;


public class Snippet {

    /*

    // 利用模板生成pdf
    private static void fillTemplate(String[] str) {


        BaseFont baseFont = null;
        try {
//            baseFont = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


        // 模板路径
        String templatePath = "D:/temp.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:/temp_hello.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            int i = 0;
            for (String s : form.getFields().keySet()) {

                form.setFieldProperty(s, "textfont", baseFont, null);

                form.setField(s, str[i++]);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();

            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //公司信息
        String[] purchase = {"美国微软公司", "15609682037", "2018-05-17 20:48:00", "安徽蚌埠", "15555136971", "20180517204800"};

        //商品信息列表
        String[] line_info = {"001", "123456", "酸奶", "45", "50.00", "20", "1000.00", ""};
        String[] jj = ArrayUtils.addAll(line_info, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);
        jj = ArrayUtils.addAll(jj, line_info);

        //商品信息統計
        String[] line_end = {"", "", "", "", "", "10", "100", ""};

        //信息合并
        String[] data = ArrayUtils.addAll(purchase, jj);
        data = ArrayUtils.addAll(data, line_end);

        fillTemplate(data);
    }

    */
}
