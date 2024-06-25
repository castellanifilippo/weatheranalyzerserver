/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.repository;

import it.unipi.WeatherAnalyzerServer.table.Session;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filippo
 */
public interface  SessionRepository extends CrudRepository<Session, Integer> {
    Session findByUsername(String Username);
    Session findByToken(String token);
}
