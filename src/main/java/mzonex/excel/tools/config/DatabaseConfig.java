package mzonex.excel.tools.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"mzonex.excel.tools.entity"})
@EnableJpaRepositories(basePackages = {"mzonex.excel.tools.repository"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableSpringDataWebSupport
public class DatabaseConfig {

}