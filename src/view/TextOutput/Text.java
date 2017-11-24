package view.TextOutput;

import java.util.HashMap;

public class Text {
    private static HashMap<String, String> texts = new HashMap<String, String>();
    private static HashMap<String, String> paths = new HashMap<String, String>();

    static {
        texts.put("updated", "Đã cập nhật");
        texts.put("editBtn", "Sửa");
        texts.put("addBtn", "Thêm");
        texts.put("inputEmpty", "Chưa nhập xong");
        texts.put("", "Từ đã tồn tại");
        texts.put("ChuaChonBoTu", "Chưa chọn bộ từ");
        texts.put("ChuaChonSoLuong", "Chưa chọn số lượng");
        texts.put("Scores", "Điểm");
        texts.put("Scores", "Điểm");
        texts.put("ERROR", "Yêu cầu không thể thực hiện :(");


        paths.put("imgDefual", "src\\resrc\\img\\default.png");
        paths.put("imgFinish", "src\\resrc\\img\\giphy.gif");
        paths.put("imgInHome", "src\\resrc\\img\\imgInHome.jpg");

    }

    public static HashMap<String, String> getTexts() {
        return texts;
    }

    public static HashMap<String, String> getPaths() {
        return paths;
    }

}

