package com.vzs.reactive.reactive.flux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;

@Service
public class ProjectService {

    public Flux<ProjectStatus> getStatus(String name) {
        String[] statusType = new String[] {"Started", "In Progress", "Completed"};
        return Flux.fromStream(Stream.generate(() -> new ProjectStatus(name, null))).doOnEach(s -> {
            s.get().setStatus(statusType[new Random().nextInt(3)]);
        });
    }

}
