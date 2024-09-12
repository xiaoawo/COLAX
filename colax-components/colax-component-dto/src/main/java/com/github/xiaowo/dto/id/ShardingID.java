package com.github.xiaowo.dto.id;

import java.util.Objects;

/*** 分布式ID的基础类
 * @author 小蜗
 * @version 1.0.0
 */
public class ShardingID<S, V> extends ID<V> {
	private final S sharding;

	public ShardingID(S sharding, V value) {
		super(value);
		Objects.requireNonNull(sharding, "sharding must not be null");
		this.sharding = sharding;
	}

	public S sharding() {
		return sharding;
	}

	public String toString() {
		return String.format("{\"sharding\": %s, \"value\": %s}", sharding, value());
	}
}