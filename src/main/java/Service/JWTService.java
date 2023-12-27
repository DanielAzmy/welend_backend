package Service;

import Enum.SessionStatus;
import Model.Session;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class JWTService {
    private static final String secretKey = "kCQFey/danialg*TD1dL7dumina^;+X>uZE~ufR6/q";

    public static Session generateToken(Long id) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Date exp = calculateExpirationTime(now);
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("welend")
                    .withIssuedAt(now)
                    .withExpiresAt(exp)
                    .withClaim("id", id)
                    .sign(algorithm);
            LocalDateTime dateNow = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return new Session(dateNow, SessionStatus.VALID.toString(), token, id);
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    private static Date calculateExpirationTime(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 3);
        return calendar.getTime();
    }

    public static DecodedJWT parseToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    public static Long checkTokenReturnId(String token) throws SQLException {
        DecodedJWT decodedJWT = parseToken(token);
        if (decodedJWT == null) {
            throw new SQLException("unauthorized");
        }
        return decodedJWT.getClaim("id").asLong();
    }
}
