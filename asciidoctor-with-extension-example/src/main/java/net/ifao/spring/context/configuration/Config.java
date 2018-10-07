package net.ifao.spring.context.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.ifao.test", "net.ifao.annotation.searcher.spring"})
public class Config {
}
