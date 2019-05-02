package leCampusServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config_Database {
	
	private String username = "alextang";
	private String password = "jQs-46o.dH3I";
	private String HOST = "95.179.143.217";
	private int PORT = 3306;
	
	@Bean
    public DriverManagerDataSource dataSource() {		
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + username );
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
