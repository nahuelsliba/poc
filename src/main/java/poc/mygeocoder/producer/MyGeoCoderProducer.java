package poc.mygeocoder.producer;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poc.mygeocoder.component.MyGeoCoderEndpoint;
import poc.mygeocoder.rest.GeocodeDataFormat;
import poc.mygeocoder.rest.GeocodingRestTemplate;
import poc.mygeocoder.rest.GoogleGeocodingRestTemplateImp;

/**
 * MyGeoCoderProducer producer.
 */
public class MyGeoCoderProducer extends DefaultProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyGeoCoderProducer.class);

	private GeocodingRestTemplate geocodingRestTemplate;

	public MyGeoCoderProducer(MyGeoCoderEndpoint endpoint) {
		super(endpoint);
		this.geocodingRestTemplate = new GoogleGeocodingRestTemplateImp();
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String address = exchange.getIn().getHeader("address", String.class);
		if (address != null) {
			LOGGER.debug("Geocode for address {}", address);
			String result = this.geocodingRestTemplate.getGeocodeData(address, GeocodeDataFormat.xml,
					exchange.getIn().getHeader("apiKey", String.class));
			LOGGER.debug("Geocode response {}", result);
			exchange.getIn().setBody(result, String.class);
		}
	}

}
