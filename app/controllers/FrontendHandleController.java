package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrontendHandleController extends Controller {
    private List<FootballClub> footballClubList = new ArrayList<>(); // To store the football club objects
    private List<Match> matchList = new ArrayList<>(); // To store the match objects

    // Loading data from the text file----------------------------------------------------------------------------------
    public void loadData(){
        try(FileInputStream fileInputStream = new FileInputStream("savedData.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            for (; ; )
            {
                try
                {
                    Object object = ( objectInputStream.readObject()); // Reading the object from the text file
                    Class objClass = object.getClass(); // Getting the type of the read object

                    if ((FootballClub.class == objClass)) { // If the object type is equal to FootballClub type
                        footballClubList.add((FootballClub) object); // adding to the footballClub list
                    }
                    if ((Match.class == objClass)) { // If the object type is equal to Match type
                        matchList.add((Match) object); // adding to the match list
                    }
                } catch (EOFException eofException)
                {
                    break;
                }
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("File Not Found !");
        }
        catch (IOException ioException)
        {
            System.out.println("ERROR" + ioException.getMessage());
        }
        catch (Exception exception)
        {
            System.out.println("ERROR" + exception.getMessage());
        }
    }
    //Saving data to the text file--------------------------------------------------------------------------------------
    public void saveData() throws IOException {
       // Collections.sort(footballClubList); // Sorting objects before saving to the text file
        FileOutputStream fileOutputStream = new FileOutputStream(("savedData.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Saving football club objects to text file
        for(SportsClub footballClub : footballClubList) {
            objectOutputStream.writeObject(footballClub);
        }
        // Saving match object to text file
        for(Match match : matchList) {
            objectOutputStream.writeObject(match);
        }
    }
    // Send list of Football Clubs to the Frontend----------------------------------------------------------------------
    public Result listFootballClubs() {
        footballClubList.clear(); // Clearing the football club arraylist to add new data
        loadData(); // Loading the football clubs and match clubs from the text file
        ArrayList<JsonNode> clubList_Frontend = new ArrayList<>(); // Temporary array list to store Json nodes

        Collections.sort(footballClubList); // Sorting football clubs by points, goal difference
        for(int i =0; i < footballClubList.size();i++) { // Getting football club objects
            JsonNode jsonNode_club = Json.toJson(footballClubList.get(i)); // Casting the football object to Json node
            clubList_Frontend.add(jsonNode_club); // Adding Json node to the array list
        }
        ObjectMapper mapper = new ObjectMapper(); // Creating a new Object Mapper
        JsonNode jsonData_Clubs = mapper.convertValue(clubList_Frontend, JsonNode.class); //Mapping the arraylist to a Json Node
        return ok(jsonData_Clubs); // Returning data to frontend
    }
    //Send list of played matches to the frontend-----------------------------------------------------------------------
    public Result listMatchData() {
        matchList.clear(); // Clearing the match club arraylist to add new data
        loadData(); // Loading the football clubs and match clubs from the text file
        ArrayList<JsonNode> matchList_Frontend = new ArrayList<>(); // Temporary array list to store Json nodes

        Collections.sort(matchList,Collections.reverseOrder()); // Sorting the match list by the date
        for(int i =0; i < matchList.size();i++) { // Getting the match data from the array list
            JsonNode jsonNode_match = Json.toJson(matchList.get(i)); // Casting match object to Json node
            matchList_Frontend.add(jsonNode_match); // Adding the Json node to the temporary arraylist
        }

        ObjectMapper mapper = new ObjectMapper(); //Creating a new Object Mapper
        JsonNode jsonData_Matches = mapper.convertValue(matchList_Frontend, JsonNode.class);  //Mapping the arraylist to a Json Node
        return ok(jsonData_Matches); // Returning data to frontend
    }
    //Search Match from date--------------------------------------------------------------------------------------------
    //Taking the day, month, year as parameters from the frontend
    public Result searchMatch(String day, String month, String year){
        footballClubList.clear(); // Clearing the football club arraylist to add new data
        matchList.clear(); // Clearing the match club arraylist to add new data
        loadData(); // Loading the football clubs and match clubs from the text file
        ArrayList<JsonNode> searchMatchList = new ArrayList<>(); // Temporary array list to store Json nodes
        Date searchDate = new Date(Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year));
        for (Match match : matchList) { // Iterating through the matchList
            if (match.getDate().equals(searchDate)) { //Checking the match date from the array list with the user entered dates
                JsonNode jsonNode_searchResult = Json.toJson(match); //casting the match object to a Json node
                searchMatchList.add(jsonNode_searchResult);//Adding the Json node to the array list
            }
        }
        ObjectMapper mapper = new ObjectMapper(); //Creating a new object mapper
        JsonNode jsonData_SearchedMatch = mapper.convertValue(searchMatchList, JsonNode.class); //Mapping the arraylist to a Json node
        if(searchMatchList.isEmpty()){ //If the search result is empty
            return ok("No Matched data");
        } else {
            return ok(jsonData_SearchedMatch); // Returning data to the frontend
        }
    }
    //Generate a random Match and send to Frontend----------------------------------------------------------------------
    public Result playRandomMatch() throws IOException {
        footballClubList.clear(); // Clearing the football club arraylist to add new data
        matchList.clear(); // Clearing the match club arraylist to add new data
        loadData(); // Loading the football clubs and match clubs from the text file
        ArrayList<JsonNode> matchDataListRandom = new ArrayList<>(); // Temporary array list to store Json nodes

        int randomTeam1 = 0; // Save the randomly generated index position
        int randomTeam2 = 0; // Save the randomly generated index position
        int min = 0; // Minimum range of football club index position in football club list
        int max = footballClubList.size() - 1; // Maximum range of football club index position in football club list
        int minGoalScore = 0; // Minimum score value range
        int maxGoalScore = 20; // Maximum score value range
        int team1GoalScore = (int)(Math.random() * (maxGoalScore - minGoalScore + 1) + minGoalScore );
        int team2GoalScore = (int)(Math.random() * (maxGoalScore - minGoalScore + 1) + minGoalScore );
        Match match = new Match(); // Creating a match object to save the records
        // Generating a random number between 0 and the number of football clubs in the list
        // It will generate until the random numbers are not same
        while(true){
            randomTeam1 = (int)(Math.random() * (max - min + 1) + min );
            randomTeam2 = (int)(Math.random() * (max - min + 1) + min );
            if(randomTeam1==randomTeam2){
                randomTeam2 = (int)(Math.random() * (max - min + 1) + min );
            } else {
                break;
            }
        }
        // Generating a random date
        int dateDay = (int) (Math.random() * (31 - 1 + 1) + 1); // Generating a random day
        int dateMonth = (int) (Math.random()* (12 - 1 + 1) + 1); // Generating a random month
        int dateYear = (int) (Math.random()* (2021 - 2020 + 1) + 2020); // Generating a random year
        Date date = new Date(dateDay,dateMonth,dateYear); // Creating the date object and passing the values
        match.setDate(date); // Adding date to the match object
        match.setTeam1(footballClubList.get(randomTeam1).getClubName()); // Setting the Team 1 name to the match object
        match.setTeam1GoalScore(team1GoalScore); // Setting the Team 1 score to the match object
        match.setTeam2(footballClubList.get(randomTeam2).getClubName()); // Setting the Team 2 name to the match object
        match.setTeam2GoalScore(team2GoalScore); // Setting the Team 2 score to the match object
        matchList.add(match); // adding the match records to the match list
        footballClubList.get(randomTeam1).setGoalScored(footballClubList.get(randomTeam1).getGoalScored() + team1GoalScore); // updating the new club score
        footballClubList.get(randomTeam1).setNumberOfPlayedMatches(footballClubList.get(randomTeam1).getNumberOfPlayedMatches() + 1); // adding played matches count
        footballClubList.get(randomTeam2).setGoalScored(footballClubList.get(randomTeam2).getGoalScored() + team2GoalScore); // updating the new club score
        footballClubList.get(randomTeam2).setNumberOfPlayedMatches(footballClubList.get(randomTeam2).getNumberOfPlayedMatches() + 1); // adding played matches count

        // randomTeam 1
        if (team1GoalScore > team2GoalScore) {
            footballClubList.get(randomTeam1).setWins(footballClubList.get(randomTeam1).getWins() + 1); // adding the wining details to Team 1
            footballClubList.get(randomTeam1).setNumberOfPoints(footballClubList.get(randomTeam1).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(randomTeam2).setDefeats(footballClubList.get(randomTeam2).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(randomTeam1).setGoalsReceived(footballClubList.get(randomTeam1).getGoalsReceived() +  // adding goals received for Team 1
                    (team1GoalScore - team2GoalScore));

        } else if (team1GoalScore < team2GoalScore) {
            footballClubList.get(randomTeam2).setWins(footballClubList.get(randomTeam2).getWins() + 1); // adding the wining details to Team 2
            footballClubList.get(randomTeam2).setNumberOfPoints(footballClubList.get(randomTeam2).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(randomTeam1).setDefeats(footballClubList.get(randomTeam1).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(randomTeam2).setGoalsReceived(footballClubList.get(randomTeam2).getGoalsReceived() +  // adding goals received for Team 1
                    (team2GoalScore - team1GoalScore));
        } else {
            footballClubList.get(randomTeam1).setDraws(footballClubList.get(randomTeam1).getDraws() + 1); //adding draw details to Both Teams
            footballClubList.get(randomTeam1).setNumberOfPoints(footballClubList.get(randomTeam1).getNumberOfPoints() + 1); // adding 1 point for draw match
            footballClubList.get(randomTeam2).setDraws(footballClubList.get(randomTeam2).getDraws() + 1); // adding draw details
            footballClubList.get(randomTeam2).setNumberOfPoints(footballClubList.get(randomTeam2).getNumberOfPoints() + 3); // adding 1 point for draw match
        }
        saveData(); //Saving all the updated match and football club data into the text file
        JsonNode jsonNode = Json.toJson(match); // Casting match object to Json node
        matchDataListRandom.add(jsonNode); //Adding jsonNode of match to the array list
        ObjectMapper mapper = new ObjectMapper(); //Creating a new Object Mapper
        JsonNode jsonData_RandomMatchResults = mapper.convertValue(matchDataListRandom, JsonNode.class); //Mapping the arraylist to a Json Node
        return ok(jsonData_RandomMatchResults); //Returning data to the frontend
    }
}
