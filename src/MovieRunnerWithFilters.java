import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRating() {

        // String moviesFile = "data/ratedmoviesfull.csv";
       // String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
         String ratingsFile = "data/ratings.csv";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
          MovieDatabase.initialize("ratedmoviesfull.csv");
      // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        int numRating = 35;
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
     //   String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
         String ratingsFile = "data/ratings.csv";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
          MovieDatabase.initialize("ratedmoviesfull.csv");
       // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        // create YearAfterFilter
        int numRating = 1;
        Filter yf = new YearAfterFilter(2000);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating, yf);
        Collections.sort(list);
        System.out.println("Found " + list.size() + " movies");
        for (Rating r : list) {
            int Year = MovieDatabase.getYear(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Year + " " + Title);
        }
    }

    public void printAverageRatingsByGenre() {
        // String moviesFile = "data/ratedmoviesfull.csv";
        //String ratingsFile = "data/ratings_short.csv";
        int numRating = 20;
        String selectGenre = "Comedy";

        // String moviesFile = "data/ratedmoviesfull.csv";
         String ratingsFile = "data/ratings.csv";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
         MovieDatabase.initialize("ratedmoviesfull.csv");
      //  MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        Filter gr = new GenreFilter(selectGenre);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(1, gr);
        Collections.sort(list);
        System.out.println("found " + list.size() + " movies");

        for (Rating r : list) {
            String Genre = MovieDatabase.getGenres(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Title + " [" + Genre + "]");

        }
    }

    public void printAverageRatingsByMinutes() {
        String ratingsFile = "data/ratings.csv";
        int numRating = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        ThirdRatings sr = new ThirdRatings(ratingsFile);
         MovieDatabase.initialize("ratedmoviesfull.csv");
       // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        Filter mr = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating, mr);
        Collections.sort(list);
        System.out.println("found " + list.size() + " movies");

        for (Rating r : list) {
            String Title = MovieDatabase.getTitle(r.getItem());
            int Time = MovieDatabase.getMinutes(r.getItem());

            System.out.println(r.getValue() + " " + "Time: " + Time + " " + Title);

        }
    }

    public void printAverageRatingsByDirectors() {
        String ratingsFile = "data/ratings.csv";
        int numRating = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        ThirdRatings sr = new ThirdRatings(ratingsFile);
          MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        Filter dr = new DirectorsFilter(directors);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating, dr);
        Collections.sort(list);
        System.out.println("found " + list.size() + " movies");

        for (Rating r : list) {
            String Title = MovieDatabase.getTitle(r.getItem());
            String Directors = MovieDatabase.getDirector(r.getItem());

            System.out.println(r.getValue() + " " + Title);
            System.out.println("      " + Directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        int numRating = 8;
        String ratingsFile = "data/ratings.csv";
        int yearCount = 1990;
        String selecGenre = "Drama";
        ThirdRatings sr = new ThirdRatings(ratingsFile);
         MovieDatabase.initialize("ratedmoviesfull.csv");
      //  MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        Filter yf = new YearAfterFilter(yearCount);
        Filter gf = new GenreFilter(selecGenre);
        AllFilters filterList = new AllFilters();
        filterList.addFilter(yf);
        filterList.addFilter(gf);
        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating, filterList);
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

    public void printAverageRatingsByDirectorsAndMinutes(){
        int numRating = 3;
        String ratingsFile = "data/ratings.csv";

        int minMinutes = 90;
        int maxMinutes =180;
        String directors ="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";

        ThirdRatings sr = new ThirdRatings(ratingsFile);
         MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + sr.getRatersSize() + " raters in the file.");

        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        Filter dr = new DirectorsFilter(directors);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(dr);

        ArrayList<Rating> list = sr.getAverageRatingsByFilter(numRating,filtersList);
        Collections.sort(list);
        System.out.println(list.size() + " movie " + "matched");

        for(Rating r:list){
            String matechedTitle = MovieDatabase.getTitle(r.getItem());
            String matchedDirectors = MovieDatabase.getDirector(r.getItem());
            int matchMinutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue()+ " "+ " Time: "+matchMinutes+ " " +matechedTitle);
            System.out.println("      " + matchedDirectors);
        }
    }

    }
