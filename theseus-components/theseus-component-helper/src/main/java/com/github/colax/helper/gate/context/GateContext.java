package com.github.colax.helper.gate.context;



import com.myth.theseus.model.constants.Symbols;

import java.util.function.Supplier;

public class GateContext {
	public static void put(String key, Object value) {
		GateCarrier.context().put(key, value);
	}

	public static <T> T get(String key) {
		return (T) GateCarrier.context().get(key);
	}

	public static boolean containsKey(String key) {
		return GateCarrier.context().containsKey(key);
	}

	public static <T> T computeIfAbsent(String key, Supplier<T> supplier) {
		if (containsKey(key)) {
			return get(key);
		}

		// supplier在这里可能是一个耗时操作，因此如果不自定义锁粒度的话，就会导致ConcurrentHashMap的整个数组都加锁
		// 这样会导致锁粒度过粗，影响性能
		// 所以我们采用 mainThreadId + key 的方式来保证锁粒度最细
		// 利用intern()方法来保证锁的key是同一个对象
		String lockKey = (GateCarrier.mainThreadId() + Symbols.HYPHEN + key).intern();
		synchronized (lockKey) {
			// double check
			if (containsKey(key)) {
				return get(key);
			}

			// 在外面将资源加载好，避免在ConcurrentHashMap.computeIfAbsent内部进行资源加载，导致加锁耗时过长，引发竞争
			T value = supplier.get();
			if (value == null) {
				return null;
			}
			put(key, value);
			return value;
		}
	}
}