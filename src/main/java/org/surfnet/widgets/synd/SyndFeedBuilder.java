package org.surfnet.widgets.synd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import org.surfnet.widgets.model.Widget;

public class SyndFeedBuilder {
  private List<Widget> widgets = Collections.emptyList();
  private String source;

  public SyndFeedBuilder(List<Widget> widgets) {
    if (widgets != null) {
      this.widgets = widgets;
    }
  }

  public SyndFeedBuilder setSource(String source) {
    this.source = source;
    return this;
  }

  public SyndFeed build() {
    final SyndFeedImpl syndFeed = new SyndFeedImpl();
    syndFeed.setAuthor(source);
    syndFeed.setFeedType("atom_1.0");
    List<SyndEntry> entries = new ArrayList<SyndEntry>();
    for (Widget w : widgets) {
      SyndEntry entry = new SyndEntryImpl();
//      entry.setTitle(w.getTitle());
      entries.add(entry);
    }
    syndFeed.setEntries(entries);
    return syndFeed;
  }
}
