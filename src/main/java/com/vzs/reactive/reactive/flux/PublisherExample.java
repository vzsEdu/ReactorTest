package com.vzs.reactive.reactive.flux;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
public class PublisherExample {
    public void fluxSubscriber() {
        Flux<Integer> flux1 = Flux.range(4, 10);

        List<String> interable = Lists.newArrayList("Hello", "World", "!");
        Flux<String> flux2 = Flux.fromIterable(interable);

        flux1.subscribe(i -> log.info(i + ""));
        flux2.subscribe(i -> log.info(i + ""));


        Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(500)).zipWith(flux2, (i, item) -> "item" + i + ": " + item);

        intervalFlux.subscribe(log::info);
    }
}
