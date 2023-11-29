# Spring for GraphQL project

## Description
This project is a sample to learn how to use GraphQL in Spring boot project

## What is used dependency of GraphQL ?
In this project we use  `spring-boot-starter-graphql`  dependency

```xml
                <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-graphql</artifactId>
		</dependency>
```

## How to access to GraphiQL UI
In the properties file you found the path to the graphiql interface. 
```properties
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql
```

## Learn to use directives in GraphQL
### What is directives ?
The directives is annotation to handle `GraphQL` data responses. You can define a custom directive to apply a specific behavior.

### Step 1 - Create and apply you're directive :
At first, you need to declare you're directives in `graphqls` file :
```graphqls
directive @<customNameDirective>(<argument>: <type>!) on FIELD_DEFINITION
```
Example : 

```graphqls
directive @caseFormat(style: CaseFormat!) on FIELD_DEFINITION
```
After the previous step, apply you're directive on you're fields like this :
```graphqls
type Author {
    id: ID
    lastName: String @caseFormat(style: UPPER_CASE)
    firstName: String @caseFormat(style: UPPER_CASE)
}
```

### Step 2 - Create behavior for the directive :
```java
@DgsDirective(name = "customNameDirective")
public class CustomNameDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {
        GraphQLFieldsContainer fieldsContainer = env.getFieldsContainer();
        GraphQLFieldDefinition fieldDefinition = env.getFieldDefinition();

        DataFetcher<?> originalDataFetcher = env.getCodeRegistry().getDataFetcher(fieldsContainer, fieldDefinition);
        DataFetcher<?> dataFetcher = DataFetcherFactories.wrapDataFetcher(
                originalDataFetcher,
                (dataFetchingEnvironment, value) -> {
                    // Implement you're behavior
                    ...
                }
        );

        env.getCodeRegistry().dataFetcher(fieldsContainer, fieldDefinition, dataFetcher);

        return fieldDefinition;
    }
```

### Step 3 - Link you're code with GraphQL schema :
This bean is mandatory to map you're directive behavior with you're graphQL schema when you run GraphQL requests :
```java
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder ->  {
            builder.directive("customNameDirective", new CaseFormatDirective());
        };
    }
```

### To know about GraphQL `introspection`
When you running you're project, by default GraphQL allow introspection for display graphql's schemas.
It's possible to disable it like this : 
```java
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder ->  {
            ...
            // Disable GraphQL introspection
            builder.fieldVisibility(NoIntrospectionGraphqlFieldVisibility.NO_INTROSPECTION_FIELD_VISIBILITY);
        };
    }
```
_*RuntimeWiringConfigurer `@Bean` is used to define the context in which the application running_
