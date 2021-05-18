package com.example.refactortask1.task;

import com.example.refactortask1.data.pojo.User;
import com.example.refactortask1.data.response.DatePoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.refactortask1.utils.TimeUtils.getEndTime;
import static com.example.refactortask1.utils.TimeUtils.getStartTimeOfDayBeforeXDay;
import static java.util.stream.Collectors.*;

@Component
public class Task {
    public void handleTask() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse("01-04-2021");
        List<User> users = readUsers();
//        users.forEach(System.out::println);
//        printUser(users);
//        print5HighestPoint(users);
//        printActivePointInSpecifiedTime
//        printTime(users, dateFormat);(users, dateFormat, date1);
//        printUserByRange(users);
//        getAverage(users);
        getAverageByTime(users, dateFormat);
    }

    private List<User> readUsers() {
        String filename = "user_point_active.txt";
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(this::convertToUser)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private User convertToUser(String string) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(string, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void printUserByRange(List<User> userList) {
        Map<Integer, Set<String>> map = userList.stream()
                .collect(groupingBy(this::generatePointKey, mapping(User::getUserId, toSet())));
        map.forEach((integer, strings) -> System.out.println("Point: " + integer + " Id: " + strings));
    }

    private int generatePointKey(User user) {
        if (user.getActivePoint() < 20) {
            return 20;
        } else if (user.getActivePoint() < 40) {
            return 40;
        } else if (user.getActivePoint() < 60) {
            return 60;
        } else if (user.getActivePoint() < 80) {
            return 80;
        } else if (user.getActivePoint() < 100) {
            return 100;
        } else if (user.getActivePoint() < 120) {
            return 120;
        } else if (user.getActivePoint() < 140) {
            return 140;
        } else
            return 160;
    }

    private void printActivePointInSpecifiedTime(List<User> userList, SimpleDateFormat dateFormat, Date date1) {
        userList.stream()
                .filter(user -> dateFormat.format(user.getTime()).compareTo(dateFormat.format(date1)) == 0)
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));
    }

    private void printTime(List<User> userList, SimpleDateFormat dateFormat) {
        List<String> timeList = userList.stream()
                .map(user -> dateFormat.format(user.getTime()))
                .collect(Collectors.toList());

        timeList.forEach(System.out::println);
    }

    private void print5HighestPoint(List<User> userList) {
        userList.stream()
                .sorted(Comparator.comparing(User::getActivePoint).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    private void printUser(List<User> userList) {
        List<String> userIdList = userList.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
        userIdList.forEach(System.out::println);
    }

    private void getAverage(List<User> userList) {
        double averageValue = userList.stream()
                .collect(groupingBy(this::generateUserKey, mapping(User::getActivePoint, toList())))
                .values()
                .stream()
                .flatMap(Collection::stream)
                .mapToInt(integer -> integer)
                .average().getAsDouble();

        Map<String, Double> averageMap1 = userList.stream()
                //Map doesn't extends Collection class => cannot stream directly
                .collect(groupingBy(this::generateUserKey, mapping(User::getActivePoint, toList())))
                //Convert to set
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .mapToInt(integer -> integer)
                        .average()
                        .getAsDouble()));
        //Sum / Size
        Map<String, Double> averageMap2 = userList.stream()
                .collect(groupingBy(this::generateUserKey, mapping(User::getActivePoint, toList())))
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .reduce(Integer::sum)
                        .orElse(0) / (e.getValue().size() * 1.0)));
        Map<String, Double> avrgMap = userList.stream()
                .collect(groupingBy(this::generateUserKey, averagingDouble(User::getActivePoint)));

//       averageMap.forEach((s, aDouble) -> System.out.println("ID: " + s + " Average Point: " + aDouble));
//       avrgMap.forEach((s, aDouble) -> System.out.println("ID: " + s + " Average Point: " + aDouble));
        averageMap2.forEach((s, aDouble) -> System.out.println("ID: " + s + " Average Point: " + aDouble));
    }

    private String generateUserKey(User user) {
        return user.getUserId();
    }

//    private void splitTime(List<User> userList, SimpleDateFormat dateFormat) {
////        List<String> splitTimeList = printTime(userList, dateFormat);
//        Map<String, List<String>> timeMap = userList.stream()
//                .collect(groupingBy(this::generateTimeKey), mapping(User::getUserId, toList()));
////        map.forEach((integer, strings) -> System.out.println("Point: " + integer + " Id: " + strings));
//    }

//    private String generateTimeKey(User user) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("10-03-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("14-03-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("14-03-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("15-03-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("21-03-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("21-03-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("22-03-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("28-03-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("28-03-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("29-03-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("04-04-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("04-04-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("05-04-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("11-04-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("11-04-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("12-04-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("18-04-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("18-04-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("19-04-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("25-04-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("25-04-2021"));
//        else if (dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat.parse("25-04-2021"))) > 0
//                && dateFormat.format(user.getTime()).compareTo(String.valueOf(dateFormat
//                .parse("29-04-2021"))) < 0)
//            return String.valueOf(dateFormat.parse("29-04-2021"));
//        else
//            return String.valueOf(dateFormat.parse("29-04-2021"));
//    }


    //Get average Active Point from 15/4. Average point of 15/4 is average point from day 11 -> 14
    private void getAverageByTime(List<User> userList, SimpleDateFormat dateFormat) throws ParseException {
        Date date = dateFormat.parse("15-04-2021");
//        Map<String, List<Integer>> timeMap = userList.stream()
//                .filter(user -> dateFormat.format(user.getTime()).compareTo(dateFormat.format(date)) > 0)
//                .collect(groupingBy(user -> dateFormat.format(user.getTime()), mapping(User::getActivePoint, toList())));
//
//        timeMap.forEach((aLong, stringList) -> System.out.println("Time: " + aLong + " || Point: " + stringList));

//        timeMap.entrySet()
//                .stream()
//                .collect(toMap());

        long dateMilliseconds = date.getTime();
        System.out.println(dateMilliseconds);
        List<Long> timeList = userList.stream()
                .map(User::getTime)
                .filter(time -> time >= dateMilliseconds)
                .distinct()
                .sorted(Comparator.comparing(aLong -> aLong))
                .collect(toList());


        /// lay thoi gian tu ngay 15 den hien tai
//        asList(15L, 16L, 17L)
//                .stream()
//                .map(longTime -> {
//                    userList.stream()
//                            .filter(user -> user.getTime() >= getStartTimeOfDayBeforeXDay(longTime, 4) &&
//                                    user.getTime() < getEndTime(longTime));
//                    return new DatePoint();
//                })

        List<DatePoint> collect = timeList.stream()
                .map(longTime -> {
                    OptionalDouble average = userList.stream()
                            .filter(user1 -> user1.getTime() >= getStartTimeOfDayBeforeXDay(longTime, 4) &&
                                    user1.getTime() < getEndTime(longTime))
                            .mapToDouble(User::getActivePoint)
                            .average();
                    return new DatePoint()
                            .setTime(longTime)
                            .setPoint(average.orElse(0.0));
                })
                .collect(toList());
        collect.forEach(System.out::println);

//        List<List<User>> collectUser = timeList.stream()
//                .map(longTime -> {
//                    List<User> newList = userList.stream()
//                            .filter(user -> user.getTime() >= getStartTimeOfDayBeforeXDay(longTime, 4) &&
//                                    user.getTime() < getEndTime(longTime))
//                            .collect(toList());
//                    return newList;
//                })
//                .collect(toList());

//        double averagePointOfSequenceOfTime = collectUser.stream()
//                .flatMap(Collection::stream)
//                .mapToInt(user -> user.getActivePoint())
//                .average()
//                .getAsDouble();

//        List<List<List<Integer>>> collectAveragePoint = collectUser.stream()
//                .map(userList1 -> {
//                    return collectUser.stream()
//                            .map(userList2 -> {
//                                return userList2.stream()
//                                        .map(user -> user.getActivePoint())
//                                        .collect(toList());
//                            })
//                            .collect(toList());
//                })
//                .collect(toList());
//
//        double averagePointOfSequenceOfTime = collectAveragePoint.stream()
//                .flatMap(Collection::stream)
//                .flatMap(Collection::stream)
//                .mapToInt(integer -> integer)
//                .average()
//                .getAsDouble();

//        System.out.println(averagePointOfSequenceOfTime);
    }
//    private String getTimeBefore(User user) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat();
//        Calendar c = Calendar.getInstance();
//        String date = dateFormat.format(user.getTime());
//    }

//    private String getTimeKey(User user) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat();
//        Date date1 = dateFormat.parse("15-04-2021");
//        if (dateFormat.format(user.getTime()).compareTo(dateFormat.format(date1)) == 0)
//            return dateFormat.format()
//    }
}
