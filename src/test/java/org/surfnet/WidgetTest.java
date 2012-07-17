package org.surfnet;

import java.util.Date;

import org.junit.Test;
import org.surfnet.widgets.model.Widget;

import static org.junit.Assert.assertEquals;

public class WidgetTest {

  private class ConcreteWidget extends Widget {

  }

  @Test
  public void compare() {
    Widget w1 = new ConcreteWidget();
    Widget w2 = new ConcreteWidget();

    assertEquals("null dates should compare as equal", 0, w1.compareTo(w2));

    w1.setUpdated(new Date(1000L));
    w2.setUpdated(new Date(1000L));
    assertEquals("same dates should compare as equal", 0, w1.compareTo(w2));

    w1.setUpdated(new Date(1000L));
    w2.setUpdated(new Date(2000L));
    assertEquals("w2 should be newer therefore first", -1, w1.compareTo(w2));

    w1.setUpdated(new Date(2000L));
    w2.setUpdated(new Date(1000L));
    assertEquals("w1 should be newer therefore first", 1, w1.compareTo(w2));

    w2.setUpdated(null);
    assertEquals("w1 should be newer therefore first", 1, w1.compareTo(w2));
  }
}
