package com.utils.tools.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @Description: 上传文件
 * @Author: yy
 * @Date: Created in 2020/1/8 15:46
 * @Modified By：
 */
public class FileUpload {

    private static Logger logger = LoggerFactory.getLogger(FileUpload.class);

    /**
     *  上传文件
     * @param file 文件
     * @param request 请求
     * @param url 上传路径
     * @return
     */
    public String saveFile(MultipartFile file, HttpServletRequest request, String url){
        //保存的路径
        String path = request.getSession().getServletContext().getRealPath(url)+"/";
        //创建文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        path += ymd + "/";
        url += ymd + "/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            //生成新文件名称
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            String fileUrl = url + newFileName;
            try {
                FileCopyUtils.copy(file.getBytes(), new File(path +"/" +  newFileName));
            }catch (IOException e) {
                e.printStackTrace();
            }
            return fileUrl;
        }
        return null;
    }

}
