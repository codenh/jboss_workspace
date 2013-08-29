package com.naiyi.jboss.ticketsystem.exception;

@SuppressWarnings("serial")
public class NotEnoughMoneyException extends RuntimeException {
	public NotEnoughMoneyException(String string) {
		super(string);
	}
	public NotEnoughMoneyException() {
		super();
	}
}
