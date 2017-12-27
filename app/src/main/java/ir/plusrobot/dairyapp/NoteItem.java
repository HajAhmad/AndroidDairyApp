package ir.plusrobot.dairyapp;


public class NoteItem {

    private String title;
    private String date;
    private boolean isFav;
    private int id;

    public NoteItem() {
    }

    public NoteItem(String title, String date, boolean isFav, int id) {
        this.title = title;
        this.date = date;
        this.isFav = isFav;
        this.id = id;
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

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteItem{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", isFav=" + isFav +
                ", id=" + id +
                '}';
    }
    
    public void print(){
        System.out.println(toString());
    }
}
