
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    ArrayList<Movie> loadMovie(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(CSVRecord record : fr.getCSVParser()){
            Movie currMovie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));;

            movieList.add(currMovie);
        }
        return movieList;
    }


    public void testLoadMovies(){
        ArrayList<Movie> loadedMovies = loadMovie("data/ratedmoviesfull.csv");

        System.out.println("number of movies: " + loadedMovies.size());
        System.out.println("loadMovie: " + loadedMovies );

        int genreCount =0;
        int moreThan150min =0;
        HashMap<String,Integer> directCounts = new HashMap<>();

        for(int k =0; k<loadedMovies.size();k++){
            Movie currMovie = loadedMovies.get(k);
            //System.out.println(currMovie);
            if(loadedMovies.get(k).getGenres().contains("Comedy")){
                genreCount++;
            }
            if(loadedMovies.get(k).getMinutes()>150){
                moreThan150min++;
            }

            String currDirector = currMovie.getDirector();
            //System.out.println("current director: " + currDirector);

            if (directCounts.containsKey(currDirector)){
                directCounts.put(currDirector,directCounts.get(currDirector)+1);
            }else directCounts.put(currDirector,1);
            //System.out.println("directorCounts : " + directorCounts);

        }
        int dirWithMaxMovies = Collections.max(directCounts.values());
        ArrayList<String> movieWithMaxdirs = new ArrayList<>();

        for(String dir :directCounts.keySet()){
            if(directCounts.get(dir) == dirWithMaxMovies){
                movieWithMaxdirs.add(dir);
            }
        }
        System.out.println("Director with Max movies: " + dirWithMaxMovies);
        System.out.println("The numbers of Comedy movies: "+genreCount);
        System.out.println("The numbers of movies with more than 150 minutes: "+ moreThan150min);


        System.out.println("Max number of movies by a single director: "+ dirWithMaxMovies);
        System.out.println("Directors that directed the max number of movies: \n" + movieWithMaxdirs);

    }

        public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource();
        ArrayList<Rater> raters = new ArrayList<>();

        for(CSVRecord record:fr.getCSVParser()){

        }
        }



}
