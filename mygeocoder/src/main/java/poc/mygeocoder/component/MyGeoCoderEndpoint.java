package poc.mygeocoder.component;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;

import poc.mygeocoder.producer.MyGeoCoderProducer;

@UriEndpoint(firstVersion = "1.0.0", scheme = "mygeocoder", title = "Custom Geocoder", syntax = "mygeocoder:address", producerOnly = true, label = "api,location")
public class MyGeoCoderEndpoint extends DefaultEndpoint {

	public MyGeoCoderEndpoint(String uri, MyGeoCoderComponent component) {
		super(uri, component);
	}

	public Producer createProducer() throws Exception {
		return new MyGeoCoderProducer(this);
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		throw new UnsupportedOperationException("Cannot consume from this component");
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
