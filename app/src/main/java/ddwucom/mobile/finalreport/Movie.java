package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    String title;
    String actor;
    String genre;
    String director;
    String date;
    int image;

    public Movie(String title, String actor, String genre, String director, String date) {
        this.title = title;
        this.actor = actor;
        this.genre = genre;
        this.director = director;
        this.date = date;
    }

    public Movie(long _id, String title, String actor, String genre, String director, String date, int image) {
        this._id = _id;
        this.title = title;
        this.actor = actor;
        this.genre = genre;
        this.director = director;
        this.date = date;
        this.image = image;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}