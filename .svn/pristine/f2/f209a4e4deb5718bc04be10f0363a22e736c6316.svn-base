package pe.gob.indecopi.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.indecopi.bean.ClsFiltroTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroVerificarTermsBean;
import pe.gob.indecopi.result.ClsTerminosResult;
import pe.gob.indecopi.result.ClsVerifTermsResult;
import pe.gob.indecopi.service.ClsTerminosService;

//@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
@CrossOrigin(origins={"*"}, maxAge=3600L)
@RequestMapping({"/terminos"})
public class ClsTerminosController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4923254670335257096L;

	public Logger logger = Logger.getLogger(ClsTerminosController.class);
	
	@Autowired
	private ClsTerminosService objConnTermService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/consultaterminos", consumes = "application/json", produces = "application/json")
	public @ResponseBody ClsTerminosResult doConsultaTerminos(@RequestBody ClsFiltroTerminosBean objFiltroTerminos) {
		logger.info("doConsultaTerminos() => "+objFiltroTerminos.getVcUsuario());
		return objConnTermService.doConsultaTerminos(objFiltroTerminos);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/verificarterminos", consumes = "application/json", produces = "application/json")
	public @ResponseBody ClsVerifTermsResult doVerificarTerminos(@RequestBody ClsFiltroVerificarTermsBean objVerificarTerms) {
		return objConnTermService.doVerificarTerminos(objVerificarTerms);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/aceptartermino", consumes = "application/json", produces = "application/json")
	public @ResponseBody ClsVerifTermsResult doAceptarTerminos(@RequestBody ClsFiltroTerminosBean objFiltroTerminos) {
		logger.info("doVerificarTerminos() => ");
		return objConnTermService.doAceptaTerminos(objFiltroTerminos);
	}
	
}  
