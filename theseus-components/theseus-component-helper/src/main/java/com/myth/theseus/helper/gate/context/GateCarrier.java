package com.myth.theseus.helper.gate.context;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class GateCarrier {
	public static final RuntimeException NOT_INITED_EXCEPTION = new RuntimeException("GateContext is not initialized");
	private static final ThreadLocal<Map<String, Object>> CARRIER = new ThreadLocal<>();
	private static final String CONTEXT = "CONTEXT";
	private static final String MAIN_THREAD_ID = "MAIN_THREAD_ID";

	public static void init() {
		CARRIER.set(new HashMap<>());
		put(CONTEXT, new ConcurrentHashMap<>());
		put(MAIN_THREAD_ID, Thread.currentThread().getId());
	}

	public static void init(Map<String, Object> map) {
		Objects.requireNonNull(map, "map must not be null");
		CARRIER.set(map);
	}

	public static void clear() {
		CARRIER.remove();
	}

	public static Map<String, Object> context() {
		return get(CONTEXT);
	}

	public static long mainThreadId() {
		return get(MAIN_THREAD_ID);
	}

	private static void put(String key, Object value) {
		Map<String, Object> map = CARRIER.get();
		if (map == null) {
			throw NOT_INITED_EXCEPTION;
		}
		map.put(key, value);
	}

	private static <T> T get(String key) {
		Map<String, Object> map = CARRIER.get();
		if (map == null) {
			throw NOT_INITED_EXCEPTION;
		}
		return (T) map.get(key);
	}
}