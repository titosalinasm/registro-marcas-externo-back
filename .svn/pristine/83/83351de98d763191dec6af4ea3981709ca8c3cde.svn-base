package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional
public class ClsConfiguracionRepository implements Serializable, ClsConfiguracionRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789470206482678434L;
	private  Logger logger = Logger.getLogger(ClsConfiguracionRepository.class);
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DataSouser incio multiple
	  @Autowired
	  private DataSource dataSource;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	@Override
	public ClsResultDAO doConfiguracion(ClsFiltroConfiguracionBean objFiltro) {
		logger.info("doConfiguracion() => "+objFiltro.getVcCodConfiguracion());
		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_GENERAL)
									.withProcedureName(ClsConstantes.SP_GET_CONFIG_FORM)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_CONFIGURACION", OracleTypes.CURSOR ,
									new RowMapper<ClsConfiguracionBean>() {

										@Override
										public ClsConfiguracionBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsConfiguracionBean objRespuesta=new ClsConfiguracionBean();
											objRespuesta.setVcCodConfiguracion(rs.getString("VC_COD_CONFIGURACION"));
											objRespuesta.setNuFlagTipo(rs.getInt("CH_FLAG_TIPO"));
											objRespuesta.setVcDesConfiguracion(rs.getString("VC_DESC_CONFIGURACION"));
											objRespuesta.setNuValor1(rs.getInt("NU_VALOR_01"));
											objRespuesta.setNuValor2(rs.getInt("NU_VALOR_02"));
											objRespuesta.setNuValor3(rs.getInt("NU_VALOR_03"));
											objRespuesta.setVcValor1(rs.getString("VC_VALOR_01"));
											objRespuesta.setVcValor2(rs.getString("VC_VALOR_02"));
											objRespuesta.setVcValor3(rs.getString("VC_VALOR_03"));
											try {
											ObjectMapper objMapper=new ObjectMapper();
											Map<?,?> map = objMapper.readValue(rs.getClob("CL_VALOR_01")!=null?ClsUtil.clobToString((Clob)rs.getClob("CL_VALOR_01")):"", Map.class);

											objRespuesta.setClValor1(map);
											
											}catch(Exception e) {
												
											}
											
											return objRespuesta;
										}
									}),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			//param
			SqlParameterSource inParamMap = new MapSqlParameterSource()
											    .addValue("PIN_VC_COD_CONFIGURACION", objFiltro.getVcCodConfiguracion());
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			//response
			objResultDAO.put("POUT_CUR_CONFIGURACION", out.get("POUT_CUR_CONFIGURACION"));
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
											    		
			
		}catch(Exception e) {
			System.out.println(e);
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
}
