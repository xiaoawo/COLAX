package com.github.xiaowo.util.exception;


import com.alibaba.cola.dto.exception.BizException;
import com.alibaba.cola.dto.exception.SysException;
import com.github.xiaowo.util.MessageFormatter;
import com.github.xiaowo.util.exception.ErrorMessage;

public final class Exceptions {
	private Exceptions() {}

	public static BizException bizException(String message, Object... args) {
		return new BizException(MessageFormatter.format(message, args));
	}

	public static BizException bizException(ErrorMessage errorMessage, Object... args) {
		return new BizException(errorMessage.getCode(), MessageFormatter.format(errorMessage.getMessage(), args));
	}

	public static SysException sysException(String message, Object... args) {
		Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(args);
		if (throwableCandidate == null) {
			return new SysException(MessageFormatter.format(message, args));
		}
		return new SysException(MessageFormatter.format(message, args), throwableCandidate);
	}

	public static SysException sysException(ErrorMessage errorMessage, Object... args) {
		Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(args);
		if (throwableCandidate == null) {
			return new SysException(errorMessage.getCode(), MessageFormatter.format(errorMessage.getMessage(), args));
		}
		return new SysException(errorMessage.getCode(), MessageFormatter.format(errorMessage.getMessage()), throwableCandidate);
	}
}