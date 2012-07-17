package org.surfnet;

import java.net.URI;

import org.junit.Test;
import org.surfnet.widgets.edukapp.EdukappFeedService;

public class EdukappFeedServiceTest {

  @Test
  public void test() {
    final EdukappFeedService svc = new EdukappFeedService(URI.create("http://edukapp/widgets.php"));
    svc.getWidgets();
  }

}
