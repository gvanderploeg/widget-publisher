package org.surfnet;

import java.net.URI;
import java.util.Collections;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.surfnet.widgets.WidgetResource;
import org.surfnet.widgets.model.Widget;
import org.surfnet.widgets.service.FeedService;
import org.surfnet.widgets.synd.SyndFeedMessageBodyWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WidgetResourceTest extends ResourceTest {

  private static final Logger LOG = LoggerFactory.getLogger(WidgetResourceTest.class);

  private FeedService fs = mock(FeedService.class);

  @Override
  protected void setUpResources() {

    addResource(new WidgetResource(Collections.singletonMap("mocky", fs)));
    addProvider(SyndFeedMessageBodyWriter.class);
  }

  @Test
  public void getAllWidgets() throws Exception {
    Widget w = new Widget();
    w.setDescription("my description");
    w.setIcon("http://example.com/icon");
    w.setId(100L);
    w.setName("The name");
    w.setUri(URI.create("http://foo.example.com/uri"));
    w.setAuthor("The Author");
    w.setLicense("My own, proprietary license");
    when(fs.getWidgets(anyInt())).thenReturn(Collections.singletonList(w));

    String atom = client().resource("/widgets").get(String.class);
    LOG.debug("atom: {}", atom);

    assertTrue(atom.contains("my description"));
    assertTrue(atom.contains("The Author"));
    assertTrue(atom.contains("My own, proprietary license"));
  }

  @Test(expected = UniformInterfaceException.class)
  public void nonExisting() throws Exception {
    String atom = client().resource("/widgets/non-existing").get(String.class);
  }
  
  @Test
  public void specificResource() {
    String atom = client().resource("/widgets/mocky").get(String.class);
    assertTrue(atom.contains("<dc:creator>mocky</dc:creator>"));
  }
}