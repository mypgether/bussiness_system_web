package net.bussiness.util;

import java.util.Iterator;
import java.util.Map;

public class HqlUtils {
	@SuppressWarnings("rawtypes")
	public static String getHql(StringBuffer sb, Map<String, String> params) {
		for (Iterator iterator = params.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			if (!StringUtils.isBlank(val)) {
				sb.append(" and " + key + " =" + val.trim());
			}
		}
		return sb.toString();
	}
}
