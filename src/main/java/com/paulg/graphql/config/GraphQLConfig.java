package com.paulg.graphql.config;

import com.paulg.graphql.graphql.directive.AuthDirective;
import com.paulg.graphql.graphql.directive.CaseFormatDirective;
import com.paulg.graphql.service.ClientService;
import graphql.schema.visibility.NoIntrospectionGraphqlFieldVisibility;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static com.paulg.graphql.graphql.constant.Constant.AUTH;
import static com.paulg.graphql.graphql.constant.Constant.CASE_FORMAT;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfig {

    private final ClientService clientService;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder ->  {
            builder.directive(CASE_FORMAT, new CaseFormatDirective());
            builder.directive(AUTH, new AuthDirective(clientService));
            // Disable GraphQL introspection
            builder.fieldVisibility(NoIntrospectionGraphqlFieldVisibility.NO_INTROSPECTION_FIELD_VISIBILITY);
        };
    }
}
