package org.surfnet.widgets;

import java.util.HashMap;
import java.util.Map;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

import org.surfnet.widgets.config.ApplicationConfiguration;
import org.surfnet.widgets.config.FeedConfiguration;
import org.surfnet.widgets.edukapp.EdukappFeedService;
import org.surfnet.widgets.repo.FeedService;
import org.surfnet.widgets.synd.SyndFeedMessageBodyWriter;

public class Application extends Service<ApplicationConfiguration> {

  public static void main(String[] args) throws Exception {
    new Application().run(args);
  }

  public Application() {
    super("widget publisher");
  }

  private Map<String, FeedService> configureFeedServices(ApplicationConfiguration config) {
    Map<String, FeedService> services = new HashMap<String, FeedService>();
    for (FeedConfiguration feedConfig : config.getFeeds()) {
      if (feedConfig.getType().equals("edukapp-rest-json")) {
        EdukappFeedService svc = new EdukappFeedService(feedConfig.getUri());
        services.put(feedConfig.getName(), svc);
      } else {
        throw new IllegalArgumentException("Unsupported service type: " + feedConfig.getType());
      }
    }
    return services;
  }

  @Override
  protected void initialize(ApplicationConfiguration configuration, Environment environment) throws Exception {
    final Map<String, FeedService> services = configureFeedServices(configuration);
    environment.addResource(new WidgetResource(services));

    environment.addProvider(new SyndFeedMessageBodyWriter());
  }
}
