package poc.geocoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void geocoderAddressTest() {
		ResponseEntity<String> response = restTemplate.exchange("/camel/mygeocoder?address=81%20Farringdon%20Street",
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertNotNull(response.getBody());
	}
}