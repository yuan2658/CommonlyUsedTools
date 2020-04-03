package com.utils.tools.excelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description: 遍历excel数据
 * @Author: yy
 * @Date: Created in 2020/4/3 9:04
 */
public class ExcelUtils1 {

    public static void main(String[] args) {

        try {
            File file1 = ResourceUtils.getFile("classpath:static/excel/test.xlsx");
            XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(file1));
            // XSSFSheet sheet = excel.getSheet("政策主体树选项");
            // XSSFSheet sheet = excel.getSheet("内部通用文件树");
            XSSFSheet sheet = excel.getSheet("文章树选项");
            //总行数
            int rownum = sheet.getPhysicalNumberOfRows();
            getData(sheet,0,rownum,0);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @Description: 获取当前cell合并的行数
     * @author: drj
     * @date: 2019/5/22 18:00
     */
    public static int getMergeRowNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell)) {
                //获取合并的行数
                mergeSize = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                break;
            }
        }
        return mergeSize;
    }


    /**
     * 获取下一级合并单元格的行数
     * @param sheet 表
     * @param rowNum1  单元格开始行
     * @param rowNum2  单元格结束行
     * @param cellNum  第几列
     * @return
     */
    public static int getCount(XSSFSheet sheet, int rowNum1, int rowNum2,int cellNum){
        int i =1;
        //第一列
        XSSFRow row = sheet.getRow(rowNum1);
        XSSFCell cell = row.getCell(cellNum);
        //合并单元格个数
        int mergeRowNum1 = getMergeRowNum(cell, sheet);
        int mergeRowNum = mergeRowNum1+rowNum1;
        while (mergeRowNum<rowNum2){
            i++;
            XSSFRow row1 = sheet.getRow(mergeRowNum);
            XSSFCell cell2 = row1.getCell(cellNum);
            mergeRowNum += getMergeRowNum(cell2,sheet);
        }
        return i;
    }

    /**
     * 遍历获取数据
     * @param sheet 工作簿
     * @param r 单元格起始行
     * @param rownum 总行数
     * @param layer 	从0开始 每层递加1
     */
    public static void getData(XSSFSheet sheet,int r,int rownum,int layer){
        int count = getCount(sheet, r, rownum,layer);
        if(count>1){
            for (int i=1;i<=count;i++) {
                XSSFRow row = sheet.getRow(r);
                XSSFCell cell = row.getCell(layer);
                //获取该单元格占多少行
                int mergeRowNum = getMergeRowNum(cell, sheet);
                System.out.println("当前单元格=" + cell.getStringCellValue() + "=下级有" + mergeRowNum + "层，当前是第" + r + "行");
                //第二层
                if (mergeRowNum > 1) {
                    getData(sheet, r,r + mergeRowNum, layer + 1);
                }
                r += mergeRowNum;
            }
        }
    }



}

// int count = getCount(sheet, 0, rownum,0);
// //第一层
// int r =0;
// int k =0;
// for (int i=1;i<=count;i++){
// 	XSSFRow row = sheet.getRow(r);
// 	XSSFCell cell = row.getCell(0);
// 	//获取该单元格占多少行
// 	int mergeRowNum = getMergeRowNum(cell, sheet);
// 	System.out.println("正在获取当前单元格="+cell.getStringCellValue()+"=下级有"+mergeRowNum+ "层，当前是第"+r+"行");
// 	//第二层
// 	if(mergeRowNum>1){
// 		//获取第二层有几行
// 		int count1 = getCount(sheet, r,  r+mergeRowNum,1);
// 		if (count1>1) {
// 			System.out.println(cell.getStringCellValue()+"下有"+count1+"层");
// 			for (int j=1;j<=count1;j++){
// 				XSSFRow row1 = sheet.getRow(k);
// 				XSSFCell cell1 = row1.getCell(1);
// 				int mergeRowNum1 = getMergeRowNum(cell1, sheet);
// 				System.out.println("正在获取当前单元格="+cell1.getStringCellValue()+"=下级有"+mergeRowNum1+ "层，当前是第"+k+"行");
// 				k+=mergeRowNum1;
// 			}
// 		}
// 	}else {
// 		k++;
// 	}
// 	r+=mergeRowNum;
// }
