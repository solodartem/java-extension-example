package net.ifao.asciidoc.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Context for test.
 */
@Configuration
@ComponentScan({"net.ifao.test", "net.ifao.annotation.searcher.spring"})
public class Config {
}
