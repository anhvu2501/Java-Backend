package com.example.task2.runner;

import com.example.task2.repository.tags.ITagsRepository;
import com.example.task2.tables.pojos.Tags;
import com.example.task2.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@Service
public class TaskRunner {
    private final Task task;
    private final ITagsRepository tagsRepository;

    public TaskRunner(Task task, ITagsRepository tagsRepository) {
        this.task = task;
        this.tagsRepository = tagsRepository;
    }

    //    @PostConstruct
    public void runner() throws IOException {
        task.handleTask();
    }

    @PostConstruct
    public void test() {
        Optional<Tags> aa = tagsRepository.findById("aa");
        System.out.println("aa");
    }
}
