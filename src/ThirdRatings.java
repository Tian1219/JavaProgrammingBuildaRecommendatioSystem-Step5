import java.util.ArrayList;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {

        FirstRatings fr = new FirstRatings();

        myRaters = fr.loadRaters(ratingsfile);
    }

  /*  public int getMoviesSize() {
        return myMovies.size();
    }*/

    public int getRatersSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id,int minimalRaters){
        double average =0;
        double total=0;
        int countRaters= 0;
        for(Rater rater:myRaters){
            if(rater.hasRating(id)){
                countRaters++;
                total = total + rater.getRating(id);
            }
        }

        if(countRaters >= minimalRaters){
            average = total/countRaters;
        }

        return average;

    }

    public ArrayList<Rating> getAverageRatings(int minimalRasters) {
        ArrayList<String > movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> allAverageRating = new ArrayList<>();
        for (String currMovieID : movies) {

            Double averageRating = getAverageByID(currMovieID, minimalRasters);
            allAverageRating.add(new Rating(currMovieID, averageRating));
        }
        return allAverageRating;
    }

    /*public String getTitle(String movieID) {
        for (Movie currMovie : myMovies) {
            if (currMovie.getID().equals(movieID)) {
                return currMovie.getTitle();
            }
        }
        return "N/A";
    }*/

 /*   public String getID(String movieTitle) {
        for (Movie currMovie : myMovies) {
            if (currMovie.getTitle().equals(movieTitle)) {
                return currMovie.getID();
            }
        }
        return "N/A";
    }*/

    public ArrayList <Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
                    ArrayList<String> movieIDs =MovieDatabase.filterBy(filterCriteria);
                    ArrayList<Rating> averageRatings  = new ArrayList<Rating>();
                    for(String s: movieIDs){
                        double ratingVluae = getAverageByID(s,minimalRaters);
                        if(ratingVluae>0.0) {
                            averageRatings.add(new Rating(s, ratingVluae));
                        }
                    }
                return averageRatings;
    }

}
