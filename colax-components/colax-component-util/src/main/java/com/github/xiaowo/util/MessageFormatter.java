package com.github.xiaowo.util;


import java.util.HashMap;
import java.util.Map;

public class MessageFormatter {
	private MessageFormatter() {}

	public static String format(String messagePattern, Object... argArray) {
		Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(argArray);
		Object[] args = argArray;
		if (throwableCandidate != null) {
			args = MessageFormatter.trimmedCopy(argArray);
		}
		return arrayFormat(messagePattern, args);
	}

	private static String arrayFormat(String messagePattern, Object... argArray) {
		if (messagePattern == null) {
			return null;
		} else if (argArray == null) {
			return messagePattern;
		} else {
			int i = 0;
			StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);

			for(int l = 0; l < argArray.length; ++l) {
				int j = messagePattern.indexOf("{}", i);
				if (j == -1) {
					if (i == 0) {
						return messagePattern;
					}

					sbuf.append(messagePattern, i, messagePattern.length());
					return sbuf.toString();
				}

				if (isEscapedDelimeter(messagePattern, j)) {
					if (!isDoubleEscaped(messagePattern, j)) {
						--l;
						sbuf.append(messagePattern, i, j - 1);
						sbuf.append('{');
						i = j + 1;
					} else {
						sbuf.append(messagePattern, i, j - 1);
						deeplyAppendParameter(sbuf, argArray[l], new HashMap<>());
						i = j + 2;
					}
				} else {
					sbuf.append(messagePattern, i, j);
					deeplyAppendParameter(sbuf, argArray[l], new HashMap<>());
					i = j + 2;
				}
			}

			sbuf.append(messagePattern, i, messagePattern.length());
			return sbuf.toString();
		}
	}

	public static Throwable getThrowableCandidate(final Object[] argArray) {
		if (argArray == null || argArray.length == 0) {
			return null;
		}

		final Object lastEntry = argArray[argArray.length - 1];
		if (lastEntry instanceof Throwable) {
			return (Throwable) lastEntry;
		}

		return null;
	}

	static Object[] trimmedCopy(final Object[] argArray) {
		if (argArray == null || argArray.length == 0) {
			throw new IllegalStateException("non-sensical empty or null argument array");
		}

		final int trimmedLen = argArray.length - 1;

		Object[] trimmed = new Object[trimmedLen];

		if (trimmedLen > 0) {
			System.arraycopy(argArray, 0, trimmed, 0, trimmedLen);
		}

		return trimmed;
	}

	static boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {
		if (delimeterStartIndex == 0) {
			return false;
		} else {
			char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
			return potentialEscape == '\\';
		}
	}

	static boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
		return delimeterStartIndex >= 2 && messagePattern.charAt(delimeterStartIndex - 2) == '\\';
	}

	private static void deeplyAppendParameter(StringBuilder sbuf, Object o, Map<Object[], Object> seenMap) {
		if (o == null) {
			sbuf.append("null");
		} else {
			if (!o.getClass().isArray()) {
				safeObjectAppend(sbuf, o);
			} else if (o instanceof boolean[]) {
				booleanArrayAppend(sbuf, (boolean[])o);
			} else if (o instanceof byte[]) {
				byteArrayAppend(sbuf, (byte[])o);
			} else if (o instanceof char[]) {
				charArrayAppend(sbuf, (char[])o);
			} else if (o instanceof short[]) {
				shortArrayAppend(sbuf, (short[])o);
			} else if (o instanceof int[]) {
				intArrayAppend(sbuf, (int[])o);
			} else if (o instanceof long[]) {
				longArrayAppend(sbuf, (long[])o);
			} else if (o instanceof float[]) {
				floatArrayAppend(sbuf, (float[])o);
			} else if (o instanceof double[]) {
				doubleArrayAppend(sbuf, (double[])o);
			} else {
				objectArrayAppend(sbuf, (Object[])o, seenMap);
			}

		}
	}

	private static void safeObjectAppend(StringBuilder sbuf, Object o) {
		try {
			String oAsString = o.toString();
			sbuf.append(oAsString);
		} catch (Throwable t) {
			sbuf.append("[FAILED toString()]");
		}

	}

	private static void objectArrayAppend(StringBuilder sbuf, Object[] a, Map<Object[], Object> seenMap) {
		sbuf.append('[');
		if (!seenMap.containsKey(a)) {
			seenMap.put(a, (Object)null);
			int len = a.length;

			for(int i = 0; i < len; ++i) {
				deeplyAppendParameter(sbuf, a[i], seenMap);
				if (i != len - 1) {
					sbuf.append(", ");
				}
			}

			seenMap.remove(a);
		} else {
			sbuf.append("...");
		}

		sbuf.append(']');
	}

	private static void booleanArrayAppend(StringBuilder sbuf, boolean[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void byteArrayAppend(StringBuilder sbuf, byte[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void charArrayAppend(StringBuilder sbuf, char[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void shortArrayAppend(StringBuilder sbuf, short[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void intArrayAppend(StringBuilder sbuf, int[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void longArrayAppend(StringBuilder sbuf, long[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void floatArrayAppend(StringBuilder sbuf, float[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}

	private static void doubleArrayAppend(StringBuilder sbuf, double[] a) {
		sbuf.append('[');
		int len = a.length;

		for(int i = 0; i < len; ++i) {
			sbuf.append(a[i]);
			if (i != len - 1) {
				sbuf.append(", ");
			}
		}

		sbuf.append(']');
	}
}