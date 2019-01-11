
import edu.duke.*;

import java.util.*;

import org.apache.commons.csv.*;

import javax.sound.midi.Soundbank;

public class FirstRatings {

    ArrayList<Movie> loadMovie(String filename) {
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for (CSVRecord record : fr.getCSVParser()) {
            Movie currMovie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            ;

            movieList.add(currMovie);
        }
        return movieList;
    }


    public void testLoadMovies() {
        ArrayList<Movie> loadedMovies = loadMovie("data/ratedmoviesfull.csv");

        System.out.println("number of movies: " + loadedMovies.size());
        System.out.println("loadMovie: " + loadedMovies);

        int genreCount = 0;
        int moreThan150min = 0;
        HashMap<String, Integer> directCounts = new HashMap<>();

        for (int k = 0; k < loadedMovies.size(); k++) {
            Movie currMovie = loadedMovies.get(k);
            //System.out.println(currMovie);
            if (loadedMovies.get(k).getGenres().contains("Comedy")) {
                genreCount++;
            }
            if (loadedMovies.get(k).getMinutes() > 150) {
                moreThan150min++;
            }

            String currDirector = currMovie.getDirector();
            //System.out.println("current director: " + currDirector);

            if (directCounts.containsKey(currDirector)) {
                directCounts.put(currDirector, directCounts.get(currDirector) + 1);
            } else directCounts.put(currDirector, 1);
            //System.out.println("directorCounts : " + directorCounts);

        }
        int dirWithMaxMovies = Collections.max(directCounts.values());
        ArrayList<String> movieWithMaxdirs = new ArrayList<>();

        for (String dir : directCounts.keySet()) {
            if (directCounts.get(dir) == dirWithMaxMovies) {
                movieWithMaxdirs.add(dir);
            }
        }
        System.out.println("Director with Max movies: " + dirWithMaxMovies);
        System.out.println("The numbers of Comedy movies: " + genreCount);
        System.out.println("The numbers of movies with more than 150 minutes: " + moreThan150min);


        System.out.println("Max number of movies by a single director: " + dirWithMaxMovies);
        System.out.println("Directors that directed the max number of movies: \n" + movieWithMaxdirs);


    }

    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource(filename);
        ArrayList<Rater> raters = new ArrayList<>();
        ArrayList<String> raterIDlist = new ArrayList<>();
        // find the number of ratings for a particular rater

        for (CSVRecord record : fr.getCSVParser()) {
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            if (raterIDlist.contains(rater_id)) {
                for (int k = 0; k < raters.size(); k++) {
                    Rater currRater = raters.get(k);
                    if (currRater.getID().equals(rater_id)) {
                        currRater.addRating(movie_id, rating);
                        raters.set(k, currRater);

                    }
                }
            } else {
                Rater newRater = new Rater(rater_id);
                newRater.addRating(movie_id, rating);

                raters.add(newRater);
                raterIDlist.add(rater_id);

            }


        }
        return raters;

    }

    public void testLoadRaters() {
        // print the total number of raters
       // ArrayList<Rater> loadedRaters = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> loadedRaters = loadRaters("data/ratings.csv");
        System.out.println("loadedRaters size: " + loadedRaters.size());
        System.out.println("They are: " + loadedRaters);
        System.out.println("The number of ratings for ID 193 is " + getRatingsPerRater(loadedRaters, "193"));
        findMaxNumberOfRatingsByRater(loadedRaters);
        findRatingsOfMoive(loadedRaters, "1798709");
        countRatedMovies(loadedRaters);
    }

    public int getRatingsPerRater(ArrayList<Rater> raters, String rater_id) {
        for (Rater currRater : raters) {
            if (currRater.getID().equals(rater_id)) {

                return currRater.numRatings();
            }

        }
        return -1;
    }

    public void findMaxNumberOfRatingsByRater(ArrayList<Rater> raters) {
        int max = 0;
        for (Rater currRater : raters) {
            if (currRater.numRatings() > max) {
                max = currRater.numRatings();
            }
        }
        System.out.println(" The maximum number of ratings by any rater " + max);
        ArrayList<String> raterIDlist = new ArrayList<>();

        for (Rater currRater : raters) {
            if (currRater.numRatings() == max) {
                raterIDlist.add(currRater.getID());
            }
        }
        System.out.println("And they are: " + raterIDlist);
    }


    public void findRatingsOfMoive(ArrayList<Rater> raters, String movieID) {
        int num = 0;
        for (Rater currRater : raters) {
            if (currRater.getItemsRated().contains(movieID)) {
                num++;
            }
        }
        System.out.println("Movie with ID " + movieID + " was rated by " + num + " raters.");
    }

    public void countRatedMovies(ArrayList<Rater> raters) {
        ArrayList<String> movies = new ArrayList<>();
        for (Rater currater : raters) {
            ArrayList<String> currMovies = currater.getItemsRated();
            for(String s : currMovies){
                if(!movies.contains(s)){
                    movies.add(s);
                }
            }

        }
        System.out.println("There are " + movies.size() + " movies rated.");
    }

}
