package com.urlshortener;

import com.urlshortener.controller.UrlShortenerController;
import com.urlshortener.dao.UrlDao;
import com.urlshortener.model.Url;
import com.urlshortener.service.UrlShortenerService;
import com.urlshortener.service.impl.UrlShortenerServiceImpl;
import com.urlshortener.util.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlShortenerApplicationTests {

	@Autowired
	private UrlDao urlDao;

	@Test
	public void testFindByOriginal() {
		Url url = new Url(Constants.TEST_URL);

		urlDao.save(url);

		Optional<Url> existingUrl = urlDao.findByOriginal(Constants.TEST_URL);

		assertThat(existingUrl.get().getOriginal()).isEqualTo(Constants.TEST_URL);
	}

	@Test
	public void testPast30Days() throws Exception {
		Url url1 = new Url(Constants.TEST_URL);

		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString = "01 01 2020";
		url1.setDate(myFormat.parse(inputString));

		urlDao.save(url1);

		Url url = new Url(Constants.TEST_URL);

		Optional<Url> existingUrl1 = urlDao.findByOriginal(url.getOriginal());

		Url result = null;

		if(existingUrl1.isPresent()){
			Url presentUrl = existingUrl1.get();
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

		Optional<Url> existingUrl = urlDao.findByOriginal(Constants.TEST_URL);

		assertThat(existingUrl.get().getDate().getTime()).isGreaterThan(url1.getDate().getTime());
	}
}