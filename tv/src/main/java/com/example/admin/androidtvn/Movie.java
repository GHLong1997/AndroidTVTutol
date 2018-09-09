package com.example.admin.androidtvn;

public class Movie {
 
    private static final String TAG = Movie.class.getSimpleName();
 
    static final long serialVersionUID = 727566175075960653L;
    private long id;
    private String title;
    private String studio;
    private String cardImageUrl;
    private String bgImageUrl;
    private String description;
    public Movie() {
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getStudio() {
        return studio;
    }
 
    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getBackgroundImageUrl() {
        return bgImageUrl;
    }

    public void setBackgroundImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}