package com.utils.tools;

import com.utils.tools.PDFUtils.PDFUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

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

}
