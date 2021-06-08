package dev.evertonsavio.app.utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TicketUtils {

    private static Environment environment;

    public TicketUtils(Environment environment) {
        TicketUtils.environment = environment;
    }

    /**
     * @apiNote Extract userId from Token
     * @param token
     * @return
     */
    /*==================================================================================================================
    ==================================================================================================================*/
    private String getUserId(String token) {

        if(token == null){
            throw new RuntimeException("É necessário um token Válido")   ;
        }
        String tokenReceived = token
                .replace("Bearer", "");

        String userId = Jwts.parser()
                .setSigningKey(environment.getProperty("authorization.token.secret"))
                .parseClaimsJws(tokenReceived)
                .getBody()
                .getSubject();

        System.out.println("Id do Usuário e: "
                + userId);

        return userId;
    }

    /**
     * Generate ticket token with HS256
     * @param userId
     * @param acc
     * @return
     */
    /*==================================================================================================================
    ==================================================================================================================*/
    public static String generateTicketToken(String userId, String acc){

        String ticketSecret = getStringTokenType(acc);

        String token = Jwts.builder()
                .setSubject(userId)
                //.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7 * 4 * 12 * 5)))
                .signWith(SignatureAlgorithm.HS256, ticketSecret)
                .compact();

        return token;
    }
    /*================================================================================================================*/
    public static String generateTicket(String userId, String tokenSecret){

        Header header = Jwts.header();
        header.setType("JWT");

        String token = Jwts.builder()
                .setSubject(userId)
                .setHeader((Map<String, Object>) header)
                //.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7 * 4 * 12 * 5)))
                .signWith(SignatureAlgorithm.HS256, tokenSecret)
                .compact();

        return token;
    }
    /**
     * TODO CHECK IF TOKEN / TICKET EXPIRED
     * THROW SOME ERROR TO INFORM CLIENT
     */

    /*==================================================================================================================
    ==================================================================================================================*/
    public String getTicket(String token, String secret) {

        if(token == null){
            throw new RuntimeException("É necessário um token Válido");
        }

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /*==================================================================================================================
    ==================================================================================================================*/
    public String getTicketId(String token, String acc) {

        if(token == null){
            throw new RuntimeException("É necessário um token Válido");
        }

        String ticketSecret = getStringTokenType(acc);

        return Jwts.parser()
                .setSigningKey(ticketSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /*==================================================================================================================
    ==================================================================================================================*/
    private static String getStringTokenType(String acc) {
        String ticketSecret = "";

        if (acc.equals(VARConfig.GATEWAY_TICKET)) {
            ticketSecret = environment.getProperty("authorization.ticket.gateway");
        }
        if (acc.equals(VARConfig.USER_TICKET)) {
            ticketSecret = environment.getProperty("authorization.ticket.user");
        }
        return ticketSecret;
    }
}
/*====================================================================================================================*/