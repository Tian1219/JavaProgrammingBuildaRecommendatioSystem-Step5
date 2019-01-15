import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {




    public void printAverageRatingsByYear() {
        // String moviesFile = "data/ratedmoviesfull.csv";
        //   String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";

        FourthRatings fr = new FourthRatings(ratingsFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + fr.getRatersSize() + " raters in the file.");

        // create YearAfterFilter
        int numRating = 1;
        Filter yf = new YearAfterFilter(2000);
        ArrayList<Rating> list = fr.getAverageRatingsByFilter(numRating, yf);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (Rating r : list) {
            int Year = MovieDatabase.getYear(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Year + " " + Title);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        int numRating = 8;
        String ratingsFile = "data/ratings.csv";
        int yearCount = 1990;
        String selecGenre = "Drama";
        FourthRatings fr = new FourthRatings(ratingsFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //  MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + fr.getRatersSize() + " raters in the file.");

        Filter yf = new YearAfterFilter(yearCount);
        Filter gf = new GenreFilter(selecGenre);
        AllFilters filterList = new AllFilters();
        filterList.addFilter(yf);
        filterList.addFilter(gf);
        ArrayList<Rating> list = fr.getAverageRatingsByFilter(numRating, filterList);
        Collections.sort(list);

        for (Rating r : list) {
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());
            int Year = MovieDatabase.getYear(r.getItem());
            System.out.println(list.size() + " movie " + "matched");


            System.out.println(r.getValue() + " " + Year + " " + Title);
            System.out.println("       " + Genre);
        }
    }

}
