package br.com.jhage.aut.constante;

import br.com.jhage.aut.dto.PhoneDto;
import br.com.jhage.aut.modelo.Phone;

public enum Transformer implements  org.apache.commons.collections.Transformer{
	
	PHONE_TO_PHONEDTO,
	PHONEDTO_TO_PHONE;

	@Override
	public Object transform(Object input) {

		switch (this) {
			case PHONE_TO_PHONEDTO:
				return new PhoneDto((Phone)input);
				
			case PHONEDTO_TO_PHONE:
				return ((PhoneDto)input).extractPhone();
			

			default:
				return null;
		}

	}

}
