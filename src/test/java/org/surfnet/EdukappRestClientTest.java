package org.surfnet;

import java.net.URI;

import org.junit.Test;
import org.surfnet.widgets.edukapp.client.EdukappRestClient;

public class EdukappRestClientTest {

  @Test
  public void test() {
    new EdukappRestClient().get(URI.create("http://edukapp/widgets.php"));
//    new EdukappRestClient().get(URI.create("http://widgets.open.ac.uk:8080/api/tag/954/widgets"));
  }
}
