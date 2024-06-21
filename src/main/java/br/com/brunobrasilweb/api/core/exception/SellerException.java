package br.com.brunobrasilweb.api.core.exception;

public class SellerException extends RuntimeException {

	public SellerException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public SellerException(String errorMessage) {
		super(errorMessage);
	}

}
