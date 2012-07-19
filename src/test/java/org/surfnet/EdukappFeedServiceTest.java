package org.surfnet;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.localserver.LocalTestServer;
import org.junit.Before;
import org.junit.Test;
import org.surfnet.widgets.edukapp.EdukappFeedService;
import org.surfnet.widgets.edukapp.EdukappWidget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

  @Test
  public void serverReturnsValidJSON() {

    server.register("/widgets",
        new InstrumentedRequestHandler()
        .bodyFromFile("valid-widgets.json")
        .status(200, "OK")
        .contentType("application/json")
        .assertion(new InstrumentedRequestHandler.RequestAssertion() {
          @Override
          public void doAssert(HttpRequest request) throws RuntimeException {
            assertTrue("Request query should contain limitation",
                request.getRequestLine().getUri().contains("resultsize=37"));
          }
        })
    );

    List<EdukappWidget> widgets = svc.getWidgets(37);
    assertEquals(2, widgets.size());
  }

  @Test(expected = RuntimeException.class)
  public void serverReturnsNon200() {
    server.register("/widgets",
        new InstrumentedRequestHandler()
        .body("An internal error occurred (not really)")
        .status(500, "Internal server error")
        .contentType("text/html")
    );
    svc.getWidgets(10);
  }
}
