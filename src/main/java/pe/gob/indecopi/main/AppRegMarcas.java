package pe.gob.indecopi.main;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan("pe.gob.indecopi")
public class AppRegMarcas extends SpringBootServletInitializer implements WebApplicationInitializer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4191143719870778552L;
	static Logger logger = Logger.getLogger(AppRegMarcas.class);
    public static void main( String[] args )
    {
    	try {
			System.setProperty("serverName", InetAddress.getLocalHost().getHostName());
			System.out.println("Nombre del servidor: " + InetAddress.getLocalHost().getHostName());
			System.out.println("Host Address: : " + InetAddress.getLocalHost().getHostAddress());
			
		} catch (UnknownHostException e) {
			System.setProperty("serverName", "ServerName");
			System.out.println("Error al obtener el nombre del servidor:" + e.getMessage());
		} 
		
		logger.info("Inicializa AppRegMarcas");
		SpringApplication.run(AppRegMarcas.class, args);
    }
    
	 @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		 return builder.sources(AppRegMarcas.class);
	 }
    
}
