package net.bussiness.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonStrUtils {
	public static String getJsonResult(int totol, List<?> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totol);
		map.put("rows", list);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = "";
		try {
			jsonResult = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
}
