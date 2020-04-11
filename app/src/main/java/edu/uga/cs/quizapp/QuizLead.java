package edu.uga.cs.quizapp;

/**
 * This class (a POJO) represents a single job lead, including the id, company name,
 * phone number, URL, and some comments.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class QuizLead {

    private String date;

    private int correct;

    public QuizLead()
    {
        this.date = null;
        this.correct = 0;
    }

    public QuizLead(String date, int correct) {
        this.date = date;
        this.correct = correct;
    }

    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public int getCorrect()
    {
        return correct;
    }
    public void setCorrect(int correct)
    {
        this.correct = correct;
    }


    public String toString()
    {
        double percentage = 0.0;
        percentage = Math.ceil((correct/6)*100);
        return "Date: " + date + "     " + "Number Correct: " + correct + "/6" + "    ";
    }
}
