package org.surfnet;

import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;
import org.surfnet.widgets.model.Widget;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

public class WidgetTest {

  @Test
  public void compare() {
    Widget w1 = new Widget();
    Widget w2 = new Widget();

    assertEquals("null dates should compare as equal", 0, w1.compareTo(w2));

    w1.setUpdated(new Date(1000L));
    w2.setUpdated(new Date(1000L));
    assertEquals("same dates should compare as equal", 0, w1.compareTo(w2));

    w1.setUpdated(new Date(1000L));
    w2.setUpdated(new Date(2000L));
    assertEquals("w2 should be newer therefore last", -1, w1.compareTo(w2));

    w1.setUpdated(new Date(2000L));
    w2.setUpdated(new Date(1000L));
    assertEquals("w1 should be newer therefore last", 1, w1.compareTo(w2));

    w2.setUpdated(null);
    assertEquals("w1 should be newer therefore last", 1, w1.compareTo(w2));
  }

  @Test
  public void serializeDeserialize() throws Exception {
    final Widget w = new Widget();
    final GregorianCalendar cal = new GregorianCalendar(2012, 0, 1);
    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    w.setCreated(cal.getTime());
    w.setUpdated(cal.getTime());
    w.setDescription("my description");
    w.setIcon("http://example.com/icon");
    w.setId(1L);
    w.setName("the name");
    w.setUri(URI.create("http://www.example.com/uri"));

    assertEquals("a Widget can be serialized to JSON",
        jsonFixture("fixtures/widget.json"), asJson(w));

    assertEquals("a Widget can be deserialized from JSON",
        w, fromJson(jsonFixture("fixtures/widget.json"), Widget.class));
  }
}