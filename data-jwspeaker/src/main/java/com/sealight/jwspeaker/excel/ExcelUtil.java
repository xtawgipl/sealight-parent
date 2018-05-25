package com.sealight.jwspeaker.excel;

import com.sealight.jwspeaker.constants.Constant;
import com.sealight.jwspeaker.entity.LampBean;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * excel表格工具类
 *
 * @author zhangjj
 * @create 2017-12-11 21:25
 **/
public class ExcelUtil {

    public static void excelExport(String sheelName, List<LampBean> lampList) {

        /** 表头占的行数*/
        int headRow = 1;

        // 创建excel
        XSSFWorkbook wb = new XSSFWorkbook();

        // 创建sheet
        XSSFSheet sheet = wb.createSheet(sheelName);
//        sheet.setDefaultColumnWidth(10);
        sheet.setDefaultRowHeight((short) 600);

        // 创建一行
        XSSFRow rowTitle = sheet.createRow(0);

        // 创建标题栏样式
        XSSFCellStyle styleTitle = wb.createCellStyle();
        styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        styleTitle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        styleTitle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);
        styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        XSSFFont fontTitle = wb.createFont();
        // 宋体加粗
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontName("宋体");
        fontTitle.setFontHeight((short) 200);
        styleTitle.setFont(fontTitle);
        styleTitle.setWrapText(true);


        XSSFCell cellTitle = null;

        cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue("model");
        cellTitle.setCellStyle(styleTitle);

        cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue("year");
        cellTitle.setCellStyle(styleTitle);

        cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("lampName");
        cellTitle.setCellStyle(styleTitle);

        cellTitle = rowTitle.createCell(3);
        cellTitle.setCellValue("lampDesc");
        cellTitle.setCellStyle(styleTitle);


        XSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        styleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        styleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        styleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        styleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        CellRangeAddress cra ;
        int rowStart = headRow;//合并单元格的行起始index
        int rowStart1 = headRow;//合并单元格的行起始index
        // 取数据
        int i = 0;
        for (; i < lampList.size(); i++) {
            LampBean item = lampList.get(i);
            XSSFRow row = sheet.createRow(i + headRow);

            XSSFCell cell = row.createCell(0);
            if(i != 0 && item.getModel().equals(lampList.get(i - 1).getModel())){
                cell.setCellValue("");
            }else{
                if(rowStart < i + headRow){
                    /*
                     * 设定合并单元格区域范围
                     *  firstRow  0-based
                     *  lastRow   0-based
                     *  firstCol  0-based
                     *  lastCol   0-based
                     */
                    cra = new CellRangeAddress(rowStart, i + headRow - 1, 0, 0);
                    //在sheet里增加合并单元格
                    sheet.addMergedRegion(cra);
                    rowStart = i + headRow;
                }
                cell.setCellValue(item.getModel());
            }
            cell.setCellStyle(styleCenter);


            cell = row.createCell(1);
            if(i != 0 && item.getYear().equals(lampList.get(i - 1).getYear())){
                cell.setCellValue("");
            }else{
                if(rowStart1 < i + headRow){
                    /*
                     * 设定合并单元格区域范围
                     *  firstRow  0-based
                     *  lastRow   0-based
                     *  firstCol  0-based
                     *  lastCol   0-based
                     */
                    cra = new CellRangeAddress(rowStart1, i + headRow - 1, 1, 1);
                    //在sheet里增加合并单元格
                    sheet.addMergedRegion(cra);
                    rowStart1 = i + headRow;
                }
                cell.setCellValue(item.getYear());
            }
            cell.setCellStyle(styleCenter);


            cell = row.createCell(2);
            cell.setCellValue(item.getLampName());
            cell.setCellStyle(styleCenter);

            cell = row.createCell(3);
            cell.setCellValue(item.getLampDesc());
            cell.setCellStyle(styleCenter);
        }

        if(rowStart < i + headRow){
                    /*
                     * 设定合并单元格区域范围
                     *  firstRow  0-based
                     *  lastRow   0-based
                     *  firstCol  0-based
                     *  lastCol   0-based
                     */
            cra = new CellRangeAddress(rowStart, i + headRow - 1, 0, 0);
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
        }

        if(rowStart1 < i + headRow){
                    /*
                     * 设定合并单元格区域范围
                     *  firstRow  0-based
                     *  lastRow   0-based
                     *  firstCol  0-based
                     *  lastCol   0-based
                     */
            cra = new CellRangeAddress(rowStart1, i + headRow - 1, 1, 1);
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
        }

        sheet.autoSizeColumn(3);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(Constant.EXCEL_PATH + sheelName+".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fout != null){
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(sheelName + " 表生成完成");
    }


}
