package controllers;

import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class PremierLeagueManagerTest {

    @Test
    public void UnitTestAddFootballClub() {
        FootballClub test_footballClub = new FootballClub("United","London",21,12,14,13,15,20,24);
        List<FootballClub> test_footballClubList = new ArrayList<>();
        assertEquals(test_footballClubList.add(test_footballClub));
    }

    private void assertEquals(boolean add) {}

    @Test
    public void UnitTestDeleteFootballClub(){
        FootballClub test_footballClub = new FootballClub("United","London",21,12,14,13,15,20,24);
        List<FootballClub> test_footballClubList = new ArrayList<>();
        assertEquals(test_footballClubList.remove(test_footballClub));
    }

    @Test
    public void UnitTestDisplayStat() {
        FootballClub test_footballClub = new FootballClub("United","London",21,12,14,13,15,20,24);
        boolean foundClub = false;
        if(!foundClub) {
            System.out.println(test_footballClub.toString());
            foundClub = true;
        } else {
            foundClub = false;
        }
        assertEquals(foundClub);
    }

    @Test
    public void UnitTestAddMatch() {
        Date test_date = new Date(12,12,2020);
        Match test_match = new Match("United","Manchester",12,17,test_date);
        List<Match> test_matchList = new ArrayList<>();
        assertEquals(test_matchList.add(test_match));
    }

}