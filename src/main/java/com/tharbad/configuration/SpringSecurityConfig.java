package com.tharbad.configuration;

import org.springframework.security.core.userdetails.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SpringSecurityConfig.class); //Logger pour enregistrer les messages de débogage

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //Methode qui permet de configurer la chaine de filtres de sécurité | HttpSecurity est une classe qui permet de configurer la sécurité  liée aux requetes HTTP
        log.debug("SpringSecurityConfig.filterChain() called"); //Enregistre un message de débogage lorsque la méthode est appelée
        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/admin").hasRole("ADMIN"); //Autorise l'accès à la page /admin uniquement aux utilisateurs ayant le rôle ADMIN
            auth.requestMatchers("/user").hasRole("USER"); //Autorise l'accès à la page /user uniquement aux utilisateurs ayant le rôle USER
            auth.anyRequest().authenticated(); //Autorise l'accès à toutes les autres requetes uniquement aux utilisateurs authentifiés
        }).formLogin(Customizer.withDefaults()).build(); //Configurer la page de login par défaut;
    }

    // NOTE POUR PLUS TARD - Il faudra gerer les roles dvia la base de données
    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user")) //Encode le mot de passe "user" avec l'encodeur BCrypt
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin")) //Encode le mot de passe "admin" avec l'encodeur BCrypt
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin); //Création d'un utilisateur en mémoire avec le nom d'utilisateur "user" ou "admin" et le mot de passe "password" et le rôle "USER" ou "ADMIN"
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Création d'un encodeur de mot de passe BCrypt
    }
}
