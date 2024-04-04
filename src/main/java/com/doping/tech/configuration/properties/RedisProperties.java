package com.doping.tech.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.cache.redis")
@Component
@Getter
@Setter
public class RedisProperties {

   private Long timeToLive;

}
