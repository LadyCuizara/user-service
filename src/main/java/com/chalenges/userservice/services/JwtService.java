package com.chalenges.userservice.services;

import java.util.Date;

/**
 * @author lady Cuizara
 */
public interface JwtService {

    String extractUsername(String token);

    Date extractExpiration(String token);

    String generateToken(String username);

    Boolean validateToken(String token, String username);
}
