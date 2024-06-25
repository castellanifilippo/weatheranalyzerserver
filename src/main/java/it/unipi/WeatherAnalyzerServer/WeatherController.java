/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.WeatherAnalyzerServer.repository.CityRepository;
import it.unipi.WeatherAnalyzerServer.table.Weather;
import it.unipi.WeatherAnalyzerServer.repository.WeatherRepository;
import it.unipi.WeatherAnalyzerServer.table.City;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Filippo
 */

@Controller
@RequestMapping(path="/weather")
public class WeatherController {
    
    private static final Logger logger = LogManager.getLogger(WeatherController.class);
    
    //Inizializzo i repository
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private CityRepository cityRepository;
    
    
    //Funzione che ritorna l'ultimo record meteo disponibile su una città
    @GetMapping(path="/city/{name}")
    public @ResponseBody Weather getCityCurrentWeather(@PathVariable("name") String name){
        logger.info("returned weather for " + name);
        return weatherRepository.findTop1ByCityOrderByDatetimeDesc(name);
    }
    
    //Funzione che ritorna l'ultimo record meteo disponibile su una coppia di coordinate
    @GetMapping(path="/coord={lat}&{lon}")
    public @ResponseBody Weather getCoordinatesCurrentWeather(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon){
        logger.info("returned weather for " + lat + " " + lon);
        return weatherRepository.findTop1ByLatitudeAndLongitudeOrderByDatetimeDesc(lat, lon);
    }
    //Funzione che ritorna gli ultimi 10 record meteo disponibile su una città
    @GetMapping(path="/city10/{name}")
    public @ResponseBody Iterable<Weather> getCityLastWeather(@PathVariable("name") String name){
        logger.info("returned weather records for " + name);
        return weatherRepository.findTop10ByCityOrderByDatetimeDesc(name);
    }
    
     //Funzione che ritorna l'ultimo record meteo disponibile su una coppia di coordinate
    @GetMapping(path="/coord10={lat}&{lon}")
    public @ResponseBody Iterable<Weather> getCoordinatesLastWeather(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon){
        logger.info("returned weather records for " + lat + " " + lon);
        return weatherRepository.findTop10ByLatitudeAndLongitudeOrderByDatetimeDesc(lat, lon);
    }
    
    //Funzione che richiede il meteo attuale per una coppia di coordinate al repository open-meteo
    private Weather getWeather(double lat, double lon) throws MalformedURLException, IOException{
        URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lon+"&current=temperature_2m,relative_humidity_2m,precipitation,snowfall&forecast_days=1");
        // https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,precipitation,snowfall&forecast_days=1
        String content = getService(url);
        
        Gson gson = new Gson(); 
        
        JsonElement json = gson.fromJson(content, JsonElement.class);
        JsonObject weatherJson = json.getAsJsonObject().get("current").getAsJsonObject();
        
        Weather weather = new Weather();
        weather.setLatitude(lat);
        weather.setLongitude(lon);
        weather.setTemperature(weatherJson.get("temperature_2m").getAsDouble());
        weather.setHumidity(weatherJson.get("relative_humidity_2m").getAsInt());
        weather.setPrecipitation(weatherJson.get("precipitation").getAsDouble());
        boolean snow = (weatherJson.get("snowfall").getAsDouble() > 0);
        weather.setSnow(snow);
        weather.setDatetime(new Date());
        
        return weather;
    }
    
    //Funzione che richiede e aggiorna il database con l'ultima rilevazione meteo per una coppia di coordiante
    @GetMapping(path="/update/coord={lat}&{lon}")
    public @ResponseBody Weather updateCoordinatesWeather(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon){
        Weather weather;
        try {
            weather = getWeather(lat, lon);
        } catch (IOException ex) {
            return null;
        }
        weatherRepository.save(weather);
        logger.info("added weather for " + lat + " " + lon);
        return weather;
    }
    
    //Funzione che richiede il record di uan città
    private City getCity(String name) throws IOException{
        City city = cityRepository.findByName(name);
        if(city != null){
            return city;
        }
        //Se la città non è presente nel databse la cerca sul repository nominatim
        URL url = new URL("https://nominatim.openstreetmap.org/search?q="+name+"&format=json");
        //https://nominatim.openstreetmap.org/search?q=Pisa&format=json
        String content = getService(url);
        
        Gson gson = new Gson(); 
        
        JsonElement json = gson.fromJson(content, JsonElement.class);
        JsonObject cityJson = json.getAsJsonArray().get(0).getAsJsonObject();
        
        city = new City();
        city.setName(cityJson.get("name").getAsString());
        city.setLatitude(cityJson.get("lat").getAsDouble());
        city.setLongitude(cityJson.get("lon").getAsDouble());
        cityRepository.save(city);
        logger.info("added " + city.getName());
        
        return city;
    }
    
    //Funzione che richiede e aggiorna il database con l'ultima rilevazione meteo per una determinata città
    @GetMapping(path="/update/city/{name}")
    public @ResponseBody Weather updateCityWeather(@PathVariable("name") String name){
        Weather weather;
        try {
            City city = getCity(name);
            weather = getWeather(city.getLatitude(), city.getLongitude());
        } catch (IOException ex) {
            return null;
        }
        weather.setCity(name);
        weatherRepository.save(weather);
        logger.info("added weather for " + name);
        return weather;
    }
    
    private String getService(URL url) throws IOException{
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
    
}
