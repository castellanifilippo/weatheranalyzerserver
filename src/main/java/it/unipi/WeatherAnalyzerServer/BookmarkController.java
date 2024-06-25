/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer;

import com.google.gson.Gson;
import it.unipi.WeatherAnalyzerServer.utility.Response;
import it.unipi.WeatherAnalyzerServer.repository.BookmarkRepository;
import it.unipi.WeatherAnalyzerServer.repository.SessionRepository;
import it.unipi.WeatherAnalyzerServer.table.Bookmark;
import it.unipi.WeatherAnalyzerServer.table.Session;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Filippo
 */

@Controller
@RequestMapping(path="/bookmark")
public class BookmarkController {
    
    private static final Logger logger = LogManager.getLogger(BookmarkController.class);
    
    //Inizializzo i repository
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private SessionRepository sessionRepository;
    
    //Funzione che aggiunge un nuovo bookmark
    @PostMapping(path="/add")
    public @ResponseBody Response add(@RequestBody Bookmark bookmarkRequest){
        Response response;
        //Il token viene passato attraverso Bookmark.username
        Session session = sessionRepository.findByToken(bookmarkRequest.getUsername());
        if(session == null || session.getExpiredate().before(new Date())){
            response = new Response(401, "Unauthorized");
            logger.info("add refused for unauthorized");
            return response;
        }
        bookmarkRequest.setUsername(session.getUsername());
        //Massimo 10 bookmark a persona
        if(bookmarkRepository.countByUsername(bookmarkRequest.getUsername()) < 10){
            bookmarkRepository.save(bookmarkRequest);
            response = new Response(200, "Bookmark added");
            logger.info(bookmarkRequest.getUsername() + " added a bookmark");
        }else{
            response = new Response(400, "Too many bookmarks");
            logger.info("add refused for too many bookmarks");
        }
        return response;
    }
    
    //Funzione che rimuove uno dei propri bookmarks
    @PostMapping(path="/remove")
    public @ResponseBody Response remove(@RequestBody Bookmark bookmarkRequest){
        Response response;
        //Il token viene passato attraverso Bookmark.username
        Session session = sessionRepository.findByToken(bookmarkRequest.getUsername());
        if(session == null || session.getExpiredate().before(new Date())){
            response = new Response(401, "Unauthorized");
            logger.info("remove refused for unauthorized");
        }else{
            bookmarkRequest.setUsername(session.getUsername());
            bookmarkRepository.delete(bookmarkRequest);
            response = new Response(200, "Bookmark Removed");
            logger.info(bookmarkRequest.getUsername() + " removed a bookmark");
        }
        return response;
    }
    
    //Funzione che restituisce i propri bookmarks
    @PostMapping(path="/get")
    public @ResponseBody Iterable<Bookmark> get(@RequestBody String tokenJson){
        Gson gson = new Gson();
        String token = gson.fromJson(tokenJson, String.class);
        String username = sessionRepository.findByToken(token).getUsername();
        logger.info(token + " requsted the bookmarks");
        return bookmarkRepository.findByUsername(username);
    }
}
