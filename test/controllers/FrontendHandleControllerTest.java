package controllers;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class FrontendHandleControllerTest extends WithApplication {
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void UnitTestListFootballClubs() {
        Http.RequestBuilder test_request = new Http.RequestBuilder().method(GET).uri("/FootballClub");
        Result test_result = route(app, test_request);
        assertEquals(OK, test_result.status());
    }
    @Test
    public void UnitTestListMatchData() {
        Http.RequestBuilder test_request = new Http.RequestBuilder().method(GET).uri("/MatchList");
        Result test_result = route(app, test_request);
        assertEquals(OK, test_result.status());
    }
    @Test
    public void UnitTestPlayRandomMatch() {
        Http.RequestBuilder test_request = new Http.RequestBuilder().method(GET).uri("/RandomMatch");
        Result test_result = route(app, test_request);
        assertEquals(OK, test_result.status());
    }
    @Test
    public void UnitTestSearchMatch() {
        int day = 12;
        int month = 12;
        int year = 2020;
        Http.RequestBuilder test_request = new Http.RequestBuilder().method(GET).uri("/Getmatch?day="+day+"&month="+month+"&year="+year);
        Result test_result = route(app, test_request);
        assertEquals(OK, test_result.status());
    }
}