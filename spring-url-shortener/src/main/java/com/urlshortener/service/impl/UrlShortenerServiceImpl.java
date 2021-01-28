package com.urlshortener.service.impl;

import java.net.URL;
import java.time.Duration;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.dao.UrlDao;
import com.urlshortener.exceptions.MalformedURLException;
import com.urlshortener.model.Url;
import com.urlshortener.service.UrlShortenerService;
import com.urlshortener.util.Constants;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	@Autowired
	private UrlDao urlDao;

	@Override
	public Optional<Url> findById(String id) {
		return urlDao.findById(id);
	}

	@Override
	public Url save(Url url) {
		validate(url.getOriginal());

		Optional<Url> existingUrl = urlDao.findByOriginal(url.getOriginal());

		Url result = null;

		if(existingUrl.isPresent()){
			Url presentUrl = existingUrl.get();
			long days = TimeUnit.DAYS.convert(Calendar.getInstance().getTime().getTime() - presentUrl.getDate().getTime(), TimeUnit.MILLISECONDS);
			if(days <= 30){
				result = presentUrl;
			} else {
				urlDao.delete(presentUrl);
				result = urlDao.save(url);
			}
		} else {
			result = urlDao.save(url);
		}

		return result;
	}

	private void validate(String stringURL) {
		try {
			new URL(stringURL);
		} catch (java.net.MalformedURLException e) {
			throw new MalformedURLException(String.format(Constants.ERROR_MESSAGE, e.getMessage()));
		}
	}
}
