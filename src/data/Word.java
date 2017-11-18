package data;

/**
 * Created by Trong on 28/10/2017.
 */
public class Word {

    private String English = "";
    private String VietNam = "";
    private String pathImage = "";
    private Level level = Level.nothing;

    public Word(){}
    public Word(String english, String vietNam) {
        setVietNam(vietNam);
        setEnglish(english);
    }

    public void clone(Word xWord){
        setVietNam(xWord.getVietNam());
        setEnglish(xWord.getVietNam());
        setLevel(xWord.level.toString());
        setPathImage(xWord.getPathImage());
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(String _level) {
        for (Level level1 : Level.values()){
            if (_level.equals(level1.toString()))
                this.level = level1;
        }
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;

        if (!English.equals(word.English)) return false;
        if (!VietNam.equals(word.VietNam)) return false;
        if (!pathImage.equals(word.pathImage)) return false;
        return level == word.level;
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s   %s    %s", getEnglish(), getVietNam(), getLevel(), getPathImage());
    }
}
