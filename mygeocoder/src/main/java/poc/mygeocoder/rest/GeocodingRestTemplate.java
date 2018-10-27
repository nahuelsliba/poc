package poc.mygeocoder.rest;

public interface GeocodingRestTemplate {

	String getGeocodeData(String address, GeocodeDataFormat format, String apiKey);

}