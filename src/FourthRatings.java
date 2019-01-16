import org.omg.CORBA.ARG_IN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {


    private double getAverageByID(String id, int minimalRaters) {
        double average = 0;
        double total = 0;
        int countRaters = 0;

        ArrayList<Rater> myRaters = RaterDatabase.getRaters();

        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                countRaters++;
                total = total + rater.getRating(id);
            }
        }

        if (countRaters >= minimalRaters) {
            average = total / countRaters;
        }

        return average;

    }

    public ArrayList<Rating> getAverageRatings(int minimalRasters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> allAverageRating = new ArrayList<>();
        for (String currMovieID : movies) {

            Double averageRating = getAverageByID(currMovieID, minimalRasters);
            allAverageRating.add(new Rating(currMovieID, averageRating));
        }
        return allAverageRating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for (String s : movieIDs) {
            double ratingVluae = getAverageByID(s, minimalRaters);
            if (ratingVluae > 0.0) {
                averageRatings.add(new Rating(s, ratingVluae));
            }
        }
        return averageRatings;
    }

    private double dotProduct(Rater me, Rater r) {
        double similarValue = 0;
        ArrayList<String> itemRatedByMe = me.getItemsRated();

        for (String movieID : itemRatedByMe) {
            if (r.hasRating(movieID)) {
                double rRating = r.getRating(movieID) - 5;
                double myRating = me.getRating((movieID)) - 5;

                similarValue = similarValue + (rRating * myRating);
            }
        }
        return similarValue;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rater> allRater = RaterDatabase.getRaters();
        // input id implied raterByMe
        Rater me = RaterDatabase.getRater(id);

        for (Rater r : allRater) {
            String raterID = r.getID();
            if (raterID.equals(id) == false) {
                double similarValue = dotProduct(me, r);
                Rating similarRating = new Rating(raterID, similarValue);
                list.add(similarRating);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilterByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilterByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieSimRatings = new ArrayList<>();
        ArrayList<Rating> raterSimList = getSimilarities(id);
        ArrayList<String> movieIDList = new ArrayList<>();

        HashMap<String, Double> similarMap = new HashMap<>();
        int mapSize = getSimilarities(id).size();
        int minIndex = Math.min(mapSize, numSimilarRaters);

        for (Rating similar : getSimilarities(id).subList(0, minIndex)) {
            if (similar.getValue() > 0) {
                similarMap.put(similar.getItem(), similar.getValue());

            }
        }
        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
            int count = 0;
            double total = 0;

            for (Rater curRater : RaterDatabase.getRaters()) {
                double rating = -1;
                if (similarMap.containsKey(curRater.getID()) && curRater.hasRating(movieID)) {
                    rating = curRater.getRating(movieID) * similarMap.get(curRater.getID());
                }
                if (rating == -1) {
                } else {
                    count++;
                    total = total + rating;
                }

            }
            if (count < minimalRaters || total == 0) {

            } else {
                movieSimRatings.add(new Rating(movieID, total / count));

            }
        }Collections.sort(movieSimRatings, Collections.reverseOrder());
        return movieSimRatings;
    }
}