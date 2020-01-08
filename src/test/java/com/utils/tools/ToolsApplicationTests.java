package com.utils.tools;

import com.sun.jndi.toolkit.url.UrlUtil;
import com.utils.tools.pdfutils.PDFUtils;
import com.utils.tools.utils.UrlUtils;
import com.utils.tools.webutils.HttpUtils;
import com.utils.tools.webutils.ResponseUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

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

}
