package com.paulg.graphql.graphql.directive;

import com.netflix.graphql.dgs.DgsDirective;
import com.paulg.graphql.service.AuthService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import static com.paulg.graphql.graphql.constant.Constant.ADMIN;
import static com.paulg.graphql.graphql.constant.Constant.AUTH;

@DgsDirective(name = AUTH)
@RequiredArgsConstructor
public class AuthDirective implements SchemaDirectiveWiring {

    private final AuthService authService;

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {
        GraphQLFieldsContainer fieldsContainer = env.getFieldsContainer();
        GraphQLFieldDefinition fieldDefinition = env.getFieldDefinition();

        DataFetcher<?> originalDataFetcher = env.getCodeRegistry().getDataFetcher(fieldsContainer, fieldDefinition);
        DataFetcher<?> dataFetcher = DataFetcherFactories.wrapDataFetcher(
                originalDataFetcher,
                (dataFetchingEnvironment, value) -> convertFieldValue(value)
        );

        env.getCodeRegistry().dataFetcher(fieldsContainer, fieldDefinition, dataFetcher);

        return fieldDefinition;
    }

    @Nullable
    private Integer convertFieldValue(Object currentFieldValue) {
        return checkAdminRole() ? (Integer) currentFieldValue : null;
    }

    private boolean checkAdminRole() {
        return authService.getAuthRole().equals(ADMIN);
    }
}
