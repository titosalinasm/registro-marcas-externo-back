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

import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.result.ClsUsuarioResult;
import pe.gob.indecopi.service.ClsUsuarioService;

//@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@CrossOrigin(origins={"*"}, maxAge=3600L)
@RequestMapping({"/usuario"})
public class ClsUsuarioController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1347969165673042652L;

	public Logger logger = Logger.getLogger(ClsUsuarioController.class);
	
	@Autowired
	private ClsUsuarioService objConnUsuarioService;

	@RequestMapping(method = RequestMethod.POST, path = "/usuario", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<ClsUsuarioResult> doUsuario(@RequestBody ClsUsuarioBean objUsuario) {
		logger.info("doUsuario()");
		return ResponseEntity.ok().body(objConnUsuarioService.doUsuario(objUsuario)) ;
	}


}
