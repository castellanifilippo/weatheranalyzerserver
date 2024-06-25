/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.repository;

import it.unipi.WeatherAnalyzerServer.table.Weather;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filippo
 */
public interface WeatherRepository extends CrudRepository<Weather, Integer> {
    Weather findTop1ByCityOrderByDatetimeDesc(String city);
    Weather findTop1ByLatitudeAndLongitudeOrderByDatetimeDesc(Double latidute, Double longitude);
    Iterable<Weather> findTop10ByCityOrderByDatetimeDesc(String city);
    Iterable<Weather> findTop10ByLatitudeAndLongitudeOrderByDatetimeDesc(Double latidute, Double longitude);
}
