package com.github.xiaowo.dto.id;

import java.util.Objects;

/**
 * ID基础类
 * @author 小蜗
 * @version 1.0.0
 */
public class ID<V> {
	private final V value;

	public ID(V value) {
		Objects.requireNonNull(value, "ID value must not be null");
		this.value = value;
	}

	public V value() {
		return value;
	}

	public String toString() {
		return value.toString();
	}
}