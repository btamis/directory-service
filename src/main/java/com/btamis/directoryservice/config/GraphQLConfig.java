package com.btamis.directoryservice.config;

import com.apollographql.federation.graphqljava.Federation;
import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Collections;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.DateTime);
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransformCustomizer() {
        return builder -> builder.schemaFactory((TypeDefinitionRegistry registry, RuntimeWiring wiring) ->
                Federation.transform(registry, wiring)
                        .fetchEntities(env -> Collections.emptyList())
                        .resolveEntityType(env -> null)
                        .build());
    }
}