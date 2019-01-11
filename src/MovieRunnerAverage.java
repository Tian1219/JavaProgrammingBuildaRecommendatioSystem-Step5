public class MovieRunnerAverage {

    public void printAverageRating(){

       // String moviesFile = "data/ratedmoviesfull.csv";
       // String ratingsFile = "data/ratings.csv";

        String moviesFile = "data/ratedmovies_short.csv";
        String ratingsFile = "data/ratings_short.csv";

        SecondRatings sr = new SecondRatings(moviesFile,ratingsFile);

        System.out.println("Number of rated movie: " +sr.getMoviesSize());
        System.out.println("Number of raters: " + sr.getRatersSize());
    }
}
