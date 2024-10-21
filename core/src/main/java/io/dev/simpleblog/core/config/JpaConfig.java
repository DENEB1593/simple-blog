package io.dev.simpleblog.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "io.dev.simpleblog.core")
@EnableJpaRepositories(basePackages = "io.dev.simpleblog.core")
public class JpaConfig {

}
