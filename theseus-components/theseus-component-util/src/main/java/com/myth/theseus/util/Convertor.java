package com.myth.theseus.util;


import com.google.common.base.CaseFormat;
import com.myth.theseus.model.util.ArrayStreamList;
import com.myth.theseus.model.util.StreamList;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Convertor {
	public static <E> StreamList<E> stream(List<E> list) {
		return new ArrayStreamList<>(list);
	}

	public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) throws Exception {
		T obj = clazz.getDeclaredConstructor().newInstance();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}
			String filedName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
			Field field = clazz.getDeclaredField(filedName);
			field.setAccessible(true);

			if (value instanceof String) {
				field.set(obj, parseObject(field.getType(), (String) value));
			} else {
				field.set(obj, value);
			}
		}

		return obj;
	}

	public static Object parseObject(Class<?> clazz, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}

		if (clazz == Boolean.class || clazz == boolean.class) {
			return Boolean.parseBoolean(value);
		}
		if (clazz == Byte.class || clazz == byte.class) {
			return Byte.parseByte(value);
		}
		if (clazz == Short.class || clazz == short.class) {
			return Short.parseShort(value);
		}
		if (clazz == Integer.class || clazz == int.class) {
			return Integer.parseInt(value);
		}
		if (clazz == Long.class || clazz == long.class) {
			return Long.parseLong(value);
		}
		if (clazz == Float.class || clazz == float.class) {
			return Float.parseFloat(value);
		}
		if (clazz == Double.class || clazz == double.class) {
			return Double.parseDouble(value);
		}
		if (clazz == String.class) {
			return value;
		}
		if (clazz == BigDecimal.class) {
			return new BigDecimal(value);
		}

		return JSON.parse(value, clazz);
	}
}