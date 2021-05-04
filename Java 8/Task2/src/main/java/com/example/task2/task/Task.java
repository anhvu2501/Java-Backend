package com.example.task2.task;

import com.example.task2.data.Comment;
import com.example.task2.data.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Task {
    public void handleTask() {
        List<Post> posts = readPost();
        List<Comment> comments = readComment();
        posts.forEach(System.out::println);
        comments.forEach(System.out::println);
    }

    private List<Post> readPost() {
        String filename = "post.txt";
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(this::convertToPost)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Comment> readComment() {
        String filename = "comment.txt";
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(this::convertToComment)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Post convertToPost(String string) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(string, Post.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Comment convertToComment(String string) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(string, Comment.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
