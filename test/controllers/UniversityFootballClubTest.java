package controllers;
import models.entities.SportsClub;
import models.entities.UniversityFootballClub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniversityFootballClubTest {
    String test_clubName = "Manchester";
    String test_clubLocation = "London";
    int test_wins = 12;
    int test_draws = 2;
    int test_defeats = 3;
    int test_goalsReceived = 20;
    int test_goalScored = 32;
    int test_numberOfPoints = 40;
    int test_numberOfPlayedMatches = 15;
    String test_universityName = "UOW";
    SportsClub test_universityFootballClub = new UniversityFootballClub(test_clubName, test_clubLocation, test_wins, test_draws, test_defeats,
            test_goalsReceived, test_goalScored, test_numberOfPoints, test_numberOfPlayedMatches, test_universityName);

    @Test
    public void UnitTestClubName() {assertEquals(test_clubName, test_universityFootballClub.getClubName());}
    @Test
    public void UnitTestClubLocation() {assertEquals(test_clubLocation, test_universityFootballClub.getClubLocation());}
    @Test
    public void UnitTestWins() {assertEquals(test_wins,((UniversityFootballClub) test_universityFootballClub).getWins());}
    @Test
    public void UnitTestDraws() {assertEquals(test_draws,((UniversityFootballClub) test_universityFootballClub).getDraws());}
    @Test
    public void UnitTestDefeats() {assertEquals(test_defeats,((UniversityFootballClub) test_universityFootballClub).getDefeats());}
    @Test
    public void UnitTestGoalReceived() {assertEquals(test_goalsReceived,((UniversityFootballClub) test_universityFootballClub).getGoalsReceived());}
    @Test
    public void UnitTestGoalScored() {assertEquals(test_goalScored,((UniversityFootballClub) test_universityFootballClub).getGoalScored());}
    @Test
    public void UnitTestNumberOfPoints() {assertEquals(test_numberOfPoints,((UniversityFootballClub) test_universityFootballClub).getNumberOfPoints());}
    @Test
    public void UnitTestNumberOfPlayedMatches() {assertEquals(test_numberOfPlayedMatches,((UniversityFootballClub) test_universityFootballClub).getNumberOfPlayedMatches());}
    @Test
    public void UnitTestUniversityName() {assertEquals(test_clubName, test_universityFootballClub.getClubName());}


}