package pe.gob.indecopi.util;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ClsSwagger2Config implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3321318431445593261L;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors
			.basePackage("pe.gob.indecopi.controller"))
			.paths(PathSelectors.any())
			.build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot appTodosValidadorDocs REST API ")
				.description("REST API del servicio de registro y consulta de documentos firmados digitalmente por el Indecopi")
				.contact(new Contact("César Polanco", "http://www.indecopi.gob.pe", "cpolanco@indecopi.gob.pe"))
				.license("INDECOPI - Todos los derechos reservador")
				.licenseUrl("https://www.indecopi.gob.pe")
				.version("1.0")
				.build();

	}
}
