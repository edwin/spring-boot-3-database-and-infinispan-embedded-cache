package com.edw.config;

import com.edw.bean.User;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <pre>
 *  com.edw.config.InfinispanConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:09
 */
@Configuration
public class InfinispanConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanCacheConfigurer infinispanCacheConfigurer() {
        return manager -> {
            org.infinispan.configuration.cache.Configuration userCacheConfig = new ConfigurationBuilder()
                    .indexing()
                        .enable()
                        .addIndexedEntities("com.edw.bean.User")
                    .expiration()
                        .lifespan(60_000)
                        .maxIdle(30_000)
                        .wakeUpInterval(10_000)
                    .build();

            manager.defineConfiguration("user-cache", userCacheConfig);
        };
    }

    @Bean
    public InfinispanGlobalConfigurer globalConfigurer() {
        return () -> {
            final GlobalConfigurationBuilder globalConfiguration = new GlobalConfigurationBuilder();

            globalConfiguration.serialization()
                    .marshaller(new JavaSerializationMarshaller()).allowList().addClasses(User.class);

            return globalConfiguration.build();
        };
    }
}
