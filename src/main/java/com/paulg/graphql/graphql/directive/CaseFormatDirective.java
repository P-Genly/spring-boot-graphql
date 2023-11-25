package com.paulg.graphql.graphql.directive;

import com.netflix.graphql.dgs.DgsDirective;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.paulg.graphql.graphql.constant.Constant.CASE_FORMAT;
import static com.paulg.graphql.graphql.constant.Constant.STYLE;
import static com.paulg.graphql.graphql.constant.Constant.UPPER_CASE;

@DgsDirective(name = CASE_FORMAT)
public class CaseFormatDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {
        GraphQLFieldsContainer fieldsContainer = env.getFieldsContainer();
        GraphQLFieldDefinition fieldDefinition = env.getFieldDefinition();

        DataFetcher<?> originalDataFetcher = env.getCodeRegistry().getDataFetcher(fieldsContainer, fieldDefinition);
        DataFetcher<?> dataFetcher = DataFetcherFactories.wrapDataFetcher(
                originalDataFetcher,
                (dataFetchingEnvironment, value) -> {
                    String convertedValue = convertFieldValue(value, dataFetchingEnvironment);

                    if (convertedValue != null) return convertedValue;

                    return value;
                }
        );

        env.getCodeRegistry().dataFetcher(fieldsContainer, fieldDefinition, dataFetcher);

        return fieldDefinition;
    }

    @Nullable
    private static String convertFieldValue(Object currentFieldValue, DataFetchingEnvironment dataFetchingEnvironment) {
        String argument = getArgument(dataFetchingEnvironment);

        if (currentFieldValue instanceof String) {
            return argument.contains(UPPER_CASE)
                    ? ((String) currentFieldValue).toUpperCase() : ((String) currentFieldValue).toLowerCase();
        }

        return null;
    }

    private static String getArgument(DataFetchingEnvironment dataFetchingEnvironment) {
        return Objects.requireNonNull(dataFetchingEnvironment.getFieldDefinition().getDefinition())
                .getDirectives(CASE_FORMAT)
                .get(0).getArgument(STYLE)
                .getValue().toString();
    }
}
