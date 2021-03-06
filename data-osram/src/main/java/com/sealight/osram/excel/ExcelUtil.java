package com.sealight.osram.excel;

import com.sealight.osram.constants.Constant;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * excel表格工具类
 *
 * @author zhangjj
 * @create 2017-12-11 21:25
 **/
@Slf4j
public class ExcelUtil {

    public static void excelExport(String sheelName, List<LampExcelBean> lampList) {

        /** 表头占的行数*/
        int headRow = 2;

        int index = 2;

        // 创建excel
        XSSFWorkbook wb = new XSSFWorkbook();

        // 创建sheet
        XSSFSheet sheet = wb.createSheet(sheelName);
        sheet.setDefaultColumnWidth(10);
        sheet.setDefaultRowHeight((short) 600);



        XSSFRow rowTitle = sheet.createRow(0);

        CellRangeAddress craTitle = null;
        XSSFCell cell = null;
        if(lampList != null && !lampList.isEmpty()){
            XSSFCellStyle styleTitle1 = wb.createCellStyle();
            styleTitle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleTitle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleTitle1.setFillBackgroundColor(HSSFColor.DARK_BLUE.index);
            craTitle = new CellRangeAddress(0, 0, index, index + lampList.get(0).getHeadLampMap().size() - 1);
            sheet.addMergedRegion(craTitle);
            cell = rowTitle.createCell(index);
            cell.setCellValue("前灯");
            cell.setCellStyle(styleTitle1);
        }

        XSSFCellStyle styleTitle2 = wb.createCellStyle();
        styleTitle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        styleTitle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        styleTitle2.setFillBackgroundColor(HSSFColor.DARK_GREEN.index);
        craTitle = new CellRangeAddress(0, 0, index + lampList.get(0).getHeadLampMap().size(),
                index + lampList.get(0).getHeadLampMap().size() + lampList.get(0).getInnerLampmap().size() - 1);
        sheet.addMergedRegion(craTitle);
        cell = rowTitle.createCell(index + lampList.get(0).getHeadLampMap().size());
        cell.setCellValue("内灯");
        cell.setCellStyle(styleTitle2);


        XSSFCellStyle styleTitle3 = wb.createCellStyle();
        styleTitle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        styleTitle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        styleTitle3.setFillBackgroundColor(HSSFColor.DARK_RED.index);
        craTitle = new CellRangeAddress(0, 0, index + lampList.get(0).getHeadLampMap().size() + lampList.get(0).getInnerLampmap().size(),
                index + lampList.get(0).getHeadLampMap().size() + lampList.get(0).getHeadLampMap().size() +  + lampList.get(0).getOuterLampMap().size() - 1);
        sheet.addMergedRegion(craTitle);
        cell = rowTitle.createCell(index + lampList.get(0).getHeadLampMap().size() + lampList.get(0).getInnerLampmap().size());
        cell.setCellValue("外灯");
        cell.setCellStyle(styleTitle3);


        rowTitle = sheet.createRow(1);

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
        cellTitle.setCellValue("type");
        cellTitle.setCellStyle(styleTitle);


        for(Map.Entry<String, String> headEntry : lampList.get(0).getHeadLampMap().entrySet()){
            cellTitle = rowTitle.createCell(index++);
            cellTitle.setCellValue(headEntry.getKey());
            cellTitle.setCellStyle(styleTitle);
        }
        for(Map.Entry<String, String> innerEntry : lampList.get(0).getInnerLampmap().entrySet()){
            cellTitle = rowTitle.createCell(index++);
            cellTitle.setCellValue(innerEntry.getKey());
            cellTitle.setCellStyle(styleTitle);
        }
        for(Map.Entry<String, String> outerEntry : lampList.get(0).getOuterLampMap().entrySet()){
            cellTitle = rowTitle.createCell(index++);
            cellTitle.setCellValue(outerEntry.getKey());
            cellTitle.setCellStyle(styleTitle);
        }


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
        try {
            for (; i < lampList.size(); i++) {
                System.out.println("i = " + i);

                LampExcelBean item = lampList.get(i);
                XSSFRow row = sheet.createRow(i + headRow);

                cell = row.createCell(0);
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
                if(i != 0 && item.getType().equals(lampList.get(i - 1).getType())){
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
                    cell.setCellValue(item.getType());
                }
                cell.setCellStyle(styleCenter);

                int col = 2;
                for(Map.Entry<String, String> headEntry : item.getHeadLampMap().entrySet()){
                    cell = row.createCell(col++);
                    cell.setCellValue(headEntry.getValue());
                    cell.setCellStyle(styleCenter);
                }
                for(Map.Entry<String, String> innerEntry : item.getInnerLampmap().entrySet()){
                    cell = row.createCell(col++);
                    cell.setCellValue(innerEntry.getValue());
                    cell.setCellStyle(styleCenter);
                }
                for(Map.Entry<String, String> outerEntry : item.getOuterLampMap().entrySet()){
                    cell = row.createCell(col++);
                    cell.setCellValue(outerEntry.getValue());
                    cell.setCellStyle(styleCenter);
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
            log.error("", e);
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
