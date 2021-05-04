package com.example.task2.runner;

import com.example.task2.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TaskRunner {
    private final Task task;

    public TaskRunner (Task task) {
        this.task = task;
    }

    @PostConstruct
    public void runner() throws IOException {
        task.handleTask();
    }
}
