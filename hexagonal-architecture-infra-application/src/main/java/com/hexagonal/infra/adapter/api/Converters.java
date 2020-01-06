package com.hexagonal.infra.adapter.api;

import java.util.Optional;

import org.springframework.beans.BeanUtils;

import com.hexagonal.domain.Example;
import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

public class Converters {

	public static XmlExampleEntity convert(Optional<Example> opExample) {
		XmlExampleEntity response = new XmlExampleEntity();
		if (opExample.isPresent()) {
			BeanUtils.copyProperties(opExample.get(), response);
		}
		return response;
	}

	public static XmlExampleEntity convert(Example example) {
		XmlExampleEntity response = new XmlExampleEntity();

		BeanUtils.copyProperties(example, response);

		return response;
	}

}
