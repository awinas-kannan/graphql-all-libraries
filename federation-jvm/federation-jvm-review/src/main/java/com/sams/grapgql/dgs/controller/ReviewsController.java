package com.sams.grapgql.dgs.controller;

import com.sams.grapgql.dgs.model.Product;
import com.sams.grapgql.dgs.model.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ReviewsController {

    private final Map<String, List<Review>> REVIEWS = Map.of(
            "2", List.of(new Review("1020", "Very cramped :( Do not recommend.", 2), new Review("1021", "Got me to the Moon!", 4)),
            "3", List.of(new Review("1030", 3)),
            "4", List.of(new Review("1040", 5), new Review("1041", "Reusable!", 5), new Review("1042", 5)),
            "5", List.of(new Review("1050", "Amazing! Would Fly Again!", 5), new Review("1051", 5))
    );

    @SchemaMapping(typeName = "Product", field = "reviews")
    public List<Review> reviews(Product product) {
        log.info("reviews {} ", product);
        return REVIEWS.getOrDefault(product.id(), Collections.emptyList());
    }

    @QueryMapping
    public List<Review> getreviews() {
        log.info("getreviews");
        return REVIEWS.values().stream().toList().stream().flatMap(Collection::stream).toList();
    }
}
