import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRating() {

        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";

        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";

        SecondRatings sr = new SecondRatings(moviesFile, ratingsFile);

        System.out.println("Number of rated movie: " + sr.getMoviesSize());
        System.out.println("Number of raters: " + sr.getRatersSize());

        int numRating = 12;
        int count = 0;

        ArrayList<Rating> ratings = sr.getAverageRatings(numRating);
        Collections .sort(ratings);
        System.out.println("Rating values of Movies with at least " + numRating + " ratings:");

        for (Rating currRating : ratings) {
            double currValue = currRating.getValue();
            if (currValue != 0) {
                String currMovieID = currRating.getItem();
                count ++;
                System.out.println(currValue + " " + sr.getTitle(currMovieID) + " "+ count);
            }
        }

    }

    public void getAverageRatingOneMovie(){
        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviesFile, ratingsFile);

        int numRating = 0 ;
        String movieTitle = "Vacation";

        String MoviveID = sr.getID(movieTitle);
        double averageRatingOneMovie =  sr.getAverageByID(MoviveID,numRating);

        System.out.println("The average rating for " + movieTitle + " is " + averageRatingOneMovie + ".");



    }
}
