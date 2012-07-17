package org.surfnet.widgets.synd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import org.surfnet.widgets.model.Widget;

public class SyndFeedBuilder {
  private List<Widget> widgets = Collections.emptyList();
  private String source;
  private int limit;

  public SyndFeedBuilder(List<Widget> widgets) {
    if (widgets != null) {
      this.widgets = widgets;
    }
  }

  public SyndFeedBuilder setSource(String source) {
    this.source = source;
    return this;
  }

  public SyndFeedBuilder limit(int i) {
    this.limit = i;
    return this;
  }

  public SyndFeed build() {
    final SyndFeedImpl syndFeed = new SyndFeedImpl();
    syndFeed.setAuthor(source);
    syndFeed.setFeedType("atom_1.0");
    List<SyndEntry> entries = new ArrayList<SyndEntry>();

    int i=0;
    while (i < limit && i < widgets.size()) {
      Widget w = widgets.get(i++);
      SyndEntry entry = new SyndEntryImpl();
      entry.setLink(w.getUri().toString());
      entry.setUri(w.getUri().toString());
      entry.setTitle(w.getName());
      final SyndContentImpl syndContent = new SyndContentImpl();
      syndContent.setValue(w.getDescription());
      entry.setDescription(syndContent);
      entry.setUpdatedDate(w.getUpdated());
      entry.setPublishedDate(w.getCreated());
      entries.add(entry);
    }
    syndFeed.setEntries(entries);
    return syndFeed;
  }
}
