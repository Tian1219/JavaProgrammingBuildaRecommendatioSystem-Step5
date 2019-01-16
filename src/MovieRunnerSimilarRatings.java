import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {




    public void printAverageRatingsByYear() {
        // String moviesFile = "data/ratedmoviesfull.csv";
        //   String ratingsFile = "data/ratings_short.csv";

        // String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";

        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + RaterDatabase.size() + " raters in the file.");

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
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //  MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies in the file.");
        System.out.println("There are " + RaterDatabase.size() + " raters in the file.");

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


    public void printSimiliarRatings(){
        int minimalRaters =5;
        String id = "71";
        int numSimilarRaters=20;

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        ArrayList<Rating> recommendations= fr.getSimilarRatings(id,numSimilarRaters,minimalRaters);
        //Collections.sort(recommendations);
        System.out.println(recommendations.size() + " movie " + "matched");
        System.out.println("movieSimRatings: " + recommendations);

        for(Rating rating:recommendations){
            String movieTitle = MovieDatabase.getTitle((rating.getItem()));
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }
    public void printSimiliarRatingsByGenre(){
        int minimalRaters =5;
        String id = "964";
        int numSimilarRaters=20;
        String selecGenre = "Mystery";

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter gr = new GenreFilter(selecGenre);
        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilterByFilter(id,numSimilarRaters,minimalRaters,gr);
        System.out.println(recommendations.size() + " movie " + "matched");
        //System.out.println("movieSimRatings: " + recommendations);

        for(Rating rating:recommendations){
            String movieTitle = MovieDatabase.getTitle((rating.getItem()));
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printSimiliarRatingsByDirector(){
        int minimalRaters =2;
        String id = "120";
        int numSimilarRaters=10;
        String inputDirectors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter dr = new DirectorsFilter(inputDirectors);
        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilterByFilter(id,numSimilarRaters,minimalRaters,dr);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
            String movieTitle = MovieDatabase.getTitle((rating.getItem()));
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printSimiliarRatingsByGenreAndMinutes(){
        int minimalRaters =3;
        String id = "168";
        int numSimilarRaters=10;
        int minMinutes = 80;
        int maxMinutes = 160;
        String selecGenre = "Drama";

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter gr = new GenreFilter(selecGenre);
        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(gr);

        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilterByFilter(id,numSimilarRaters,minimalRaters,filtersList);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
            String movieTitle = MovieDatabase.getTitle((rating.getItem()));
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printAverageRatingsByYearAfterAndMinutes(){
        int minimalRaters =5;
        String id = "314";
        int numSimilarRaters=10;
        int minMinutes = 70;
        int maxMinutes = 200;
        int yearCount = 1975;

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter yf = new YearAfterFilter(yearCount);
        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(yf);

        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilterByFilter(id,numSimilarRaters,minimalRaters,filtersList);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
            String movieTitle = MovieDatabase.getTitle((rating.getItem()));
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }


}
