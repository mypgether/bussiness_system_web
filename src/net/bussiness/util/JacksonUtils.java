package net.bussiness.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JacksonUtils {
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

	public static String getBDPushString(int msgType, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgType", msgType);
		map.put("content", obj);
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

	private static ObjectMapper mapper;

	/**
	 * 
	 * @param createNew
	 *            是否创建一个新的Mapper
	 * @return
	 */
	public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
		if (createNew) {
			return new ObjectMapper();
		} else if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

	public static String bean2Json(Object obj) {
		ObjectMapper mapper = getMapperInstance(false);
		StringWriter writer = new StringWriter();
		JsonGenerator gen = null;
		try {
			gen = new JsonFactory().createJsonGenerator(writer);
			mapper.writeValue(gen, obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				gen.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String json = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static Object json2Bean(String json, Class<?> clazz) {
		ObjectMapper mapper = getMapperInstance(false);
		Object vo = null;
		try {
			vo = mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
