package com.tharbad.tharbad_api.security;

import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.MalformedJwtException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.tharbad.security.JwtUtil;

public class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        jwtUtil.setSecret("ma_cle_secrete");
    }

    @Test
    public void testGenerateToken() {
        // Simule un utilisateur
        UserDetails userDetails = User.withUsername("test@test.com")
                .password("123456")
                .roles("USER")
                .build();

        // Simule l'authentification
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(userDetails.getUsername());

        // Génère le token
        String token = jwtUtil.generateToken(authentication);
        assertNotNull(token);

        // Vérifie que le token contient l'email de l'utilisateur
        String username = jwtUtil.extractUsername(token);
        assertEquals(userDetails.getUsername(), username);

        // Vérifie que le token est valide
        boolean isValid = jwtUtil.validateToken(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    public void testValidateToken() {
        // Simule un utilisateur
        UserDetails userDetails = User
            .withUsername("sophie@durand.com")
            .password("Soso1234")
            .roles("USER")
            .build();

    //Génère un mauvais token
    String badToken = "Mauvais Token";

    assertThrows(MalformedJwtException.class, () -> {
    jwtUtil.extractUsername(badToken);
});
    }
}
