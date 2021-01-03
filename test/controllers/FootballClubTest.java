package controllers;
import models.entities.FootballClub;
import models.entities.SportsClub;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FootballClubTest {
    String test_clubName = "Manchester";
    String test_clubLocation = "London";
    int test_wins = 12;
    int test_draws = 2;
    int test_defeats = 3;
    int test_goalsReceived = 20;
    int test_goalScored = 32;
    int test_numberOfPoints = 40;
    int test_numberOfPlayedMatches = 15;
    SportsClub test_footballClub = new FootballClub(test_clubName, test_clubLocation, test_wins, test_draws, test_defeats, test_goalsReceived, test_goalScored, test_numberOfPoints, test_numberOfPlayedMatches);

    @Test
    public void UnitTestClubName() {assertEquals(test_clubName, test_footballClub.getClubName());}
    @Test
    public void UnitTestClubLocation() {assertEquals(test_clubLocation, test_footballClub.getClubLocation());}
    @Test
    public void UnitTestWins() {assertEquals(test_wins,((FootballClub) test_footballClub).getWins());}
    @Test
    public void UnitTestDraws() {assertEquals(test_draws,((FootballClub) test_footballClub).getDraws());}
    @Test
    public void UnitTestDefeats() {assertEquals(test_defeats,((FootballClub) test_footballClub).getDefeats());}
    @Test
    public void UnitTestGoalReceived() {assertEquals(test_goalsReceived,((FootballClub) test_footballClub).getGoalsReceived());}
    @Test
    public void UnitTestGoalScored() {assertEquals(test_goalScored,((FootballClub) test_footballClub).getGoalScored());}
    @Test
    public void UnitTestNumberOfPoints() {assertEquals(test_numberOfPoints,((FootballClub) test_footballClub).getNumberOfPoints());}
    @Test
    public void UnitTestNumberOfPlayedMatches() {assertEquals(test_numberOfPlayedMatches,((FootballClub) test_footballClub).getNumberOfPlayedMatches());}





}