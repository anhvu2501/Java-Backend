package com.noron.tutorial.runner;

import com.noron.tutorial.task.Java8Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TaskRunner {
    private final Java8Task java8Task;

    public TaskRunner(Java8Task java8Task) {
        this.java8Task = java8Task;
    }

    @PostConstruct
    public void runner() {
        java8Task.handle();
    }
}
