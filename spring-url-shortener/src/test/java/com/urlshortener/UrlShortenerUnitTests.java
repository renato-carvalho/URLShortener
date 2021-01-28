package com.urlshortener;

import com.urlshortener.dao.UrlDao;
import com.urlshortener.model.Url;
import com.urlshortener.service.UrlShortenerService;
import com.urlshortener.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class UrlShortenerUnitTests {

	@Test
	public void testOriginal() {
		Url url = new Url();
		url.setOriginal(Constants.TEST_URL);

		assertThat(url.getOriginal()).isEqualTo(Constants.TEST_URL);
	}

	@Test
	public void testId() {
		Url url = new Url();
		url.setId(Constants.TEST_ID);

		assertThat(url.getId()).isEqualTo(Constants.TEST_ID);
	}

	@Test
	public void testUrlShortener() {
		Url url = new Url();
		url.setOriginal(Constants.TEST_URL);

		assertThat(url.getId()).isEqualTo(Constants.TEST_ID);
	}
}

