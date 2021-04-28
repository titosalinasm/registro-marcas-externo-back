package pe.gob.indecopi.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class ClsResultDAO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7258160148624655724L;
	Map<String, Object> resultado = new LinkedHashMap<String, Object>();
    public static final String CODIGO_ERROR = "CODIGO_ERROR";
    public static final String MENSAJE_ERROR = "MENSAJE_ERROR";
    public static final String ERROR_THROW = "-1";

    public void put(String id, Object objeto) {
        resultado.put(id, objeto);
    }

    public Object get(String id) {
        return resultado.get(id);
    }

}
