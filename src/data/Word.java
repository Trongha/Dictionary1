package data;

/**
 * Created by Trong on 28/10/2017.
 */
public class Word {

    private String English = "";
    private String VietNam = "";
    private String pathImage = "";
    private Level level = Level.nothing;
    private String wordGroup;

    public Word(){}
    public Word(String english, String vietNam) {
        setVietNam(vietNam);
        setEnglish(english);
    }

    public Word(String _english, String _vietNam, String _pathImage, String _wordGroup) {
        setVietNam(_vietNam);
        setEnglish(_english);
        setPathImage(_pathImage);
        setWordGroup(_wordGroup);
    }

    public void clone(Word xWord){
        setVietNam(xWord.getVietNam());
        setEnglish(xWord.getEnglish());
        setLevel(xWord.level.toString());
        setPathImage(xWord.getPathImage());
        setWordGroup(xWord.getWordGroup());
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

    public String getWordGroup() {
        return wordGroup;
    }

    public void setWordGroup(String _wordGroup) {
        this.wordGroup = new String();
        this.wordGroup = _wordGroup;
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

        if (!getEnglish().equals(word.getEnglish())) return false;
        if (!getVietNam().equals(word.getVietNam())) return false;
        return getWordGroup().equals(word.getWordGroup());
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s   %s    %s    group:  %s", getEnglish(), getVietNam(), getLevel(), getPathImage(), getWordGroup());
    }
}
