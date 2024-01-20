package com.sams.grapgql.dgs.config;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.sams.grapgql.dgs.model.Product;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sams.grapgql.dgs.model.Product.PRODUCT_TYPE;

@Configuration
@Slf4j
public class GraphQLConfiguration {

  @Bean
  public GraphQlSourceBuilderCustomizer federationTransform() {
    DataFetcher entityDataFetcher = env -> {
      List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
      log.info("representations {} " , representations);
      return representations.stream()
        .map(representation -> {
          if (PRODUCT_TYPE.equals(representation.get("__typename"))) {
            return new Product((String)representation.get("id"));
          }
          return null;
        })
        .collect(Collectors.toList());
    };
    TypeResolver entityTypeResolver = env -> {
      final Object src = env.getObject();
      log.info("src {} " , src);
      if (src instanceof Product) {
        return env.getSchema()
          .getObjectType(PRODUCT_TYPE);
      }
      return null;
    };

    return builder -> {
      builder.schemaFactory((registry, wiring)->
        Federation.transform(registry, wiring)
          .fetchEntities(entityDataFetcher)
          .resolveEntityType(entityTypeResolver)
          .build()
      );
    };
  }
}
