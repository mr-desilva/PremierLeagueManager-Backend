package models.manager;
import models.entities.FootballClub;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager(); //Creating the Premier League Object

    public static void addClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Name of the club : ");
        String clubName = scanner.nextLine();
        clubName = clubName.substring(0, 1).toUpperCase() + clubName.substring(1); //Setting club name first letter to be capital
        System.out.println("Enter the location(city) of the club : ");
        String clubLocation = scanner.nextLine();
        FootballClub footballClub = new FootballClub(clubName, clubLocation);
        try {
            System.out.println("Do you want to add club statistics ? ");
            System.out.println("1. Yes\n2. No : ");
            int menuOption = scanner.nextInt();
            switch (menuOption)
            {
                case 1:
                    try {
                        //Getting user inputs for the Football Club
                        System.out.println("Enter the number of wins : ");
                        int wins = scanner.nextInt();
                        System.out.println("Enter the number of draws : ");
                        int draws = scanner.nextInt();
                        System.out.println("Enter the number of defeats : ");
                        int defeats = scanner.nextInt();
                        System.out.println("Enter the number of goals received : ");
                        int goalsReceived = scanner.nextInt();
                        System.out.println("Enter the number of goal scored : ");
                        int goalScored = scanner.nextInt();
                        System.out.println("Enter the number of points : ");
                        int numberOfPoints = scanner.nextInt();
                        System.out.println("Enter the number of played matches : ");
                        int numberOfPlayedMatches = scanner.nextInt();
                        //Adding the club data to the Football Club object
                        footballClub.setWins(wins);
                        footballClub.setDraws(draws);
                        footballClub.setDefeats(defeats);
                        footballClub.setGoalsReceived(goalsReceived);
                        footballClub.setGoalScored(goalScored);
                        footballClub.setNumberOfPoints(numberOfPoints);
                        footballClub.setNumberOfPlayedMatches(numberOfPlayedMatches);
                    } catch (InputMismatchException e) {System.out.println("Invalid type entered!!");break;}
                    premierLeagueManager.addClub(footballClub); //Calling the add method from the premier league manager class
                    break;
                case 2:
                    //Adding the club name and the location to the Football Club object
                    footballClub.setClubName(clubName);
                    footballClub.setClubLocation(clubLocation);
                    premierLeagueManager.addClub(footballClub); //Calling the add method from the premier league manager class
                    break;
                default:
                    System.out.println("Invalid menu selection!!"); // Console massage to the invalid menu selection
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option!"); // Console massage to the invalid main menu selection
        }
    }
    public static void deleteClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the football club to remove : ");
        String removeClub = scanner.nextLine();
        premierLeagueManager.deleteClub(removeClub);
    }
    public static void displayStat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the football club name to view statistics : ");
        String displayClub = scanner.nextLine();
        premierLeagueManager.displayStat(displayClub);
    }
    public static void displayPLMtable(){premierLeagueManager.displayPLMtable();}

    public static void addMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the football club name : "); // Getting the club 1 name
        String footballClubTeam1 = scanner.nextLine();
        System.out.println("Enter the second football club name : "); // Getting the club 1 name
        String footballClubTeam2 = scanner.nextLine();
        premierLeagueManager.addMatch(footballClubTeam1,footballClubTeam2);
    }

    public static void updateTextData() throws IOException {
        premierLeagueManager.saveData("savedData.txt"); //Saving the data
    }

    public static void main(String[] args) throws IOException {
        premierLeagueManager.loadData("savedData.txt"); //Loading the data
        premierLeagueManager.serverConfig(); // Calling the server config method
        menuLoop:
        while (true) {
            System.out.println("\n=====Welcome to the Premier League Manager=====");
            System.out.println("Select options from the menu to continue");
            System.out.println("Enter 1 to add a Football Club");
            System.out.println("Enter 2 to delete a Football Club");
            System.out.println("Enter 3 to view statistics for a selected club");
            System.out.println("Enter 4 to view the Premier League Table");
            System.out.println("Enter 5 to add a played match");
            System.out.println("Enter 6 to open the GUI");
            System.out.print("Enter 7 to exit the programme : ");
            Scanner scanner = new Scanner(System.in);
            String menuSelect = scanner.nextLine();
            switch (menuSelect) {
                case ("1"):
                    addClub();
                    updateTextData();
                    break;
                case ("2"):
                    deleteClub();
                    updateTextData();
                    break;
                case ("3"):
                    displayStat();
                    break;
                case ("4"):
                    displayPLMtable();
                    break;
                case ("5"):
                    addMatch();
                    updateTextData();
                    break;
                case ("6"):
                    System.out.println("----Please wait the browser will open ----");
                    premierLeagueManager.launchGui();
                    break;
                case ("7"):
                    System.out.println("Exiting program.......");
                    break menuLoop;
                default:
                    System.out.println("Enter a valid option!!");
            }
        }
    }
}
