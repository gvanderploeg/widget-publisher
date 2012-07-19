package org.surfnet.widgets.service;

import java.util.List;

import org.surfnet.widgets.model.Widget;

public interface FeedService<T extends Widget> {
  List<T> getWidgets(int limit);
}
