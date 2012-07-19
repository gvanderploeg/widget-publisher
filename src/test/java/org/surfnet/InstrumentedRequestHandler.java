package org.surfnet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

/**
 * Request handler for HttpClient's LocalTestServer.
 * Can be instructed for test purposes.
 * Example usage:
 * <pre>
 *  server.register("/widgets",
 *   new InstrumentedRequestHandler()
 *     .bodyFromFile("valid-widgets.json")
 *     .status(200, "OK")
 *     .contentType("application/json")
 *   );
 * </pre>
 */
public class InstrumentedRequestHandler implements HttpRequestHandler {

  private String body;
  private int statusCode;
  private String contentType;

  private String statusReason;
  private InputStream bodyInputStream;
  private RequestAssertion assertion;

  /**
   * Use the given string as response body
   * @param body the string to use as response body.
   * @return self, for chaining
   */
  public InstrumentedRequestHandler body(String body) {
    this.body = body;
    return this;
  }

  /**
   * Set the HTTP status line
   * @param statusCode the code (e.g. 200 for OK)
   * @param reason explanation (e.g. "Not found" for 404)
   * @return self, for chaining
   */
  public InstrumentedRequestHandler status(int statusCode, String reason) {
    this.statusCode = statusCode;
    this.statusReason = reason;
    return this;
  }



  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {


    if (assertion != null) {
      assertion.doAssert(request);
    }

    response.setStatusLine(new ProtocolVersion("HTTP", 1, 1), statusCode, statusReason);
    final BasicHttpEntity entity = new BasicHttpEntity();

    if (bodyInputStream != null) {
      entity.setContent(bodyInputStream);
    } else if (body != null) {
      entity.setContent(new ByteArrayInputStream(body.getBytes()));
    }
    if (contentType != null) {
      entity.setContentType(contentType);
    }
    response.setEntity(entity);
  }

  /**
   * Set the content type header of the body
   * @param contentType the content type header value (e.g. "text/plain")
   * @return
   */
  public InstrumentedRequestHandler contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * Use the given file as source for the response body. Must be on the classpath.
   * @return self, for chaining
   */
  public InstrumentedRequestHandler bodyFromFile(String filename) {
    bodyInputStream = getClass().getClassLoader().getResourceAsStream(filename);
    return this;
  }

  /**
   * Inject an assertion to perform on the request. Example usage:
   * <pre>
   * new InstrumentedRequestHandler()
   *   .assertion(new InstrumentedRequestHandler.RequestAssertion() {
   *     public void doAssert(HttpRequest request) throws RuntimeException {
   *       assertTrue("Request query should contain limitation",
   *          request.getRequestLine().getUri().contains("resultsize=37"));
   *
   *     }
   *  })
   * </pre>
   * @param assertion the assertion
   * @return
   */
  public InstrumentedRequestHandler assertion(RequestAssertion assertion) {
    this.assertion = assertion;
    return this;
  }

  public interface RequestAssertion {
    public void doAssert(HttpRequest request);
  }
}
