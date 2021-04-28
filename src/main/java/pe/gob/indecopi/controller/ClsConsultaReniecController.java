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

import pe.gob.indecopi.bean.ClsFilterReniecBean;
import pe.gob.indecopi.result.ClsRespuestaReniecResult;
import pe.gob.indecopi.service.ClsConsultaReniecService;


@RestController
@CrossOrigin(origins={"*"}, maxAge=3600L)
@RequestMapping({"/pide"})
public class ClsConsultaReniecController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1376650513321283858L;

	static Logger logger = Logger.getLogger(ClsConsultaReniecController.class);
	
	@Autowired
	private ClsConsultaReniecService objConnReniecService;

	@RequestMapping(method = RequestMethod.POST, path = "/consultapide", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> doConsultaReniec(@RequestBody ClsFilterReniecBean objFilterReniec)
			throws Exception {
		return ResponseEntity.ok().body(objConnReniecService.doConsultaReniec(objFilterReniec)) ;
	}
	
}
