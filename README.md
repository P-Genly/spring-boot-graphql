# Spring for GraphQL project

## Description
This project is a sample to learn how to use GraphQL in Spring boot project

## What is used dependency of graphQL ?
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

## Learn to use directive in GraphQL
### What is directives ?
The directives is annotation to handle `GraphQL` data responses. You can define a custom directive to apply a specific behavior.

### Step 1 - Create and apply you're directive :
You juste need to declare you're directives in `graphqls` file :
```graphqls
directive @<customName>(<argument>: <type>!) on FIELD_DEFINITION
```
Example : 

```graphqls
directive @caseFormat(style: CaseFormat!) on FIELD_DEFINITION
```
After the previous step you can apply you're directive on fields like this :
```graphqls
type Author {
    id: ID
    lastName: String @caseFormat(style: UPPER_CASE)
    firstName: String @caseFormat(style: UPPER_CASE)
}
```

### Step 2 - Create behavior of directive :
```java
@DgsDirective(name = "customDirective")
public class CustomDirective implements SchemaDirectiveWiring {

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
            builder.directive(<NAME_OF_DIRECTIVE>, new CaseFormatDirective());
        };
    }
```
