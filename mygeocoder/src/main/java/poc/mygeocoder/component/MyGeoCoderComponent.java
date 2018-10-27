package poc.mygeocoder.component;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.springframework.stereotype.Component;

/**
 * Represents the component that manages {@link MyGeoCoderEndpoint}.
 */
@Component
@SuppressWarnings("deprecation")
// TODO Use DefaultComponent instead of UriEndpointComponent, it is deprecated.
public class MyGeoCoderComponent extends UriEndpointComponent {

	public MyGeoCoderComponent() {
		super(MyGeoCoderEndpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		MyGeoCoderEndpoint endpoint = new MyGeoCoderEndpoint(uri, this);

		if (!remaining.startsWith("address:")) {
			throw new IllegalArgumentException("Missing reuired param address");
		}

		setProperties(endpoint, parameters);
		return endpoint;
	}

}
