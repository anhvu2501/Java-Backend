package task;

import user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class HandlingData {
    public void handle() throws ParseException, IOException {
        List<User> userList = readUsers();
        //Print out all users
        printUser(userList);

        //Print 5 users have the highest activePoint
        print5HighestPoint(userList);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        String dateText = df2.format(date);
        Date date1 = dateFormat.parse("01-04-2021");

        //Print out all day time
        printTime(userList, dateFormat);

        //Active Point of users in 01/04/2021
        printActivePointInSpecifiedTime(userList, dateFormat, date1);

        System.out.println("------------- Matrix user --------------");

//        userList.stream()
//                .filter(user -> user.getActivePoint() >= 0 && user.getActivePoint() < 20)
//                .collect(Collectors.groupingBy(u -> dateFormat.format(u.getTime())))
////                    => group by day
//                .values()
//                .forEach(users -> users
//                        .forEach(user -> System.out.println(user.getUserId() + "\t"
//                                + user.getActivePoint() + "\t"
//                                + dateFormat.format(user.getTime())))
//                );

        printUserByRange(userList, dateFormat);
    }

    private void printUserByRange(List<User> userList, SimpleDateFormat dateFormat) {
//        Map<Integer, List<User>> collect = userList.stream()
//                .collect(groupingBy(HandlingData::generatePointKey));
//
//        Map<Integer, Set<String>> collect1 = collect.entrySet()
//                .stream()
//                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().stream().map(User::getUserId).collect(toSet())));

        Map<Integer, Set<String>> map = userList.stream()
                .collect(groupingBy(this::generatePointKey, mapping(User::getUserId, toSet())));

//        userList.stream()
//                .filter(user -> user.getActivePoint() >= 160)
//                .sorted(Comparator.comparing(User::getTime))
//                .forEach(user -> System.out.println(user.getUserId() + "\t"
//                        + user.getActivePoint() + "\t"
//                        + dateFormat.format(user.getTime())));
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
        } else {
            return 120;
        }
    }

    private void printActivePointInSpecifiedTime(List<User> userList, SimpleDateFormat dateFormat, Date date1) {
        userList.stream()
                .filter(user -> dateFormat.format(user.getTime()).compareTo(dateFormat.format(date1)) == 0)
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));
    }

    private void printTime(List<User> userList, SimpleDateFormat dateFormat) {
        List<String> daytime = userList.stream()
                .map(user -> dateFormat.format(user.getTime()))
                .collect(Collectors.toList());

        daytime.forEach(System.out::println);
    }

    private void print5HighestPoint(List<User> userList) {
        userList.stream()
                .sorted((u1, u2) -> u2.getActivePoint() - u1.getActivePoint())
                .limit(5)
                .forEach(System.out::println);
    }

    private void printUser(List<User> userList) {
        List<String> userIdList = userList.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
        userIdList.forEach(System.out::println);
    }

    private List<User> readUsers() throws IOException {
        String fileName = "/home/vuhta/Java Backend/Java 8/Task1/user_point_active.txt";
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
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
        String[] userSplit = string.split(",");
        for (String s : userSplit) {
            if (s.contains("user_id") && s.contains("active_point") && s.contains("time")) {
                User user = new User();
                String[] userIdSplit = s.split(":");
                for (String value : userIdSplit) {
                    if (!value.contains("user_id")) {
                        user.setUserId(value.substring(1, value.length() - 1));
                        break;
                    }
                }
                String[] activeSplit = s.split(":");
                for (String value : activeSplit) {
                    if (!value.contains("active_point")) {
                        user.setActivePoint(Integer.parseInt(value));
                    }
                }
                String[] timeSplit = s.split(":");
                for (String value : timeSplit) {
                    if (!value.contains("time")) {
                        user.setTime(Long.parseLong(value));
                    }
                }
                return user;
            }
        }
        return null;
    }
}
