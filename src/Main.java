import edu.duke.FileResource;

public class Main {
    public static void main(String[] args) {
    //  FirstRatings test = new FirstRatings();

       // test.testLoadRaters();
          // test.testLoadMovies();
      //  SecondRatings test1 = new SecondRatings();
    //    System.out.println(test1.getAverageByID("0068646",1));;
        /*MovieRunnerAverage  test2 = new MovieRunnerAverage();
      test2.printAverageRating();
  test2.getAverageRatingOneMovie();*/

       // MovieRunnerWithFilters test2 = new MovieRunnerWithFilters();
       // test2.printAverageRating();
        //test2.printAverageRatingsByYear();
       //test2.printAverageRatingsByGenre();
       // test2.printAverageRatingsByMinutes();
       // test2.printAverageRatingsByDirectors();
       // test2.printAverageRatingsByYearAfterAndGenre();
      // test2.printAverageRatingsByDirectorsAndMinutes();
  //      MovieRunnerSimilarRatings test = new MovieRunnerSimilarRatings();
  //      test.printAverageRatingsByYearAfterAndMinutes();
       RecommendationRunner test1 = new RecommendationRunner();
        test1.printRecommendationsFor("1");
    }
}
