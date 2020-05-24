class Movie {
    private String title;
    private String desc;
    private int year;

    // write two constructors here
    Movie(String title, String desc, int year){
        this.title = title;
        this.desc = desc;
        this.year = year;
    }
    Movie(String t, int y){
        title = t;
        year = y;
        desc = "empty";
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getYear() {
        return year;
    }
}