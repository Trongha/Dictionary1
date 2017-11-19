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
    private String patch = "";

    public Input() {}

    public Input(String patch) {
        this.patch = patch;
    }

    public  String getPatch() {
        return this.patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public HashMap<String, Word> loadMap(){
        HashMap<String, Word> map = new HashMap<>();

        try{
            FileInputStream inputStream = new FileInputStream(new File(patch));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt( 0);
            Iterator<Row> rowIterator = sheet.iterator();

            //Bor dongf ddaafu
            if (rowIterator.hasNext()){
                Row row = rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String nameGroup = patch.substring(patch.lastIndexOf('\\')+1, patch.lastIndexOf('.'));

                String eng="" ;
                String vie="" ;
                String pathImage = "";
                String level = "";
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
//                đọc ô 3
                if (cellIterator.hasNext()){
                    Cell pathImageCell = cellIterator.next();
                    level = pathImageCell.getStringCellValue().toString();
                }
                //Đọc ô 4
                if (cellIterator.hasNext()){
                    Cell pathImageCell = cellIterator.next();
                    pathImage = pathImageCell.getStringCellValue().toString();
                }

                if (!vie.equals("") && !eng.equals("")){
                    Word newWord = new Word(eng, vie, pathImage, nameGroup);
                    newWord.setLevel(level);
                    map.put(newWord.getEnglish(), newWord);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(String.format("%-70s", patch) + " Load Complete!");
        return map;
    }
}
