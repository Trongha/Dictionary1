package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trong on 4/11/2017.
 */
public class Output {
    private String path = "src\\data\\dataFile\\xlsx\\";
    private String tail = ".xlsx";

    public Output(){}
    public Output(String nameFile){
        this.path += nameFile;
        if (nameFile.indexOf('.') < 0){
            this.path+=tail;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public void ghiTieuDe(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int cellNum = 0;
        row.createCell(cellNum++).setCellValue("English");
        row.createCell(cellNum++).setCellValue("Tieng Viet");
        row.createCell(cellNum++).setCellValue("Level");
        row.createCell(cellNum++).setCellValue("PathImage");
    }

    public void outFile(HashMap<String, Word> map){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        ghiTieuDe(sheet);

        int rowNum = 1;
        //Ghi tieu de:

        for(Map.Entry entry : map.entrySet()){
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            Word word =(Word)entry.getValue();
            row.createCell(cellNum++).setCellValue(word.getEnglish());
            row.createCell(cellNum++).setCellValue(word.getVietNam());
            row.createCell(cellNum++).setCellValue(word.getLevel().toString());
            row.createCell(cellNum++).setCellValue(word.getPathImage());
        }
        try{
            System.out.println(this.path);
            FileOutputStream out = new FileOutputStream(new File(this.path));
            workbook.write(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
