package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsClaseBean;
import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.bean.ClsNizaBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional("transactionManagerSEL")
public class ClsNizaRepository implements Serializable, ClsNizaRepositoryI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2979164248472593922L;

	private  Logger logger = Logger.getLogger(ClsNizaRepository.class);
	
	
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
	public ClsResultDAO doLstClaseNiza(ClsFiltroClaseBean objFiltroClase) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_NIZA)
									.withCatalogName(ClsConstantes.PKG_BUSQUEDAS_NIZA)
									.withProcedureName(ClsConstantes.SP_RELACION_TITULOS_NIZA_NAME)
									.declareParameters(
									new  SqlOutParameter("PVO_CURSOR", OracleTypes.CURSOR ,
									new RowMapper<ClsNizaBean>() {

										@Override
										public ClsNizaBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsNizaBean objRespuesta=new ClsNizaBean();
											objRespuesta.setNuIdClase(rs.getInt("VC_CLASE"));
											objRespuesta.setVcNroDeBase(rs.getString("VC_NRO_DE_BASE"));
											objRespuesta.setVcProductosServicios(rs.getString("VC_PRODUCTOS_SERVICIOS"));
											objRespuesta.setVcSugerencia(rs.getString("VC_SUGERENCIA"));
											
											return objRespuesta;
										}
									})
									);
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();

	        inParamMap.put("P_NAME", objFiltroClase.getVcParaBusq());

	        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(in);
			
			//response
			objResultDAO.put("POUT_CUR_CLASE", out.get("PVO_CURSOR"));								    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
	  
	  
}
