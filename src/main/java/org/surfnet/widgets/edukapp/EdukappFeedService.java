package org.surfnet.widgets.edukapp;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.surfnet.widgets.service.FeedService;

public class EdukappFeedService implements FeedService {

  private static final Logger LOG = LoggerFactory.getLogger(EdukappFeedService.class);

  public EdukappFeedService(URI baseUri) {
    this.baseUri = baseUri;
  }

  private URI baseUri;

  @XmlRootElement
  public static class SearchResultContainer {


    @JsonProperty
    private List<EdukappWidget> widgets;

    @JsonProperty
    private int number_of_results;
  }

  private URI addLimit(int limit) {
    return UriBuilder.fromUri(baseUri).queryParam("resultsize", limit).build();
  }

  @Override
  public List<EdukappWidget> getWidgets(int limit) {

    URI uri = addLimit(limit);

    ClientConfig cc = new DefaultClientConfig();
    cc.getClasses().add(JacksonJsonProvider.class);
    final Client client = Client
        .create(cc);
    ClientResponse response = client
        .resource(uri)
        .accept(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
    }

    List<EdukappWidget> output = response.getEntity(SearchResultContainer.class).widgets;

    LOG.debug("Response from URI {}:\n{}", uri, output);
    return output;
  }
}
