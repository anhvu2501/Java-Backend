package com.noron.tutorial.task;

import org.springframework.stereotype.Component;

@Component
public class Java8Task {

    public void handle() {
        readUsers();
    }

    private void readUsers() {
        System.out.println("ahihi");
    }

}
