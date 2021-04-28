package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.bean.ClsPagoBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional("transactionManagerSEL")
public class ClsExpedienteRepository implements Serializable, ClsExpedienteRepositoryI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2979164248472593922L;

	private  Logger logger = Logger.getLogger(ClsExpedienteRepository.class);
	
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DataSouser incio multiple
	  @Autowired
	  @Qualifier("dataSourceSEL")
	  private DataSource dataSourceSEL;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSourceSEL);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	  
	  @Override
	  public ClsResultDAO doExpediente()
	  {
		  logger.info("doUsuario()");
	    try
	    {
	   
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_MARCAS)
	    		  					.withProcedureName(ClsConstantes.SP_DO_EXPEDIENTE)
	    		  					.declareParameters(	
	    		  							
	    		  					new  SqlOutParameter("POUT_NU_NRO_EXPEDIENTE", OracleTypes.NUMBER 	,"NUMBER"	),
	    		  					new  SqlOutParameter("POUT_NU_ANIO_EXPEDIENTE", OracleTypes.NUMBER 	,"NUMBER"	),
	    		  					new  SqlOutParameter("POUT_VC_AREA_EXPEDIENTE", OracleTypes.VARCHAR ,"VARCHAR2"	),
	    		  					
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);

		      	Map<String, Object> out = simpleJdbcCall.execute();
	      
		      	objResultDAO.put("POUT_NU_NRO_EXPEDIENTE", 		out.get("POUT_NU_NRO_EXPEDIENTE"));
		      	objResultDAO.put("POUT_NU_ANIO_EXPEDIENTE", 	out.get("POUT_NU_ANIO_EXPEDIENTE"));
		      	objResultDAO.put("POUT_VC_AREA_EXPEDIENTE", 	out.get("POUT_VC_AREA_EXPEDIENTE"));
	      	
				objResultDAO.put("POUT_NU_ERROR"	 , 				out.get("POUT_NU_ERROR"	 ));
				objResultDAO.put("POUT_VC_ERROR"	 , 				out.get("POUT_VC_ERROR"	 ));
			
	    }
	    catch (Exception e)
	    {
	      logger.info(e);
	    }
	    return objResultDAO;
	  }
	  
	 

}
