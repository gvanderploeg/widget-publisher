package org.surfnet;

import java.net.URI;

import org.apache.http.localserver.LocalTestServer;
import org.junit.Before;
import org.junit.Test;
import org.surfnet.widgets.edukapp.EdukappFeedService;

public class EdukappFeedServiceTest {

  private String serverUrl;
  private EdukappFeedService svc;

  private LocalTestServer server;

  @Before
  public void setUp() throws Exception {
    server = new LocalTestServer(null, null);
    server.start();
    // report how to access the server
    serverUrl = String.format("http://%s:%d",
        server.getServiceAddress().getHostName(),server .getServiceAddress().getPort());

    System.out.println("HTTP server running at " + serverUrl);

    svc = new EdukappFeedService(URI.create(serverUrl + "/widgets"));
  }

  @Test(expected = RuntimeException.class)
  public void serverReturnsInvalidJSON() {

    server.register("/widgets",
        new InstrumentedRequestHandler()
        .bodyFromFile("invalid-widget.json")
        .status(200, "OK")
        .contentType("application/json")
    );

    svc.getWidgets(10);

  }

}
