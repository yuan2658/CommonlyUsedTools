package com.utils.tools;

import com.utils.tools.PDFUtils.PDFUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
class ToolsApplicationTests {

    /**
     * pdf 指定位置添加签章
     */
    @Test
    void contextLoads() {
        PDFUtils.insertImgToPdf("","","",0,0);
    }

}
