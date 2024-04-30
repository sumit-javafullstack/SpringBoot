package com.springboot.crud.features.SpringBootCRUD.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@EnableHazelcastHttpSession
@Configuration
@Slf4j
public class HazelcastConfig {

  private static final String INSTANCE_NAME = "EMPLOYEE-INSTANCE";

  // hazelcast session cache data configuration

  @Value("${comm.hazelcast.cache.name.comm_prod_data:employeeDataMap}")
  private String cacheName;

  @Value("${comm.hazelcast.cache.comm.ttl.sec:300}")
  private int totalTimeToLive;

  @Value("${comm.hazelcast.cache.comm.max.idle:300}")
  private int maxIdleTime;

  @Value("${comm.hazelcast.cache.comm.max.size:100}")
  private int maxSize;

  //kubernetes hazelcast
  @Value("${comm.hazelcast.k8s:false}")
  private boolean hazelcastk8s;

  @Bean
  public HazelcastInstance hazelcastInstance() {
    HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName(INSTANCE_NAME);

    if (hazelcastInstance == null) {
      Config config = createConfig();
//      //kubernetes hazelcast
//      if(hazelcastk8s){
//        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
//        joinConfig.getMulticastConfig().setEnabled(false);
//        joinConfig.getKubernetesConfig().setEnabled(true);
//      }

        config.setProperty("hazelcast.backpressure.enabled", "true");
        config.setProperty("hazelcast.phone.home.enabled", "false");

      hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    }
    return hazelcastInstance;
  }

  private Config createConfig() {
    Config config = new Config().setInstanceName(INSTANCE_NAME);

    config.addMapConfig(
        new MapConfig()
            .setName(cacheName)
            .setEvictionConfig(
                new EvictionConfig()
                    .setMaxSizePolicy(MaxSizePolicy.PER_NODE)
                    .setSize(maxSize)
                    .setEvictionPolicy(EvictionPolicy.LRU))
            .setTimeToLiveSeconds(totalTimeToLive)
            .setMaxIdleSeconds(maxIdleTime));
    return config;
  }
}
