package org.surfnet.widgets.synd;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.util.StdDateFormat;

/**
 * A serializer that uses Jackson's own StdDateFormat, an RFC 8601 compliant serializer
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

  private static final DateFormat dateFormat = new StdDateFormat();

  @Override
  public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    String formattedDate = dateFormat.format(date);
    gen.writeString(formattedDate);
  }
}
