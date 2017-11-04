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
    private String path = "E:\\Java\\testLinhTinh\\src\\outSrc\\";
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

    public void outFile(HashMap<String, Word> map){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowNum = 0;

        for(Map.Entry entry : map.entrySet()){
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            Word word =(Word)entry.getValue();
            row.createCell(cellNum++).setCellValue(word.getEnglish());
            row.createCell(cellNum++).setCellValue(word.getVietNam());
        }
        try{
            FileOutputStream out = new FileOutputStream(new File(this.path));
            workbook.write(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
