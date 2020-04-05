package edu.uga.cs.quizapp;

public class Quiz {
    private String country;
    private String continent;
    private boolean isCorrect;

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public String getContinent() {
        return continent;
    }
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    public boolean getIsCorrect() {
        return isCorrect;
    }
}

