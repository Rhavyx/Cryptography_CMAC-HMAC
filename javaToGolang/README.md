```
package br.com.padotec.app.smartlockservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;

@SpringBootTest
@ActiveProfiles("docker")
class TicketUtilsTest {

    TicketUtils ticketUtils;

    public static final String TICKET1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMxMjMifQ.uvHkIXx2jk9sydCi_EaC7XpgZ_6ylBFkLkNSkxq_EsU";
    public static final String TICKET2 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMxMjMifQ.ziPTZgC6Wrmnkb-8tSy1PX3Utc_jzQsDBAYQ0ghivGE";

    public static final String TICKET_GO = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjMxMjMifQ.gypSc8pZAs0bq13NNT6hJplklBHfKrrse7aRelvyxNM";

    private static final String SEC1 = "PADOPADOPADOPADO";
    private static final String SEC2 = "2021202120212021";
    private static final String SEC3 = "UEFET1BBRE9QQURPUEFETw==";

    @Autowired
    Environment environment;

    @BeforeEach
    void setUp(){
        ticketUtils = new TicketUtils(environment);
    }

    @Test
    void generateTicketWithSecret1(){

        byte[] bytesSec1 = SEC1.getBytes();
        System.out.println(bytesSec1);
        String encodeToString = Base64.getEncoder().encodeToString(bytesSec1);
        System.out.println(encodeToString);

        String user1 = ticketUtils.generateTicket("123123", SEC3);
        String user2 = ticketUtils.generateTicket("123123", encodeToString);

        assertThat(user1).isEqualTo(user2);
    }

    @Test
    void generateTicketWithSecret2(){
        String t = ticketUtils.generateTicket("123123", SEC2);

        System.out.println("TOKEN 2: "+ t);
    }

    @Test
    void getTicketUserId1() {
    String u = ticketUtils.getTicket(TICKET1, SEC1);
        System.out.println(u);
    }

    @Test
    void getTicketUserId2() {
        String u = ticketUtils.getTicket(TICKET2, SEC2);
        System.out.println(u);
    }

    @Test
    void getTicketUserId3(){
        String u = ticketUtils.getTicket(TICKET_GO, SEC3);
        System.out.println(u);
    }

}
```