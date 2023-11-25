package com.paulg.graphql.config;

import com.paulg.graphql.graphql.directive.CaseFormatDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static com.paulg.graphql.graphql.constant.Constant.CASE_FORMAT;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder ->  {
            builder.directive(CASE_FORMAT, new CaseFormatDirective());
        };
    }
}
