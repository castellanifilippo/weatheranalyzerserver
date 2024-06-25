package it.unipi.WeatherAnalyzerServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherAnalyzerServerApplication {
        
        private static final Logger logger = LogManager.getLogger(WeatherAnalyzerServerApplication.class);
        
        private static String read(String file) throws IOException {
            String content = null;
            try {
                content = Files.readString(Paths.get(file));
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return content;
        }
    
	public static void main(String[] args) {
            
            //Controllo se i ldatabse esiste, senno lo creo
            String url = "jdbc:mysql://localhost:3306";
            try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
               
            String schema = "635466";
            String query = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, schema);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                     logger.info("Schema : OK");
            } else {
                logger.info("Schema : not exist");
                String file = "init.sql";
                String sql = read(file);
                
                
                Statement statement = connection.createStatement();
                String[] queries = sql.split(";");
                for (String q : queries) {
                    statement.execute(q);
                }

                logger.info("Schema : Created");
                    }
                } 
            }
            }catch (Exception ex) {
                logger.error(ex.getMessage());
            }
                
            
            
            
            SpringApplication.run(WeatherAnalyzerServerApplication.class, args);
	}

}
