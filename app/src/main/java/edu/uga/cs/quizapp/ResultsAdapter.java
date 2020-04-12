package edu.uga.cs.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

/**
 *
 * Adapter class for the Results porttion of the QuizApp
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {
    private List<QuizLead> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    /**
     *
     * Class for extending the recycleview
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textView;

        /**
         *
         * Constructor for MyViewHolder class
         * @param v - current view
         */
        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }

    /**
     *
     * Constructor for the QuizLead dataset
     * @param myDataset - dataset containing all quizzes.
     */
    // Provide a suitable constructor (depends on the kind of dataset)
    public ResultsAdapter(List<QuizLead> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)

    /**
     *
     * method to create the view using a layout inflator
     * @param parent - viewgroup parent
     * @param viewType - type of view
     * @return the viewholder
     */
    @Override
    public ResultsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /**
     *
     * Method to replace the contents of a view as invoked by a layout manager
     * @param holder - the specific view holder
     * @param position - the position of a particular view
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        QuizLead quizlead = mDataset.get(position);
        holder.textView.setText(quizlead.toString());


    }

    /**
     *
     * Retrieve the number of items from the list
     * @return
     */
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}