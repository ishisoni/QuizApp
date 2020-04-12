package edu.uga.cs.quizapp;

/**
 * This class (a POJO) represents a single Quiz lead, including the id, company name,
 * phone number, URL, and some comments.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class QuizLead {

    private String date;

    private int correct;

    /**
     *
     * Default constructor for a single quiz object
     */
    public QuizLead()
    {
        this.date = null;
        this.correct = 0;
    }

    /**
     *
     * Constructor for quiz object with two parameters
     * @param date - date quiz was taken
     * @param correct - number of correct answers
     */
    public QuizLead(String date, int correct) {
        this.date = date;
        this.correct = correct;
    }

    /**
     * getter method for date
     * @return date
     */
    public String getDate()
    {
        return date;
    }

    /**
     *
     * setter method for date
     * @param date - date to set
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     *
     * Getter method for obtaining correct answers
     * @return number of correct answers
     */
    public int getCorrect()
    {
        return correct;
    }

    /**
     * setter method for setting number of correct answers
     * @param correct - number of correct answers
     */
    public void setCorrect(int correct)
    {
        this.correct = correct;
    }


    /**
     *
     * To string method that returns content of the quiz object in string format
     * @return a string containing quiz info
     */
    public String toString()
    {
        double percentage = 0.0;
        //return an appropriate string
        return "Date: " + date + "     " + "Number Correct: " + correct + "/6" + "    ";
    }
}
