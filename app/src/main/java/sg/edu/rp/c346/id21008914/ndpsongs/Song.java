package sg.edu.rp.c346.id21008914.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
        this.id = id;
    }
    public int get_id() {  return id;  }
    public String getTitle() {  return title;  }
    public String getSingers() {  return singers;  }
    public int getYear() {  return year;  }
    public int getStars() {  return stars;  }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String stars = "";
        for (int i = 0; i < getStars(); i++) {
            stars += "*";
        }
        String output = getTitle() + "\n"
                + getSingers() + " - " + getYear() + "\n"
                + stars;
        return output;
    }

}


