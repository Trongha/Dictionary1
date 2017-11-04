package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Trong on 30/10/2017.
 */
public class Input {
    private String path = "";

    public Input() {}

    public Input(String path) {
        this.path = path;
    }

    public  String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, Word> loadMap(){
        HashMap<String, Word> map = new HashMap<>();

        try{
            FileInputStream inputStream = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt( 0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                String eng="" ;
                String vie="" ;
            //doc ô 1
                if (cellIterator.hasNext()){
                    Cell keyEnglish = cellIterator.next();
                    eng = keyEnglish.getStringCellValue();
                    eng = eng.trim();
                }
            // đọc ô 2
                if (cellIterator.hasNext()){
                    Cell keyVietNam = cellIterator.next();
                    vie = keyVietNam.getStringCellValue();
                    vie = vie.trim();
                }
                if (!vie.equals("") && !eng.equals("")){
                    Word newWord = new Word(eng, vie);
                    map.put(newWord.getEnglish(), newWord);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return map;
    }
}
