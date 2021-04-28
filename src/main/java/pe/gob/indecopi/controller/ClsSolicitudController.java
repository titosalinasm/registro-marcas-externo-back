package pe.gob.indecopi.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.result.ClsClaseResult;
import pe.gob.indecopi.service.ClsRegistroServiceI;

@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@RequestMapping({"/solicitud"})
public class ClsSolicitudController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6483351352091677087L;
	
	
	@Autowired
	private ClsRegistroServiceI objConn;

	@RequestMapping(method = RequestMethod.POST, path = "/registro",  produces = "application/json")
	public @ResponseBody ResponseEntity<?> doLstClase(@RequestBody ClsSolicitudBean objSolicitud) {
		return ResponseEntity.ok().body(objConn.doRegistr2(objSolicitud));
	}
}
