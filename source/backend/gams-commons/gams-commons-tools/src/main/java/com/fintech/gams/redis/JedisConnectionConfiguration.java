/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fintech.gams.redis;

import java.net.UnknownHostException;
import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis connection configuration using Jedis.
 *
 * @author Mark Paluch
 * @author Stephane Nicoll
 */
@Primary
@Configuration
@ConditionalOnClass({GenericObjectPool.class, JedisConnection.class, Jedis.class})
class JedisConnectionConfiguration extends RedisConnectionConfiguration
{
    private final RedisProperties                                           properties;
    
    private final ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers;
    
    JedisConnectionConfiguration(RedisProperties properties, ObjectProvider<RedisSentinelConfiguration> sentinelConfiguration,
            ObjectProvider<RedisClusterConfiguration> clusterConfiguration, ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers)
    {
        super(properties, sentinelConfiguration, clusterConfiguration);
        this.properties = properties;
        this.builderCustomizers = builderCustomizers;
    }
    
    @Bean
    @Primary
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public JedisConnectionFactory redisConnectionFactory() throws UnknownHostException
    {
        return createJedisConnectionFactory();
    }
    
    private JedisConnectionFactory createJedisConnectionFactory()
    {
        JedisClientConfiguration clientConfiguration = getJedisClientConfiguration();
        if (getSentinelConfig() != null)
        { return new JedisConnectionFactory(getSentinelConfig(), clientConfiguration); }
        if (this.properties.getConnType().equals("cluster") && getClusterConfiguration() != null)
        {// 加入配置标识
            return new JedisConnectionFactory(getClusterConfiguration(), clientConfiguration);
        }
        return new JedisConnectionFactory(getStandaloneConfig(), clientConfiguration);
    }
    
    private JedisClientConfiguration getJedisClientConfiguration()
    {
        JedisClientConfigurationBuilder builder = applyProperties(JedisClientConfiguration.builder());
        RedisProperties.Pool pool = this.properties.getJedis().getPool();
        if (pool != null)
        {
            applyPooling(pool, builder);
        }
        if (StringUtils.hasText(this.properties.getUrl()))
        {
            customizeConfigurationFromUrl(builder);
        }
        customize(builder);
        return builder.build();
    }
    
    private JedisClientConfigurationBuilder applyProperties(JedisClientConfigurationBuilder builder)
    {
        if (this.properties.isSsl())
        {
            builder.useSsl();
        }
        if (this.properties.getTimeout() != null)
        {
            Duration timeout = this.properties.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }
        return builder;
    }
    
    private void applyPooling(RedisProperties.Pool pool, JedisClientConfigurationBuilder builder)
    {
        builder.usePooling().poolConfig(jedisPoolConfig(pool));
    }
    
    private JedisPoolConfig jedisPoolConfig(RedisProperties.Pool pool)
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        if (pool.getMaxWait() != null)
        {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return config;
    }
    
    private void customizeConfigurationFromUrl(JedisClientConfigurationBuilder builder)
    {
        ConnectionInfo connectionInfo = parseUrl(this.properties.getUrl());
        if (connectionInfo.isUseSsl())
        {
            builder.useSsl();
        }
    }
    
    private void customize(JedisClientConfigurationBuilder builder)
    {
        this.builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
    }
}
