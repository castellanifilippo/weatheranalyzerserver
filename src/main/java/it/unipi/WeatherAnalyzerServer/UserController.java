/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer;


import it.unipi.WeatherAnalyzerServer.utility.LoginResponse;
import it.unipi.WeatherAnalyzerServer.utility.Response;
import it.unipi.WeatherAnalyzerServer.utility.RequestUser;
import com.google.gson.Gson;
import it.unipi.WeatherAnalyzerServer.repository.SessionRepository;
import it.unipi.WeatherAnalyzerServer.repository.UsersRepository;
import it.unipi.WeatherAnalyzerServer.table.Session;
import it.unipi.WeatherAnalyzerServer.table.User;
import java.util.Date;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
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
@RequestMapping(path="/")
public class UserController {
    
    private static final Logger logger = LogManager.getLogger(UserController.class);
    
    //Inizializzo i repository
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SessionRepository sessionRepository;
    
    //Funzione per registrare il proprio account
    @PostMapping(path="/sign-in")
    public @ResponseBody Response sign(@RequestBody RequestUser requestUser){
        Response response;
        //username da massimo 10 caratteri e password da 72
        if(requestUser.username.length() > 10 || requestUser.password.length() > 72 || 
                !requestUser.username.matches("^[a-zA-Z0-9]*$") || requestUser.password.contains(" ")){
            response = new Response(400, "Bad Format");
            logger.info("Sign refused for bad format");
        //verifico se ci sono username uguali
        }else if(usersRepository.findByUsername(requestUser.username) == null){
            User user = new User();
            user.setUsername(requestUser.username);
            user.setPassword(BCrypt.hashpw(requestUser.password, BCrypt.gensalt()));
            usersRepository.save(user);
            response = new Response(200, "Account successfully registered");
            logger.info(requestUser.username + " registered");
        }else{
           response = new Response(403, "This username already exists");
           logger.info("Sign refused for username already exists");
        }
        return response;
    }
    
    //Funzione per il login
    @PostMapping(path="/login")
    public @ResponseBody Response login(@RequestBody RequestUser requestUser){
        Response response;
        //username da massimo 10 caratteri e password da 72
        if(requestUser.username.length() > 10 || requestUser.password.length() > 72 ||
                !requestUser.username.matches("^[a-zA-Z0-9]*$") || requestUser.password.contains(" ")){
            response = new Response(400, "Bad Format");
            logger.info("Login refused for bad format");
        }else{
            response = auth(requestUser);
        }
        return response;
    }
    
    //Funzione che autentica e crea una sessione
    private Response auth(RequestUser requestUser){
        Response response;
        User user = usersRepository.findByUsername(requestUser.username);
        //Controllo le credenziali
        if(user == null){
            response = new Response(401, "Username does not exist");
            logger.info("Login refused for username does not exist");
        }else if(!BCrypt.checkpw(requestUser.password, user.getPassword())){
            response = new Response(401, "Wrong password");
            logger.info("Login refused for wrong password");
        }
        else{
            Session session = sessionRepository.findByUsername(user.getUsername());
            //verifico se no nesiste o è scaduta la sessione
            //in tal caso le creo
            if(session == null){
                session = createSession(user);
            }else if(session.getExpiredate().before(new Date())){
                sessionRepository.delete(session);
                session = createSession(user);
            }
            LoginResponse loginResponse = new LoginResponse("Login successful", session.getToken());
            Gson gson = new Gson();
            String line = gson.toJson(loginResponse);
            response = new Response(200, line);
            logger.info(user.getUsername() + " Logged");
        }
        return response;
    }
        
    private Session createSession(User user){
        Session session = new Session();
        //randomUUID() mette - tra i numeri
        String token = UUID.randomUUID().toString().replace("-", "");
        session.setUsername(user.getUsername());
        session.setToken(token);
        //una sessione dura 1h
        session.setExpiredate(new Date(System.currentTimeMillis() + (3600000)));
        sessionRepository.save(session);
        return session;
    }
    
}
