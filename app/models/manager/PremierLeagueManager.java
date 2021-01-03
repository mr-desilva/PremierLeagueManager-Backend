package models.manager;
import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager  {
    final static int maximumClubs = 20; //Maximum number of clubs can be added
    private List<FootballClub> footballClubList = new ArrayList<>(maximumClubs); //Arraylist to save clubs
    private List<Match> matchList = new ArrayList<>(); //Arraylist to save played matches

    @Override
    public void addClub(FootballClub footballClub) {
        if(footballClubList.size()==maximumClubs){
            System.out.println("Clubs are full!!");
            return;
        }
        // use this to check if the club is already in the list
        if (footballClubList.contains(footballClub)) {
            System.out.println("Club already in the list");
            return;
        }
        //if not it will add to the football club list
        footballClubList.add(footballClub);
        Collections.sort(footballClubList);
        System.out.println("New Club Added Successfully - Club Name = "+footballClub.getClubName()+" | Club Location = "+footballClub.getClubLocation()+
    " | wins = "+footballClub.getWins()+" | draws = "+footballClub.getDraws()+" | defeats = "+footballClub.getDefeats()+" | goal scored = "+footballClub.getGoalScored()+
                " | goal received = "+footballClub.getGoalsReceived()+" |points = "+footballClub.getNumberOfPoints()+" | Match Count = "+footballClub.getNumberOfPlayedMatches());
    }

    @Override
    public void deleteClub(String clubName) {
        boolean foundClub = false;
        if(footballClubList.isEmpty()){
            System.out.println("Club list is empty!");
            return;
        }
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().equals(clubName)) {
                System.out.println("Club deleted Successfully - Club Name = "+ footballClubList.get(i).getClubName()+" | Club Location = "+footballClubList.get(i).getClubLocation()+
                        " | wins = "+footballClubList.get(i).getWins()+" | draws = "+footballClubList.get(i).getDraws()+" | defeats = "+footballClubList.get(i).getDefeats()+" | goal scored = "+footballClubList.get(i).getGoalScored()+
                        " | goal received = "+footballClubList.get(i).getGoalsReceived()+" |points = "+footballClubList.get(i).getNumberOfPoints()+" | Match Count = "+footballClubList.get(i).getNumberOfPlayedMatches());
                footballClubList.remove(i);
                foundClub = true;
                Collections.sort(footballClubList);
                break;
            }
        }
        if (!foundClub) {
            System.out.println("Football Club" + clubName + " is not available in the list");
        }
    }

    @Override
    public void displayStat(String clubName) {
        refreshData(); // Refresh the club stats with the Gui operations
        boolean foundClub = false;
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().toLowerCase().equals(clubName.toLowerCase())) {
                System.out.println(footballClubList.get(i).toString());
                foundClub = true;
                break;
            }
        }
        if (!foundClub) {
            System.out.println("Football Club" + clubName + " is not available in the list");
        }

    }

    @Override
    public void displayPLMtable() {
        //"%-15s %-8s %-12s %-12s %-12s %-18s %-16s %-16s %-16s%n"
        //%-18s %-13s %-16s %-18s %-20s %-22s %-23s %-17s %-13s%n console
        refreshData(); // Refresh the club stats with the Gui operations
        Collections.sort(footballClubList);
        System.out.println("Team\t\t " + "Played\t\t" + "Wins\t\t" + "Draw\t\t" + "Defeats\t\t" + "Goals Received\t\t" + "Goal Scored\t\t" + "Goal difference\t\t" + "Points");
        for (FootballClub footballClub1 : footballClubList) {
            System.out.printf("%-15s %-8s %-12s %-12s %-12s %-18s %-16s %-16s %-16s%n", footballClub1.getClubName(), footballClub1.getNumberOfPlayedMatches(), footballClub1.getWins(),
                    footballClub1.getDraws(), footballClub1.getDefeats(), footballClub1.getGoalsReceived(), footballClub1.getGoalScored(),
                    footballClub1.getGoalScored() - footballClub1.getGoalsReceived(),
                    footballClub1.getNumberOfPoints());
        }
    }

    @Override
    public void addMatch(String footballClub1, String footballClub2) {
        refreshData(); // Refresh the club stats with the Gui operations
        boolean foundClub1 = false; // to check whether the team 1 is existing in the PML table
        boolean foundClub2 = false; // to check whether the team 2 is existing in the PML table
        int indexOfTeam1 = 0; // Save the index of the match team 1
        int indexOfTeam2 = 0; // Save the index of the match team 2
        int team1GoalScore = 0; // Save the goal score of the match team 1
        int team2GoalScore = 0; // Save the goal score of the match team 2

        Match match = new Match(); // Creating the match object to store the match details

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().toLowerCase().equals(footballClub1.toLowerCase())) {
                indexOfTeam1 = i;
                foundClub1 = true;
                String homeTeamName = footballClub1.substring(0, 1).toUpperCase() + footballClub1.substring(1); //Setting club name first letter to be capital
                match.setTeam1(homeTeamName); // adding team 1 to the match array list
            } else if (footballClubList.get(i).getClubName().toLowerCase().equals(footballClub2.toLowerCase())) {
                indexOfTeam2 = i;
                foundClub2 = true; // adding the team 2 to the match array list
                String opponentTeam = footballClub2.substring(0, 1).toUpperCase() + footballClub2.substring(1); //Setting club name first letter to be capital
                match.setTeam2(opponentTeam);
            }
        }
        if (!foundClub1 && !foundClub2) {
            System.out.println(footballClub1 + " and " + footballClub2 + " not in the PML Table!!, Please check again");
            return;
        } else if (!foundClub1) {
            System.out.println(footballClub1 + " is not in the PML Table!!, Please check again");
            return;
        } else if (!foundClub2) {
            System.out.println(footballClub2 + " is not in the PML Table!!, Please check again");
            return;
        }


        // Getting the played match date
        try {
            System.out.println("Please enter the date of the played match");
            System.out.println("Format : DD/MM/YYYY");
            System.out.println("Enter the Day : ");
            int day = scanner.nextInt();
            System.out.println("Enter the Month : ");
            int month = scanner.nextInt();
            System.out.println("Enter the Year : ");
            int year = scanner.nextInt();
            models.entities.Date date = new Date(day, month, year);
            match.setDate(date); //adding the match date
        } catch (InputMismatchException e) {
            System.out.println("Invalid format!!, please try again!");
            return;
        }
        while (true) {
            try {
                System.out.println("How many goal scored " + footballClub1 + " in the match : ");
                team1GoalScore = scanner.nextInt(); // getting the club score
                match.setTeam1GoalScore(team1GoalScore); // setting the match score
                footballClubList.get(indexOfTeam1).setGoalScored(footballClubList.get(indexOfTeam1).getGoalScored() + team1GoalScore); // updating the new club score
                footballClubList.get(indexOfTeam1).setNumberOfPlayedMatches(footballClubList.get(indexOfTeam1).getNumberOfPlayedMatches() + 1); // adding played matches count
                System.out.println(footballClub1 + " match details updated!"); // output message for completion
                break;
            } catch (InputMismatchException e) { // checking the type error
                System.out.println("Please enter a valid number!!");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("How many goal scored " + footballClub2 + " in the match : ");
                team2GoalScore = scanner.nextInt(); // getting the club score
                match.setTeam2GoalScore(team2GoalScore); //setting the match score
                footballClubList.get(indexOfTeam2).setGoalScored(footballClubList.get(indexOfTeam2).getGoalScored() + team2GoalScore); // updating the new club score
                footballClubList.get(indexOfTeam2).setNumberOfPlayedMatches(footballClubList.get(indexOfTeam2).getNumberOfPlayedMatches() + 1); // adding played matches count
                System.out.println(footballClub2 + " match details updated!"); // output message for completion
                break;
            } catch (InputMismatchException e) { // checking the type error
                System.out.println("Please enter a valid number!!");
                scanner.nextLine();
            }
        }
        matchList.add(match); // adding entered match details to the array list

        // adding the win/defeat/draw statistics
        if (team1GoalScore > team2GoalScore) {
            footballClubList.get(indexOfTeam1).setWins(footballClubList.get(indexOfTeam1).getWins() + 1); // adding the wining details
            footballClubList.get(indexOfTeam1).setNumberOfPoints(footballClubList.get(indexOfTeam1).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(indexOfTeam2).setDefeats(footballClubList.get(indexOfTeam2).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(indexOfTeam1).setGoalsReceived(footballClubList.get(indexOfTeam1).getGoalsReceived() +  // adding goals received for Team 1
                    (team1GoalScore - team2GoalScore));
        } else if (team1GoalScore < team2GoalScore) {
            footballClubList.get(indexOfTeam2).setWins(footballClubList.get(indexOfTeam2).getWins() + 1); // adding the wining details
            footballClubList.get(indexOfTeam2).setNumberOfPoints(footballClubList.get(indexOfTeam2).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(indexOfTeam1).setDefeats(footballClubList.get(indexOfTeam1).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(indexOfTeam2).setGoalsReceived(footballClubList.get(indexOfTeam2).getGoalsReceived() +  // adding goals received for Team 2
                    (team2GoalScore - team1GoalScore));
        } else {
            footballClubList.get(indexOfTeam1).setDraws(footballClubList.get(indexOfTeam1).getDraws() + 1); //adding draw details
            footballClubList.get(indexOfTeam1).setNumberOfPoints(footballClubList.get(indexOfTeam1).getNumberOfPoints() + 1); // adding 1 point for draw match
            footballClubList.get(indexOfTeam2).setDraws(footballClubList.get(indexOfTeam2).getDraws() + 1); // adding draw details
            footballClubList.get(indexOfTeam2).setNumberOfPoints(footballClubList.get(indexOfTeam2).getNumberOfPoints() + 3); // adding 1 point for draw match
        }
    }

    @Override
    public void saveData(String fileName) throws IOException {
        Collections.sort(footballClubList); // Sorting the clubs before saving
        FileOutputStream fileOutputStream = new FileOutputStream((fileName)); // Creating a file output stream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); // Creating object output stream and passing the file output stream

        for(SportsClub footballClub : footballClubList) { // saving footballClub objects to the file
            objectOutputStream.writeObject(footballClub);
        }
        for(Match match : matchList) { // saving the match objects to the file
            objectOutputStream.writeObject(match);
        }
    }


    @Override
    public void loadData(String fileName) {
        try(FileInputStream fileInputStream = new FileInputStream(fileName); // creating a file input stream
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) // creating a object input stream and passing the file input stream
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
        catch (FileNotFoundException fileNotFoundException) //Exception for file not found error
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

    // This method will refresh the data with Gui operations
    public void refreshData() {
        matchList.clear(); //Clearing the arraylist to load updated data
        footballClubList.clear(); //Clearing the arraylist to load updated data
        loadData("savedData.txt"); //Loading the data
    }

    // This method will compile and configure the servers
    @Override
    public void serverConfig() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(); // Creating a new process builder
            processBuilder.command("cmd.exe", "/c", "sbt run"); // Passing the command to process builder to run on cmd, to compile the backend and frontend
            Process process = processBuilder.start(); // Starting the process builder
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method is to call the backend and frontend
    @Override
    public void launchGui() {
        String url_open_AngularFrontend = "http://localhost:4200/footballClubList"; // Url for frontend
        String url_open_PlayBackend = "http://localhost:9000"; // Url for backend
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open_AngularFrontend)); // Open the frontend url through browser
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open_PlayBackend)); // Open the backend url through browser
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








