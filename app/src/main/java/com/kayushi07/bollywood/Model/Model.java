package com.kayushi07.bollywood.Model;


public class Model {

    public static final int CLOSED_LEVEL=0;
    public static final int OPEN_LEVEL=1;

    public int type;
    public int id, movies, score, unlock_score;
    public String text;



    public Model(int type, String text, int id, int movies, int score, int unlock_score)
    {
        this.type=type;
        this.id = id;
        this.movies = movies;
        this.score = score;
        this.text=text;
        this.unlock_score = unlock_score;

    }

}
