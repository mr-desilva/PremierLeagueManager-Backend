package controllers;
import models.entities.Date;
import models.entities.Match;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchTest {
    String test_team1 = "Manchester";
    String test_team2 = "United";
    int test_team1GoalScore = 12;
    int test_team2GoalScore = 16;
    Date test_date = new Date(12,12,2020);
    Match test_match = new Match(test_team1, test_team2, test_team1GoalScore, test_team2GoalScore, test_date);

    @Test
    public void UnitTestTeam1() {assertEquals(test_team1, test_match.getTeam1());}

    @Test
    public void UnitTestTeam2() {assertEquals(test_team2, test_match.getTeam2());}

    @Test
    public void UnitTestTeam1GoalScore() {assertEquals(test_team1GoalScore, test_match.getTeam1GoalScore());}

    @Test
    public void UnitTestTeam2GoalScore() {assertEquals(test_team2GoalScore, test_match.getTeam2GoalScore());}

}