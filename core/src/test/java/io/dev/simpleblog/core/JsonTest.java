package io.dev.simpleblog.core;

import static org.assertj.core.api.BDDAssertions.then;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dev.simpleblog.core.JsonTest.JsonSerializeObject.SimpleEnum;
import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

class JsonTest {

  public static final ObjectMapper OM = objectMapper()
    .copy()
    .configure(SerializationFeature.INDENT_OUTPUT, false);

  private static ObjectMapper objectMapper() {
    ObjectMapper om = JsonMapper.builder()
      .disable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
      .disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN)
      .build()
      .registerModules(
        new Jdk8Module(),
        new JavaTimeModule()
      )
      .setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")))
      .setLocale(Locale.KOREA);

    return om.setTypeFactory(
      om.getTypeFactory()
        .withCache((LookupCache<Object, JavaType>) new LRUMap<Object, JavaType>(5120, 5120))
    );
  }


  @Test
  void serializeEnum() throws Exception {
    JsonSerializeObject object = JsonSerializeObject.builder()
      .simpleEnum(SimpleEnum.BACKWARD)
      .build();

    String actual = OM.writeValueAsString(object);

    then(actual).isEqualTo("{\"simpleEnum\":\"BACKWARD\"}");
  }

  @Test
  void deserializeEnum() throws Exception {
    String json = """
      {"simpleEnum":"BACKWARD"}
      """;

    JsonSerializeObject actual = OM.readValue(json, JsonSerializeObject.class);

    then(actual.getSimpleEnum()).isEqualTo(SimpleEnum.BACKWARD);
  }

  @Test
  void serializeBigDecimal() throws Exception {
    JsonSerializeObject object = JsonSerializeObject.builder()
      .bigDecimal(BigDecimal.valueOf(1000))
      .build();

    String actual = OM.writeValueAsString(object);

    then(actual).isEqualTo("{\"bigDecimal\":1000}");
  }

  @Test
  void deserializeBigDecimal() throws Exception {
    String json = """
      {"bigDecimal":"1000"}
      """;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @JsonInclude(Include.NON_NULL)
  public static class JsonSerializeObject {
    private SimpleEnum simpleEnum;
    private BigDecimal bigDecimal;
    private Instant instant;
    private ZonedDateTime zonedDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime zonedDateTimeFormat;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate localDateFormat;
    private LocalTime localTime;
    @JsonFormat(pattern = "HHmm")
    private LocalTime localTimeFormat;
    @JsonFormat(pattern = "HHmmss")
    private LocalTime localTimeFullFormat;
    private Duration duration;
    private OffsetDateTime offsetDateTime;
    private Year year;
    private MonthDay monthDay;

    public enum SimpleEnum {
      FORWARD,
      BACKWARD
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class JsonCollection {
      @Builder.Default
      private final Map<String, String> map = new HashMap<>();

      @Nullable
      private final Entry<String, String> entry;

      @Builder.Default
      private final List<String> list = new ArrayList<>();

      @Builder.Default
      private final Set<String> set = new HashSet<>();
    }
  }

}


