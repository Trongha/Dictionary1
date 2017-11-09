package data;

/**
 * Created by Trong on 28/10/2017.
 */
public class Word {
    private String English = "";
    private String VietNam = "";
    private String pathImage = "";

    public Word(){}
    public Word(String english, String vietNam) {
        setVietNam(vietNam);
        setEnglish(english);
    }
    public Word(String english, String vietNam, String pathImage) {
        setVietNam(vietNam);
        setEnglish(english);
        setPathImage(pathImage);
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        english = english.trim();
        english = english.toLowerCase();
        this.English = english;
    }

    public String getVietNam() {
        return VietNam;
    }

    public void setVietNam(String vietNam) {
        vietNam = vietNam.trim();
        vietNam = vietNam.toLowerCase();
        this.VietNam = vietNam;
    }


    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s", getEnglish(), getVietNam());
    }
}
