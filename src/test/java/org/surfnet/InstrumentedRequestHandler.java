package org.surfnet;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

public class InstrumentedRequestHandler implements HttpRequestHandler {

  private String body;
  private int statusCode;
  private String statusReason;

  private String contentType;

  public InstrumentedRequestHandler() {
    this.statusCode = statusCode;
    this.body = body;
  }
  public InstrumentedRequestHandler body(String body) {
    this.body = body;
    return this;
  }

  public InstrumentedRequestHandler status(int statusCode, String reason) {
    this.statusCode = statusCode;
    this.statusReason = reason;
    return this;
  }



  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
    if (contentType != null) {
      response.setHeader("Content-Type", contentType);
    }
    response.setStatusLine(new ProtocolVersion("HTTP", 1, 1), statusCode, statusReason);
  }

  public InstrumentedRequestHandler contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public InstrumentedRequestHandler bodyFromFile(String s) {
    body = new Scanner(getClass().getClassLoader().getResourceAsStream(s)).useDelimiter("\\Z").next();
    return this;
  }
}
