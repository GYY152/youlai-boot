package com.youlai.boot.utils.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gyy
 * @since 2025/3/15 20:25
 */
public class ExcelUtils {

    /**
     * 读取Excel文件并将其数据转为指定的实体类
     *
     * @param excelFile Excel文件
     * @param clazz     目标实体类
     * @param <T>       实体类类型
     * @return 转换后的实体类列表
     * @throws Exception 可能的异常
     */
    public static <T> List<T> readExcel(File excelFile, Class<T> clazz) throws Exception {
        List<T> resultList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个sheet页
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            // 跳过表头
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                T entity = clazz.getDeclaredConstructor().newInstance();
                int cells = row.getPhysicalNumberOfCells();

                for (int j = 0; j < cells; j++) {
                    Cell cell = row.getCell(j);
                    Field field = clazz.getDeclaredFields()[j];
                    field.setAccessible(true);

                    // 根据单元格的类型设置字段的值
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                field.set(entity, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    field.set(entity, cell.getDateCellValue());
                                } else {
                                    // 强制转换为int类型
                                    if (field.getType().equals(int.class)) {
                                        field.set(entity, (int) cell.getNumericCellValue());
                                    } else {
                                        field.set(entity, cell.getNumericCellValue());
                                    }                                }
                                break;
                            case BOOLEAN:
                                field.set(entity, cell.getBooleanCellValue());
                                break;
                            default:
                                field.set(entity, null);
                                break;
                        }
                    }
                }
                resultList.add(entity);
            }
        }

        return resultList;
    }
}
