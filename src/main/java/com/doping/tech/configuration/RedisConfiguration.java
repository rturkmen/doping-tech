package com.doping.tech.configuration;

import com.doping.tech.configuration.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfiguration implements CachingConfigurer {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory standaloneFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Override
    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.builder(standaloneFactory())
                .cacheDefaults(cacheConfiguration())
                .build();
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(redisProperties.getTimeToLive()))
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.
                        fromSerializer(new JdkSerializationRedisSerializer()));
    }

    @Bean
    public KeyGenerator customKeyGenerator() {
        return (target, method, params) -> {
            var key = new StringBuilder()
                    .append(target.getClass().getName())
                    .append(method.getName());
            for (var param : params) {
                key.append(param.toString());
            }
            return key.toString();
        };
    }
}
