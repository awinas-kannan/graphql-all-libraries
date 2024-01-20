package com.sams.grapgql.dgs.controller;

import com.sams.grapgql.dgs.model.EntityFilter;
import com.sams.grapgql.dgs.model.Product;
import com.sams.grapgql.dgs.model.ProductFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Slf4j
public class ProductsController {

    private final Map<String, Product> PRODUCTS = Stream.of(
            new Product("1", "Saturn V", "The Original Super Heavy-Lift Rocket!"),
            new Product("11", "Saturn V", "The Original Super Heavy-Lift Rocket!"),
            new Product("2", "Lunar Module"),
            new Product("3", "Space Shuttle"),
            new Product("4", "Falcon 9", "Reusable Medium-Lift Rocket"),
            new Product("5", "Dragon", "Reusable Medium-Lift Rocket"),
            new Product("6", "Starship", "Super Heavy-Lift Reusable Launch Vehicle")
    ).collect(Collectors.toMap(Product::id, product -> product));

    @QueryMapping
    public Product product(@Argument String id) {
        log.info("product {} ", id);
        return PRODUCTS.get(id);
    }

    @QueryMapping
    public List<Product> productsFilter(@Argument ProductFilter filter) {
        log.info("product {} ", filter);
        List<Product> prList = PRODUCTS.values().stream()
                .filter(e -> e.name().contains(filter.getName()) || (e.description() != null && e.description().contains(filter.getDescription())))
                .collect(Collectors.toList());
        return prList;
    }

    @QueryMapping
    public List<Product> entityFilter(@Argument EntityFilter filter) {
        log.info("product {} ", filter);
        return PRODUCTS.values().stream().toList();
    }

    @QueryMapping
    public List<Product> products() {
        log.info("products {} ");
        return PRODUCTS.values().stream().toList();
    }
}
