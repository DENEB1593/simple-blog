package io.dev.simpleblog.api.config;

import io.dev.simpleblog.core.config.JpaConfig;
import io.dev.simpleblog.core.config.ObjectMapperConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  JpaConfig.class,
  ObjectMapperConfig.class
})
public class ApiCoreConfig {

}
