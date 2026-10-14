package br.com.firenze.loteamento.shared.regional;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RegionalSettingsProperties.class)
class RegionalSettingsConfiguration {}
