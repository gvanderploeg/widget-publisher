Widget Publisher
==========
An ATOM feed builder with Edukapp's REST api as backend.

Can be configured to use multiple backends.

Uses:

- Dropwizard (http://dropwizard.codahale.com/) as application framework.
- Jersey client for consuming REST api
- ROME for ATOM output

## Build / run
    mvn clean package
    java -cp target/widget-publisher-*.jar org.surfnet.widgets.Application server widget-publisher.yml
Configuration resides in widget-publisher.yml.

## Test
    curl -v http://localhost:8081/widgets
    curl -v http://localhost:8081/widgets/edukapp?limit=5
