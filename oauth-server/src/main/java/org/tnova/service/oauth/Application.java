package org.tnova.service.oauth;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application
{

    @Bean @Primary @ConfigurationProperties( prefix = "spring.datasource" ) public DataSource mainDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }
}
