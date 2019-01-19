<<<<<<< HEAD
package cn.nju.edu.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2017/11/22.
 */
@Component
public class ExcelHelper {

    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    private File file;
    private Workbook workbook;

    public boolean openFile(File file) {
        try {
            String fileName = file.getName();
            workbook = null;

            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(XLS)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(XLSX)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
            this.file = file;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取指定 sheet
     * @param sheetIndex 第几个 sheet
     * @return 读取失败 --> 空的 List
     *         读取成功 --> 包含本 sheet 数据的 List
     */
    public List<List<String>> getSheet(int sheetIndex) {
        List<List<String>> sheetList = new ArrayList<>();
        if (workbook == null) {
            return sheetList;
        }
        Sheet sheetAt = workbook.getSheetAt(sheetIndex);
        int startRow = sheetAt.getFirstRowNum();
        int endRow = sheetAt.getLastRowNum();
        for (int i = startRow; i <= endRow; i++) {
            List<String> rowList = getRow(sheetIndex, i);
            sheetList.add(rowList);
        }
        return sheetList;
    }


    /**
     * 读取指定 sheet 的指定行
     * @param sheetIndex 第几个 sheet
     * @param rowIndex 读取第几行
     * @return 读取失败 --> 空的 List
     *         读取成功 --> 包含本行数据的 List
     */
    public List<String> getRow(int sheetIndex, int rowIndex) {
        List<String> rowList = new ArrayList<>(4);
        if (workbook == null) {
            return rowList;
        }
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        int start = row.getFirstCellNum();
        int end = row.getLastCellNum();
        for (int i = start; i < end; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                rowList.add(getStringCellValue(cell));
            }
        }
        return rowList;
    }

    /**
     * 如何使用 参见 main
     * 写入 excel
     * @param content  excel 内容
     * @param dir 生成 excel 的目录
     */
    public void writeExcel(List<String[]> content, String dir) {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            System.out.println("i " + i);
            String[] rowContent = content.get(i);
            for (int j = 0; j < rowContent.length; j++) {
                System.out.println(j + " " + rowContent[j]);
                Cell cell = row.createCell(j);
                cell.setCellValue(rowContent[j]);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(dir);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        this.file = null;
        this.workbook = null;
        return true;
    }

    private String getStringCellValue(Cell cell) {
        String value = null;
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            //把数字当成String来读，避免出现1读成1.0的情况
            case NUMERIC:
                cell.setCellType(CellType.STRING);
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    public static void main(String[] args) throws IOException {
        ExcelHelper excelHelper = new ExcelHelper();
        String[] a = new String[3];
        a[0] = "1";
        a[1] = "2";
        a[2] = "4";
        List<String[]> test = new ArrayList<>();
        test.add(a);
        excelHelper.writeExcel(test, "src/main/resources/Excel/score.xlsx");
    }
=======
package cn.nju.edu.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2017/11/22.
 */
@Component
public class ExcelHelper {

    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    private File file;
    private Workbook workbook;

    public Workbook openFile(File file) {
        try {
            String fileName = file.getName();
            workbook = null;

            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(XLS)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(XLSX)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
            this.file = file;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return workbook;
    }

    /**
     * 读取指定 sheet
     * @param sheetIndex 第几个 sheet
     * @return 读取失败 --> 空的 List
     *         读取成功 --> 包含本 sheet 数据的 List
     */
    public List<List<String>> getSheet(int sheetIndex) {
        List<List<String>> sheetList = new ArrayList<>();
        if (workbook == null) {
            return sheetList;
        }
        Sheet sheetAt = workbook.getSheetAt(sheetIndex);
        int startRow = sheetAt.getFirstRowNum();
        int endRow = sheetAt.getLastRowNum();
        for (int i = startRow; i <= endRow; i++) {
            List<String> rowList = getRow(sheetIndex, i);
            sheetList.add(rowList);
        }
        return sheetList;
    }


    /**
     * 读取指定 sheet 的指定行
     * @param sheetIndex 第几个 sheet
     * @param rowIndex 读取第几行
     * @return 读取失败 --> 空的 List
     *         读取成功 --> 包含本行数据的 List
     */
    public List<String> getRow(int sheetIndex, int rowIndex) {
        List<String> rowList = new ArrayList<>(4);
        if (workbook == null) {
            return rowList;
        }
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        int start = row.getFirstCellNum();
        int end = row.getLastCellNum();
        for (int i = start; i < end; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                rowList.add(getStringCellValue(cell));
            }
        }
        return rowList;
    }

    /**
     * 如何使用 参见 main
     * 写入 excel
     * @param content  excel 内容
     * @param dir 生成 excel 的目录
     */
    public void writeExcel(List<String[]> content, String dir) {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            System.out.println("i " + i);
            String[] rowContent = content.get(i);
            for (int j = 0; j < rowContent.length; j++) {
                System.out.println(j + " " + rowContent[j]);
                Cell cell = row.createCell(j);
                cell.setCellValue(rowContent[j]);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(dir);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        this.file = null;
        this.workbook = null;
        return true;
    }

    private String getStringCellValue(Cell cell) {
        String value = null;
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            //把数字当成String来读，避免出现1读成1.0的情况
            case NUMERIC:
                cell.setCellType(CellType.STRING);
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    public static void main(String[] args) throws IOException {
        ExcelHelper excelHelper = new ExcelHelper();
        String[] a = new String[3];
        a[0] = "1";
        a[1] = "2";
        a[2] = "4";
        List<String[]> test = new ArrayList<>();
        test.add(a);
        excelHelper.writeExcel(test, "src/main/resources/Excel/score.xlsx");
    }
>>>>>>> c58a3ce7d0718c4c71ceb3c8ea622012148d548d
}