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
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroUbigeoBean;
import pe.gob.indecopi.result.ClsConfiguracionResult;
import pe.gob.indecopi.result.ClsUbigeoResult;
import pe.gob.indecopi.service.ClsConfiguracionService;
import pe.gob.indecopi.service.ClsUbigeoServiceI;


@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@RequestMapping({"/ubigeo"})
public class ClsUbigeoController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4734850483415073100L;

	public Logger logger = Logger.getLogger(ClsUbigeoController.class);
	
	@Autowired
	private ClsUbigeoServiceI objConnUbigeo;
	
	@RequestMapping(method = RequestMethod.POST, path = "/lstubigeo", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<ClsUbigeoResult> doAviso(@RequestBody ClsFiltroUbigeoBean objFiltro) {
		
		return ResponseEntity.ok().body(objConnUbigeo.doUbigeo(objFiltro)) ;
	}

}
