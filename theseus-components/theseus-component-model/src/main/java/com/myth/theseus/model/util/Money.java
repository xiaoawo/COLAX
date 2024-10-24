package com.myth.theseus.model.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Money {
	private Long value;
	private Currency currency;

	public Long value() {
		return value;
	}

	public Currency currency() {
		return currency;
	}

	public Money(Long value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Money{" +
				"value=" + value +
				", currency=" + currency +
				'}';
	}

	public enum Currency {
		CNY("人民币", Locale.CHINA),
		USD("美元", Locale.US);
		private static final Map<Locale, Currency> MAP = new HashMap<>();

		static {
			for (Currency currency : Currency.values()) {
				MAP.put(currency.locale, currency);
			}
		}

		private final Locale locale;
		private final String des;

		Currency(String des, Locale locale) {
			this.des = des;
			this.locale = locale;
		}

		public static Currency getByLocale(Locale locale) {
			Currency currency = MAP.get(locale);
			if (currency == null) {
				throw new IllegalArgumentException(locale + " 无相关币种");
			}
			return currency;
		}

		public String getDes() {
			return des;
		}
	}
}