package pe.gob.indecopi.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.result.ClsAvisoResult;
import pe.gob.indecopi.result.ClsValidaTramitePagoResult;
import pe.gob.indecopi.service.ClsTramiteSELService;

@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@RequestMapping({"/aviso"})
public class ClsAvisoSELController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8713751312727999967L;

	public Logger logger = Logger.getLogger(ClsAvisoSELController.class);
	
	@Autowired
	private ClsTramiteSELService objConnTramitePagoService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/validaaviso", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<ClsAvisoResult> doAviso(@RequestBody ClsFiltroProcedimientoBean objProcedimiento) {
		logger.info("doAviso()");
		return ResponseEntity.ok().body(objConnTramitePagoService.doAviso(objProcedimiento)) ;
	}

}
