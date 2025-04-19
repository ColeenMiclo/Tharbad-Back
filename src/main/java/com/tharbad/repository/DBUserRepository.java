package com.tharbad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharbad.model.DBUser;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    DBUser findByUsername(String username); //Méthode pour trouver un utilisateur par son nom d'utilisateur

}
