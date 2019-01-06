package com.vzs.reactive.reactive.flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @GetMapping(value = "/{name}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProjectStatus> getProjectStatus(@PathVariable String name) {
        return projectService.getStatus(name).delayElements(Duration.ofSeconds(1)).timeout(Duration.ofSeconds(120));
    }


}
