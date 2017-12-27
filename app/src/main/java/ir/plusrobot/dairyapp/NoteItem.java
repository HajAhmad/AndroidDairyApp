package ir.plusrobot.dairyapp;


public class NoteItem {

    private String title;
    private String date;

    public NoteItem() {
    }

    public NoteItem(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NoteItem{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
