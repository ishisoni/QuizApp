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
    }

    public QuizLead( String date, String q1, String q2, String q3, String q4, String q5, String q6, String correct   ) {
        this.id = -1;
        this.date = date;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.correct = correct;
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



    public String toString()
    {
        return id + ": " + date + " " + q1 + " " + q2 + " "+ q3 + " "+ q4 + " "+ q5 + " "+ q6 + " "+ q1 + " " + correct;
    }
}
