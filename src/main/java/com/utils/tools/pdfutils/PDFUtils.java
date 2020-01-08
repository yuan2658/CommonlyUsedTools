package com.utils.tools.pdfutils;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;

/**
 * @Description: pdf操作工具类
 * @Author: yy
 * @Date: Created in 2020/1/3 11:17
 * @Modified By：
 */
public class PDFUtils {



    /**
     * 插入单张图片
     *
     * @param inputPdfPath  要操作的pdf路径
     * @param outputPdfPath 输出的pdf路径
     * @param imgPath       添加的图片路径
     * @param locationX     x轴位置
     * @param locationY     y轴位置
     * @return
     */
    public static void insertImgToPdf(String inputPdfPath, String outputPdfPath, String imgPath, float locationX, float locationY) {
        try {
            PdfReader reader = new PdfReader(inputPdfPath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPdfPath));
            //获取pdf的第一页
            PdfContentByte overContent = stamper.getOverContent(1);
            Image image = Image.getInstance(imgPath);
            //图片缩放比例
            image.scalePercent(15);
            //左边距、底边距
            image.setAbsolutePosition(locationX, locationY);
            overContent.addImage(image);
            overContent.stroke();

            //关闭资源
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
