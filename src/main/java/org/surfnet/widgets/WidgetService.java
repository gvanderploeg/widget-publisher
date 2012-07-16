package org.surfnet.widgets;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

import org.surfnet.widgets.edukapp.client.EdukappRestClient;
import org.surfnet.widgets.health.RepositoryHealthCheck;
import org.surfnet.widgets.model.Widget;
import org.surfnet.widgets.repo.WidgetFeedRepository;
import org.surfnet.widgets.synd.SyndFeedMessageBodyWriter;

public class WidgetService extends Service<WidgetConfiguration> {

  public static void main(String[] args) throws Exception {
    new WidgetService().run(args);
  }

  public WidgetService() {
    super("widget publisher");
  }

  @Override
  protected void initialize(WidgetConfiguration configuration, Environment environment) throws Exception {
    WidgetFeedRepository repository = new WidgetFeedRepository();

    WidgetFeedUpdater updater = new WidgetFeedUpdater(new EdukappRestClient(),
        repository, configuration.getFeeds());
    updater.syncAll();

    environment.addResource(new WidgetResource(repository));
    environment.addProvider(new SyndFeedMessageBodyWriter());
    environment.addHealthCheck(new RepositoryHealthCheck(repository));
  }
}
