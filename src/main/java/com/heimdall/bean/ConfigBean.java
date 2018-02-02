package com.heimdall.bean;


import com.heimdall.defines.Constants;
import com.heimdall.dao.Configs;
import com.heimdall.repository.ConfigRepository;
import com.heimdall.utils.ListUtils;
import com.heimdall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigBean {

    private final ConfigRepository repository;

    @Autowired
    public ConfigBean(ConfigRepository repository) {
        this.repository = repository;
    }

    @Bean
    public Configs initConfigs() {
        List<Configs> configs = repository.findAll();
        if (ListUtils.isNullOrEmpty(configs)) {
            Configs firstConfig = new Configs();
            firstConfig.setSecretKey(StringUtils.getRandomPassword());
            firstConfig.setTokenExpireDuration(Constants.DURATION_TOKEN_EXPIRED_IN_SECONDS);
            repository.insert(firstConfig);
            return firstConfig;
        }
        return configs.get(0);
    }
}
