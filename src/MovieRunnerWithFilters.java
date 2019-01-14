import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRating() {

        // String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
        //  MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        int numRating = 1;
        int count = 0;

        ArrayList<Rating> ratings = sr.getAverageRatings(numRating);
        Collections.sort(ratings);
        System.out.println("Rating values of Movies with at least " + numRating + " ratings:");

        for (Rating currRating : ratings) {
            double currValue = currRating.getValue();
            if (currValue != 0) {
                String currMovieID = currRating.getItem();
                count++;
                System.out.println(currValue + " " + MovieDatabase.getTitle(currMovieID) + " " + count);
            }
        }

    }

    public void printAverageRatingsByYear() {
        // String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
        //  MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        // create YearAfterFilter
        int numRating = 1;
        Filter yf = new YearAfterFilter(2000);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating, yf);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for(Rating r : list){
            int Year = MovieDatabase.getYear(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Year + " " + Title);
        }
    }
}
