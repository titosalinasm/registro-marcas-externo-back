package pe.gob.indecopi.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroValidacionBean;
import pe.gob.indecopi.result.ClsClaseResult;
import pe.gob.indecopi.result.ClsLstGeneralResult;
import pe.gob.indecopi.service.ClsGeneralService;


@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@RequestMapping({"/general"})
public class ClsGeneralController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7769818473226706791L;

	public Logger logger = Logger.getLogger(ClsGeneralController.class);
	
	@Autowired
	private ClsGeneralService objConn;
	
	@RequestMapping(method = RequestMethod.POST, path = "/lstgeneral",  produces = "application/json")
	public @ResponseBody ResponseEntity<ClsLstGeneralResult> doGeneral(@RequestBody ClsFiltroConfiguracionBean objConfiguracion) {

		return ResponseEntity.ok().body(objConn.doGeneral()) ;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lstclase",  produces = "application/json")
	public @ResponseBody ResponseEntity<ClsClaseResult> doLstClase(@RequestBody ClsFiltroClaseBean objFiltroClase) {

		return ResponseEntity.ok().body(objConn.dolstClase(objFiltroClase));
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/validaexpediente",  produces = "application/json")
	public @ResponseBody ResponseEntity<?> doValidaExpediente(@RequestBody ClsFiltroValidacionBean objFiltro) {

		return ResponseEntity.ok().body(objConn.doValidarExpediente(objFiltro));
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/validacertificado",  produces = "application/json")
	public @ResponseBody ResponseEntity<?> doValidaCertificado(@RequestBody ClsFiltroValidacionBean objFiltro) {

		return ResponseEntity.ok().body(objConn.doValidarCertificado(objFiltro));
	}

}
