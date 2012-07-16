package org.surfnet.widgets;

import java.net.URI;
import java.util.List;

import org.surfnet.widgets.model.Widget;

public abstract class FeedFetchingStrategy<T extends Widget> {

  public abstract List<T> get(URI uri);
}
