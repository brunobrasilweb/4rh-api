package br.com.brunobrasilweb.api.util;

import br.com.brunobrasilweb.api.core.enums.TypeContract;

public class SellerUtil {

	public static String generateRegistration(Long id, TypeContract typeContract) {
		return (id + 1) + "-" + typeContract.getValue();
	}

}
