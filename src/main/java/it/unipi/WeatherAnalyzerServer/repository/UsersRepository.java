/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.repository;

import it.unipi.WeatherAnalyzerServer.table.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filippo
 */
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
