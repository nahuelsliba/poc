package poc.mygeocoder.route;

import static org.apache.camel.model.rest.RestParamType.query;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class MyGeocoderRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		rest("/mygeocoder").description("Geocoder REST service").consumes("application/json")
				.produces("application/json").get().description("Geocoder address lookup").outType(String.class).param()
				.name("address").type(query).description("The address to lookup").dataType("string").endParam()
				.responseMessage().code(200).message("Geocoder successful").endResponseMessage().route()
				.toD("mygeocoder:address:${header.address}").marshal(new XmlJsonDataFormat()).to("mock:json");

	}

}
