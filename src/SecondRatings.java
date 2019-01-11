
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String loadRaters) {

        FirstRatings fr = new FirstRatings();

        myMovies = fr.loadMovie(moviefile);
        myRaters = fr.loadRaters(loadRaters);
    }

    public int getMoviesSize() {
        return myMovies.size();
    }

    public int getRatersSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters){
        int numRatings = 0;
        double totalScore = 0;
        for(Rater currRater : myRaters){
            ArrayList<String>  cureMovies = currRater.getItemsRated();
            if(cureMovies.contains(id)){
                numRatings++;
                totalScore += currRater.getRating(id);
            }
        }
        if (numRatings < minimalRaters){
            return 0.0;
        } else {
            return totalScore/numRatings;
        }

    }

}
