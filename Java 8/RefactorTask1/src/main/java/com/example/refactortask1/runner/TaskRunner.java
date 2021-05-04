package com.example.refactortask1.runner;

import com.example.refactortask1.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;

@Service
public class TaskRunner {
    private final Task task;

    public TaskRunner (Task task) {
        this.task = task;
    }

    @PostConstruct
    public void runner() throws IOException, ParseException {
        task.handleTask();
    }
}
