package ir.plusrobot.dairyapp.rest;


public class Dairy {

    private int _Id;
    private String _Title;
    private String _Content;
    private String _Date;
    private boolean _IsFav;

    public Dairy() {
    }

    public Dairy(int _Id, String _Title, String _Content, String _Date, boolean _IsFav) {
        this._Id = _Id;
        this._Title = _Title;
        this._Content = _Content;
        this._Date = _Date;
        this._IsFav = _IsFav;
    }

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_Title() {
        return _Title;
    }

    public void set_Title(String _Title) {
        this._Title = _Title;
    }

    public String get_Content() {
        return _Content;
    }

    public void set_Content(String _Content) {
        this._Content = _Content;
    }

    public String get_Date() {
        return _Date;
    }

    public void set_Date(String _Date) {
        this._Date = _Date;
    }

    public boolean is_IsFav() {
        return _IsFav;
    }

    public void set_IsFav(boolean _IsFav) {
        this._IsFav = _IsFav;
    }

    @Override
    public String toString() {
        return "Dairy{" +
                "_Id=" + _Id +
                ", _Title='" + _Title + '\'' +
                ", _Content='" + _Content + '\'' +
                ", _Date='" + _Date + '\'' +
                ", _IsFav=" + _IsFav +
                '}';
    }
}
