package com.urlshortener.model;

import static com.urlshortener.util.Constants.CUSTOM_GENERATOR;
import static com.urlshortener.util.Constants.CUSTOM_ID_GENERATOR;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Calendar;

@Entity
public class Url {

	@Id
	@GeneratedValue(generator = CUSTOM_GENERATOR)
	@GenericGenerator(name = CUSTOM_GENERATOR, strategy = CUSTOM_ID_GENERATOR)
	private String id;

	private String original;

	private Date date;

	public Url() {
	}

	public Url(String original) {
		this.original = original;
		this.date = Calendar.getInstance().getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Date getDate() { return date; }

	public void setDate(Date date) { this.date = date; }
}
