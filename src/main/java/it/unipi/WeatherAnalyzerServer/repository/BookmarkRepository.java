/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.repository;

import it.unipi.WeatherAnalyzerServer.table.Bookmark;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filippo
 */
public interface BookmarkRepository extends CrudRepository<Bookmark, Integer>{
    Iterable<Bookmark> findByUsername(String username);
    int countByUsername(String username);
}
