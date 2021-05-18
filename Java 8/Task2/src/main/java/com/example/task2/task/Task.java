package com.example.task2.task;

import com.example.task2.data.pojo.Comment;
import com.example.task2.data.pojo.Post;
import com.example.task2.data.response.DatePoint;
import com.example.task2.data.response.TimePoint;
import com.example.task2.utils.TimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.task2.utils.TimeUtils.*;
import static java.util.stream.Collectors.*;

@Component
public class Task {
    public void handleTask() {
        List<Post> posts = readPost();
        List<Comment> comments = readComment();
//        posts.forEach(System.out::println);
//        comments.forEach(System.out::println);
//        getPostHasHighestComment(comments);
//        getHighestUser(posts);
//        getHighestSecondLevelComment(comments);
//        getDatePoint(posts, comments);
        getTimePoint(posts, comments);
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

    private void getPostHasHighestComment(List<Comment> comments) {
//        List<String> collectPostId = comments.stream()
//                .map(comment -> comment.getPostId())
//                .distinct()
//                .collect(Collectors.toList());
////        collectPostId.stream()
////                .forEach(System.out::println);
//        List<Long> collectComment = collectPostId.stream()
//                .map(s -> {
//                    return comments.stream()
//                            .map(comment -> comment.getContent())
//                            .count();
//                })
//                .collect(Collectors.toList());
        Map<String, List<String>> postCommentsMap = comments.stream()
                .collect(groupingBy(Comment::getPostId, mapping(Comment::getContent, toList())));
        Map<String, Long> collectPostIdPoint = postCommentsMap.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));
        Map<String, Long> sortedPointOfPostId = collectPostIdPoint.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
//        collectPostId.forEach((s, stringList) -> System.out.println("Post ID: " + s + " Comments: " + stringList));
        sortedPointOfPostId.forEach((s, aLong) -> System.out.println("Post ID: " + s + " Point: " + aLong));
//        System.out.println();
    }

    private void getHighestUser(List<Post> posts) {
        Map<String, List<String>> collectUserId = posts.stream()
                .collect(groupingBy(Post::getOwnerId, mapping(Post::getId, toList())));
        Map<String, Long> collectPointOfUser = collectUserId.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));
        Map<String, Long> sortedPointUserId = collectPointOfUser.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) //Another Way
                .limit(5)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        sortedPointUserId.forEach((s, aLong) -> System.out.println("User ID: " + s + " Point: " + aLong));
//        collectPointOfUser.forEach((s, aLong) -> System.out.println("User ID: " + s + " Point: " + aLong));
//        collectUserId.forEach((s, stringList) -> System.out.println("User ID: " + s + " stringList " + stringList));
    }

    private void getHighestSecondLevelComment(List<Comment> comments) {
        Map<String, Long> sortedComments = comments.stream()
                .filter(comment -> comment.getParentId() != null)
                .collect(groupingBy(Comment::getParentId, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        sortedComments.forEach((s, count) -> System.out.println("Parent ID: " + s + " Count: " + count));
    }

    private Long generateDatePostKey(Post post) {
        return getStartTimeOfDayFromTime(post.getCreationTime());
    }

    private Long generateDateCommentKey(Comment comment) {
        return getStartTimeOfDayFromTime(comment.getCreationTime());
    }

    private void getDatePoint(List<Post> posts, List<Comment> comments) {
        Map<Long, Long> datePointMapPost = posts.stream()
                .collect(groupingBy(this::generateDatePostKey, counting()));

        Map<Long, Long> datePointMapComment = comments.stream()
                .collect(groupingBy(this::generateDateCommentKey, counting()));

//        datePointMapPost.forEach((aLong, aLong2) -> System.out.println("Date: " + aLong + " Point: " + aLong2));
//        datePointMapComment.forEach((aLong, aLong2) -> System.out.println("Date: " + aLong + " Point: " + aLong2));
//
        Map<Long, Long> mergedDatePointMap = Stream.of(datePointMapPost, datePointMapComment)
                .flatMap(map -> map.entrySet().stream())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));

        //Another method
//        Stream
//                .concat(
//                        posts.stream().map(Post::getCreationTime),
//                        comments.stream().map(Comment::getCreationTime))
//                .collect(groupingBy(TimeUtils::getStartTimeOfDayFromTime, counting()))
//                .entrySet()
//        .stream()
//        .map(e -> {
//            return
//                    new DatePoint()
//                            .setDate(e.getKey())
//                            .setPoint(e.getValue());
//        })
//                .collect(toList()););

        List<DatePoint> listDatePoint = mergedDatePointMap.entrySet()
                .stream()
                .map(e -> new DatePoint()
                        .setDate(e.getKey())
                        .setPoint(e.getValue()))
                .collect(toList());
        listDatePoint.forEach(System.out::println);
//
//        Map<String, Long> countPosts = posts.stream()
//                .collect(groupingBy(Post::getOwnerId, counting()));
//
//        Map<String, Long> countComments = comments.stream()
//                .collect(groupingBy(Comment::getOwnerId, counting()));
//
////        countPosts.forEach((s, aLong) -> System.out.println("User ID: " + s + " Post Point: " + aLong));
////        countComments.forEach((s, aLong) -> System.out.println("User ID: " + s + " Comment Point: " + aLong));
//        Map<String, Long> mergedPoint = Stream.of(countPosts, countComments)
//                .flatMap(map -> map.entrySet().stream())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
//
//        Map<String, List<Long>> datePosts = posts.stream()
//                .collect(groupingBy(Post::getOwnerId, mapping(Post::getCreationTime, toList())));
//
//        Map<String, List<Long>> dateComments = comments.stream()
//                .collect(groupingBy(Comment::getOwnerId, mapping(Comment::getCreationTime, toList())));
//
//        Map<String, List<Long>> mergedDate = Stream.of(datePosts, dateComments)
//                .flatMap(map -> map.entrySet().stream())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> Stream.of(v1, v2)
//                        .flatMap(Collection::stream).collect(toList())));
//
//        List<DatePoint> collectDatePoint = mergedDate.values()
//                .stream()
//                .map(longs -> {
//                    DatePoint datePoint = new DatePoint();
//                    mergedPoint.values()
//                            .stream()
//                            .map(aLong -> datePoint
//                                    .setPoint(aLong)
//                                    .setDate(longs));
//                    return datePoint;
//                })
//                .collect(toList());

//        datePosts.forEach((s, longs) -> System.out.println("User Id: " + s + "Date: " + longs));
//        dateComments.forEach((s, longs) -> System.out.println("User Id: " + s + "Date: " + longs));
//        mergedPoint.forEach((s, aLong) -> System.out.println("User ID: " + s + " Point: " + aLong));

//        collectDatePoint.forEach(System.out::println
    }

    private Long generateHourPostKey(Post post) {
        return getHourFromTime(post.getCreationTime());
    }

    private Long generateHourCommentKey(Comment comment) {
        return getHourFromTime(comment.getCreationTime());
    }

    private void getTimePoint(List<Post> posts, List<Comment> comments) {
//        Stream
//                .concat(
//                        posts.stream().map(Post::getCreationTime),
//                        comments.stream().map(Comment::getCreationTime))
//                .collect(groupingBy(TimeUtils::getDayHourFromTime, counting()))
//                .entrySet()
//                .stream()
//                .map(e -> {
//                    return new TimePoint()
//
//
//                })
//                .collect(toList());
//        Map<Long, Long> datePointMapPost = posts.stream()
//                .collect(groupingBy(this::generateDatePostKey, counting()));
//
//        Map<Long, Long> datePointMapComment = comments.stream()
//                .collect(groupingBy(this::generateDateCommentKey, counting()));

//        Map<Long, Long> mergedDatePointMap = Stream.of(datePointMapPost, datePointMapComment)
//                .flatMap(map -> map.entrySet().stream())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
//
//        Map<Long, Long> hourPointMapPost = posts.stream()
//                .collect(groupingBy(this::generateHourPostKey, counting()));
//
//        Map<Long, Long> hourPointMapComment = comments.stream()
//                .collect(groupingBy(this::generateHourCommentKey, counting()));
//
//        Map<Long, Long> mergedHourPointMap = Stream.of(hourPointMapComment, hourPointMapPost)
//                .flatMap(map -> map.entrySet().stream())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));

        Map<Long, Map<Long, Long>> dateHourPointPostMap = posts.stream()
                .collect(groupingBy(this::generateDatePostKey, groupingBy(this::generateHourPostKey, counting())));

        Map<Long, Map<Long, Long>> dateHourPointCommentMap = comments.stream()
                .collect(groupingBy(this::generateDateCommentKey, groupingBy(this::generateHourCommentKey, counting())));

        List<TimePoint> dateHourPointList = Stream.of(dateHourPointPostMap, dateHourPointCommentMap)
                .flatMap(map -> map.entrySet().stream())
                .flatMap(entry -> {
                    Long date = entry.getKey();
                    return entry.getValue()
                            .entrySet()
                            .stream()
                            .map(hourPointMap -> {
                                return new TimePoint()
                                        .setDay(date)
                                        .setPoint(hourPointMap.getValue())
                                        .setHour(hourPointMap.getKey());
                            });
                })
                .collect(toList());

        dateHourPointList.forEach(System.out::println);

//        Stream.of(dateHourPointPostMap, dateHourPointCommentMap)
//                .flatMap(map -> map.entrySet().stream())
//                .map(entry -> {
//                    Long date = entry.getKey();
//                    List<TimePoint> hourPoint = entry.getValue()
//                            .entrySet()
//                            .stream()
//                            .map(hourPointMap -> {
//                                return new TimePoint()
//                                        .setDay(date)
//                                        .setPoint(hourPointMap.getValue())
//                                        .setHour(hourPointMap.getKey());
//                            })
//                            .collect(toList());
//                    Map<Long, List<TimePoint>> dateHourPointList = new HashMap<>();
//                    dateHourPointList.put(date, hourPoint);
//                    return dateHourPointList;
//                });

    }
}
