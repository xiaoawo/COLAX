package com.myth.theseus.util.exception;

import com.myth.theseus.model.exception.BizException;

import java.util.Collection;
import java.util.Map;

public final class Assert {
    private Assert() {}

    /**
     * Assert a boolean expression, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * @param expression a boolean expression
     * @throws BizException if expression is {@code false}
     */
    public static void isTrue(boolean expression, ErrorMessage errorMessage, Object... args) {
        if (!expression) {
            throw Exceptions.bizException(errorMessage, args);
        }
    }

    /**
     * Assert a boolean expression, if expression is true, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * This is more intuitive than isTure.
     */
    public static void isFalse(boolean expression, ErrorMessage errorMessage, Object... args) {
        if (expression) {
            throw Exceptions.bizException(errorMessage, args);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw Exceptions.bizException(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw Exceptions.bizException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] Must be true");
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] Must be false");
    }

    public static void notNull(Object object, ErrorMessage errorMessage, Object... args) {
        if (object == null) {
            throw Exceptions.bizException(errorMessage, args);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw Exceptions.bizException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] Must not null");
    }

    public static void notEmpty(Collection<?> collection, ErrorMessage errorMessage, Object... args) {
        if (collection == null || collection.isEmpty()) {
            throw Exceptions.bizException(errorMessage, args);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw Exceptions.bizException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Map<?, ?> map, ErrorMessage errorMessage, Object... args) {
        if (map == null || map.isEmpty()) {
            throw Exceptions.bizException(errorMessage, args);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw Exceptions.bizException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] Map must not be empty: it must contain at least one entry");
    }

}
