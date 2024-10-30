package com.myth.theseus.model.entity;


import com.myth.theseus.model.validation.Validatable;

public class Entity<T> implements Validatable {

	private T id;

	@Override
	public void validate() throws IllegalArgumentException {

	}
}