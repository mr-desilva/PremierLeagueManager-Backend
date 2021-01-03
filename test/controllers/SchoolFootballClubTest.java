package controllers;
import models.entities.SchoolFootballClub;
import models.entities.SportsClub;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SchoolFootballClubTest {
    String test_clubName = "Manchester";
    String test_clubLocation = "London";
    int test_wins = 12;
    int test_draws = 2;
    int test_defeats = 3;
    int test_goalsReceived = 20;
    int test_goalScored = 32;
    int test_numberOfPoints = 40;
    int test_numberOfPlayedMatches = 15;
    String test_schoolName = "SSC";
    SportsClub test_schoolFootballClub = new SchoolFootballClub(test_clubName, test_clubLocation, test_wins, test_draws, test_defeats,
            test_goalsReceived, test_goalScored, test_numberOfPoints, test_numberOfPlayedMatches, test_schoolName);

    @Test
    public void UnitTestClubName() {assertEquals(test_clubName, test_schoolFootballClub.getClubName());}
    @Test
    public void UnitTestClubLocation() {assertEquals(test_clubLocation, test_schoolFootballClub.getClubLocation());}
    @Test
    public void UnitTestWins() {assertEquals(test_wins,((SchoolFootballClub) test_schoolFootballClub).getWins());}
    @Test
    public void UnitTestDraws() {assertEquals(test_draws,((SchoolFootballClub) test_schoolFootballClub).getDraws());}
    @Test
    public void UnitTestDefeats() {assertEquals(test_defeats,((SchoolFootballClub) test_schoolFootballClub).getDefeats());}
    @Test
    public void UnitTestGoalReceived() {assertEquals(test_goalsReceived,((SchoolFootballClub) test_schoolFootballClub).getGoalsReceived());}
    @Test
    public void UnitTestGoalScored() {assertEquals(test_goalScored,((SchoolFootballClub) test_schoolFootballClub).getGoalScored());}
    @Test
    public void UnitTestNumberOfPoints() {assertEquals(test_numberOfPoints,((SchoolFootballClub) test_schoolFootballClub).getNumberOfPoints());}
    @Test
    public void UnitTestNumberOfPlayedMatches() {assertEquals(test_numberOfPlayedMatches,((SchoolFootballClub) test_schoolFootballClub).getNumberOfPlayedMatches());}
    @Test
    public void UnitTestSchoolName() {assertEquals(test_schoolName,((SchoolFootballClub) test_schoolFootballClub).getSchoolName());}
}