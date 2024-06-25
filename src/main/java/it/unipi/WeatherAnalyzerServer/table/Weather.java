/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.table;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Filippo
 */

@Entity
@Table(name="weather", schema="weather")
public class Weather implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name="latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @Column(name="temperature")
    private Double temperature;
    @Column(name="humidity")
    private Integer humidity;
    @Column(name="precipitation")
    private Double precipitation;
    @Column(name="snow")
    private Boolean snow;
    @Column(name="datetime")
    private Date datetime;
    @Column(name="city")
    private String city;

    public Integer getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Boolean getSnow() {
        return snow;
    }

    public void setSnow(Boolean snow) {
        this.snow = snow;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Weather(Integer id, Double latitude, Double longitude ,Double temperature, Integer humidity, Double precipitation, Boolean snow, Date datetime, String city) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.snow = snow;
        this.datetime = datetime;
        this.city = city;
    }

    public Weather() {
    }
    
}
