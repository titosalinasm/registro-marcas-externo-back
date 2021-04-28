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
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.result.ClsValidaTramitePagoResult;
import pe.gob.indecopi.service.ClsTramiteSELService;

@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@RequestMapping({"/tramite"})
public class ClsTramiteSELController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2862601411362752952L;

	public Logger logger = Logger.getLogger(ClsTramiteSELController.class);
	
	@Autowired
	private ClsTramiteSELService objConnTramitePagoService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/validapago", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<ClsValidaTramitePagoResult> doSaveTramitePago(@RequestBody ClsFilterPagoBean objTramitePago) {
		logger.info("doSaveTramitePago()");
		return ResponseEntity.ok().body(objConnTramitePagoService.doSaveTramitePago(objTramitePago)) ;
	}

}
