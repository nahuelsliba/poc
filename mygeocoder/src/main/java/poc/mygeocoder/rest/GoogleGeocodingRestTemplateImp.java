package poc.mygeocoder.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GoogleGeocodingRestTemplateImp implements GeocodingRestTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGeocodingRestTemplateImp.class);

	private static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";
	private static final String PARAM_BEGIN = "?";
	private static final String ADDRESS_PARAM = "address=";
	private static final String PARAM_SEP = "&";
	private static final String KEY_PARAM = "key=";
	private static final String KEY_DEFAULT_VALUE = "changeMe";

	private RestTemplate restTemplate;

	public GoogleGeocodingRestTemplateImp() {
		this.restTemplate = new RestTemplate();
	}

	public GoogleGeocodingRestTemplateImp(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String getGeocodeData(String address, GeocodeDataFormat format, String apiKey) {
		StringBuilder requestUrl = new StringBuilder(BASE_URL).append(format.name()).append(PARAM_BEGIN);
		try {
			requestUrl.append(ADDRESS_PARAM).append(URLEncoder.encode(address, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncoding: " + address, e);
			return StringUtils.EMPTY;
		}
		requestUrl.append(PARAM_SEP);
		requestUrl.append(KEY_PARAM).append(StringUtils.isBlank(apiKey) ? KEY_DEFAULT_VALUE : apiKey);

		ResponseEntity<String> response = restTemplate.getForEntity(requestUrl.toString(), String.class);
		return response.getBody();
	}

}
