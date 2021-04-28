package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.indecopi.bean.ClsFilterRucBean;
import pe.gob.indecopi.util.ClsResultDAO;
import oracle.jdbc.OracleTypes;
@Repository
@Transactional
public class ClsSunatRepository implements Serializable, ClsSunatRepositoryI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2113047163213624804L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public ClsResultDAO doConsultaRuc(ClsFilterRucBean objRuc) {
		
		try {
		//procedure
		this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
								.withSchemaName("USR_PIDE")
								.withCatalogName("PKG_CONSULTA_PIDE")
								.withProcedureName("SP_GET_CONRUC_M")
								.declareParameters(
								new  SqlOutParameter("POUT_NU_COD_RESULTADO", OracleTypes.NUMBER ,"NUMBER"),
								new  SqlOutParameter("POUT_VC_RAZON_SOCIAL", OracleTypes.VARCHAR ,"VARCHAR2"),
								new  SqlOutParameter("POUT_VC_CIIU", OracleTypes.VARCHAR ,"VARCHAR2"),
								new  SqlOutParameter("POUT_VC_UBIGEO", OracleTypes.VARCHAR ,"VARCHAR2"),
								new  SqlOutParameter("POUT_VC_DIREC", OracleTypes.VARCHAR ,"VARCHAR2"),
								new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
								new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
								);
		
		
		//param
		SqlParameterSource inParamMap = new MapSqlParameterSource()
										    .addValue("PIN_VC_RUC", objRuc.getVcRuc());
		//execute
		Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
		
		//response
		
		objResultDAO.put("PNU_COD_RESULTADO", out.get("POUT_NU_COD_RESULTADO"));
		objResultDAO.put("PVC_RAZON_SOCIAL", out.get("POUT_VC_RAZON_SOCIAL"));
		objResultDAO.put("PVC_CIIU", out.get("POUT_VC_CIIU"));
		objResultDAO.put("POUT_VC_UBIGEO", out.get("POUT_VC_UBIGEO"));
		objResultDAO.put("POUT_VC_DIREC", out.get("POUT_VC_DIREC"));
		objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
		objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objResultDAO;
		
	}



}
