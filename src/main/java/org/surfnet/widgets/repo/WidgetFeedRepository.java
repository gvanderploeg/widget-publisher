package org.surfnet.widgets.repo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.surfnet.widgets.model.Widget;

public class WidgetFeedRepository {

  private Map<String, List<Widget>> feeds = new ConcurrentHashMap<String, List<Widget>>();

  public void setFeed(String src, List<Widget> feed) {
    feeds.put(src, feed);
  }
  public List<Widget> getBySource(String source) {
    return feeds.get(source);
  }

  public boolean containsFeeds() {
    return !feeds.isEmpty();
  }
}
