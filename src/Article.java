package file;

public class Article extends Chapter {
    private int ID;
    private String text;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article(int chapterID, int ID, String text) {
        super(chapterID);
        this.ID = ID;
        this.text = text;
    }
}
