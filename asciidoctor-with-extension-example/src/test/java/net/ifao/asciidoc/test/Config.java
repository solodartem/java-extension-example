package net.ifao.asciidoc.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.ifao.test", "net.ifao.annotation.searcher.searcher.spring"})
public class Config {
}
