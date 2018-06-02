package com.mingyuan.summer.tool;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GeneratePDF {


    // 利用模板生成pdf
    public static void fillTemplate(List<String> str, String orderId) {


        BaseFont baseFont = null;
        try {
//            baseFont = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


        // 模板路径
        String templatePath = PropertiesUtil.getProperties("pdf_path_template") + "temp.pdf";


        String file_path = PropertiesUtil.getProperties("pdf_path");
        File file = new File(file_path);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
        }
        // 生成的新文件路径
        String newPDFPath = file_path + orderId + ".pdf";
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

//                form.setField(s, str[i++]);
                form.setField(s, str.get(i++));
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


}
