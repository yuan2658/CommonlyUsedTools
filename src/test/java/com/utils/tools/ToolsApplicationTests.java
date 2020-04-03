package com.utils.tools;

import com.sun.jndi.toolkit.url.UrlUtil;
import com.utils.tools.pdfutils.PDFUtils;
import com.utils.tools.utils.Encodes;
import com.utils.tools.utils.UrlUtils;
import com.utils.tools.webutils.HttpUtils;
import com.utils.tools.webutils.ResponseUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
class ToolsApplicationTests {

    /**
     * pdf 指定位置添加签章
     */
    @Test
    void insertImgToPdf() throws Exception {
        File file1 = ResourceUtils.getFile("classpath:static/pdf/file/ceshi.pdf");
        File file2 = ResourceUtils.getFile("classpath:static/pdf/image/wangtie.png");
        File file3 = ResourceUtils.getFile("classpath:static/pdf/file/ceshiResult.pdf");
        String inputPdfPath = file1.getPath();
        String outputPdfPath = file3.getPath();
        String imgPath = file2.getPath();
        PDFUtils.insertImgToPdf(inputPdfPath,outputPdfPath,imgPath,100,700);
    }




    @Test
    void geturl() throws Exception {
        //请求接口地址
        String url ="http://cpdc.chinapost.com.cn/web/api.php?op=get_linkage&act=ajax_select&keyid=1&parent_id="+0;
        String jsonByUrl = UrlUtils.getJsonByUrl(url);
        System.out.println(jsonByUrl);
    }


    /**
     *  ppt的复制
     * @throws Exception
     */
    @Test
    void copyPPT() throws Exception {
        //复制ppt 如果ppt中有图表的话需要使用4.0版本以上的poi  3.X版本只能复制幻灯片里的文字和表格
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        File file1 = ResourceUtils.getFile("classpath:static/ppt/template.pptx");
        File file2 = ResourceUtils.getFile("classpath:static/ppt/test.pptx");
        XMLSlideShow xmlSlideShow1 = new XMLSlideShow(new FileInputStream(file1));
        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(file2));
        List<XSLFPictureData> pictureData = xmlSlideShow.getPictureData();
        for (int i = 0; i < pictureData.size(); i++) {
            XSLFPictureData xslfPictureData = pictureData.get(i);
            PictureData.PictureType type = xslfPictureData.getType();
            if(PictureData.PictureType.WDP.equals(type)){
                System.out.println(type);
                // xslfPictureData.;
            }
        }
        List<XSLFSlide> slides = xmlSlideShow.getSlides();
        for (XSLFSlide slide : slides) {
            xmlSlideShow1.createSlide().importContent(slide);
        }
        FileOutputStream out = new FileOutputStream(new File("D:/aa.pptx"));
        xmlSlideShow1.write(out);
        out.close();
    }
}
