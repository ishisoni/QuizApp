package edu.uga.cs.quizapp;

/**
 * This class (a POJO) represents a single job lead, including the id, company name,
 * phone number, URL, and some comments.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class QuizLead {

    private long id;
    private String date;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private String correct;
    private String countryID;
    private String country;
    private String continent;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    public QuizLead()
    {
        this.id = -1;
        this.date = null;
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
        this.correct = null;
        this.countryID = null;
        this.continent = null;
        this.country = null;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
        this.a5 = null;
        this.a6 = null;
        this.c1 = null;
        this.c2 = null;
        this.c3 = null;
        this.c4 = null;
        this.c5 = null;
        this.c6 = null;
    }

    public QuizLead( String date, String q1, String q2, String q3, String q4, String q5, String q6, String correct, String countryID, String country,String continent, String a1, String a2, String a3, String a4, String a5, String a6, String c1, String c2, String c3, String c4, String c5, String c6) {
        this.id = -1;
        this.date = date;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.correct = correct;
        this.countryID = countryID;
        this.continent = continent;
        this.country = country;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;

    }

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getQ1()
    {
        return q1;
    }
    public void setQ1(String q1)
    {
        this.q1 = q1;
    }

    public String getQ2()
    {
        return q2;
    }
    public void setQ2(String q2)
    {
        this.q2 = q2;
    }

    public String getQ3()
    {
        return q3;
    }
    public void setQ3(String q3)
    {
        this.q3 = q3;
    }

    public String getQ4()
    {
        return q4;
    }
    public void setQ4(String q4)
    {
        this.q4 = q4;
    }

    public String getQ5()
    {
        return q5;
    }
    public void setQ5(String q5)
    {
        this.q5 = q5;
    }

    public String getQ6()
    {
        return q6;
    }
    public void setQ6(String q6)
    {
        this.q6 = q6;
    }

    public String getCorrect()
    {
        return correct;
    }
    public void setCorrect(String correct)
    {
        this.correct = correct;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }
    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }
    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }
    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }
    public String getA6() {
        return a6;
    }

    public void setA6(String a6) {
        this.a6 = a6;
    }
    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }
    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }
    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }
    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }
    public String getC5() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5 = c5;
    }
    public String getC6() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6 = c6;
    }

    public String toString()
    {
        return id + ": " + date + " " + q1 + " " + q2 + " "+ q3 + " "+ q4 + " "+ q5 + " "+ q6 + " "+ q1 + " " + correct;
    }
}
