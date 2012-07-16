package org.surfnet.widgets.edukapp.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.surfnet.widgets.FeedFetchingStrategy;
import org.surfnet.widgets.edukapp.model.EdukappWidget;

public class EdukappRestClient extends FeedFetchingStrategy<EdukappWidget> {

  private static final Logger LOG = LoggerFactory.getLogger(EdukappRestClient.class);

  @Override
  public List<EdukappWidget> get(URI uri) {

    ClientConfig cc = new DefaultClientConfig();
    cc.getClasses().add(JacksonJsonProvider.class);
    LOG.debug("{}", cc.getClasses());
    final Client client = Client
        .create(cc);
    LOG.debug("{}", client.getProviders());
    ClientResponse response = client
        .resource(uri)
        .accept(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
    }

    GenericType<List<EdukappWidget>> genericType = new GenericType<List<EdukappWidget>>() {};
    List<EdukappWidget> output = response.getEntity(genericType);

    LOG.info("Response from URI {}:\n{}", uri, output);
//    return output;
    return null;
  }
}
