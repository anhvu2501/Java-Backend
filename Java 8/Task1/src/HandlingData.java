import User.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HandlingData {
    static String userId;
    static int activePoint;
    static long time;

    public static void main(String[] args) throws ParseException {
        List<User> userList = new ArrayList<>();
        try {
            String url = "/home/vuhta/Java Backend Intern/Java 8/Task1/user_point_active.txt";
            FileInputStream fileInputStream = new FileInputStream(url);
            Scanner input = new Scanner(fileInputStream);
            while (input.hasNextLine()) {
                String userInfo = input.nextLine();
//                System.out.println(userInfo);
                String[] userSplit = userInfo.split(",");
                for (String s : userSplit) {
                    if (s.contains("user_id")) {
                        String[] userIdSplit = s.split(":");
                        for (String value : userIdSplit) {
                            if (!value.contains("user_id")) {
                                userId = value.substring(1, value.length() - 1);
                            }
                        }
                    }

                    if (s.contains("active_point")) {
                        String[] activeSplit = s.split(":");
                        for (String value : activeSplit) {
                            if (!value.contains("active_point")) {
                                activePoint = Integer.parseInt(value);
                            }
                        }
                    }

                    if (s.contains("time")) {
                        String[] timeSplit = s.split(":");
                        for (String value : timeSplit) {
                            if (!value.contains("time")) {
                                time = Long.parseLong(value);
                            }
                        }
                    }
                }
                userList.add(new User(userId, activePoint, time));
                // json string to json object , json object to object
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

//        for (User user : userList)
//            System.out.println(user.toString());

        //Print out all users
        List<String> userIdList = userList.stream()
                .map(user -> user.getUserId())
                .collect(Collectors.toList());

        userIdList.forEach(System.out::println);

//        User max = userList
//                .stream()
//                .max(Comparator.comparing(User ::getActivePoint))
//                .get();
//        System.out.println(max);
//
//        userList.stream()
//                .filter(user -> user.getActivePoint() <= max.getActivePoint())
//                .limit(5)
//                .forEach(System.out::println);


        //Print 5 users have the highest activePoint
        userList.stream()
                .sorted((u1, u2) -> u2.getActivePoint() - u1.getActivePoint())
                .limit(5)
                .forEach(System.out::println);

        //Get average point ??
//        userList.stream()
//                .mapToInt(u -> u.getActivePoint())
//                .average()
//                .getAsDouble();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        String dateText = df2.format(date);
        Date date1 = dateFormat.parse("01-04-2021");

        //Print out all day time
        List<String> daytime = userList.stream()
                .map(user -> dateFormat.format(user.getTime()))
                .collect(Collectors.toList());

        daytime.forEach(System.out::println);

        //Active Point of users in 01/04/2021
        userList.stream()
                .filter(user -> dateFormat.format(user.getTime()).compareTo(dateFormat.format(date1)) == 0)
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

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


        userList.stream()
                .filter(user -> user.getActivePoint() >= 0 && user.getActivePoint() < 20)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        // -> map<String,User>

//
        userList.stream()
                .filter(user -> user.getActivePoint() >= 20 && user.getActivePoint() < 40)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 40 && user.getActivePoint() < 60)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 60 && user.getActivePoint() < 80)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 80 && user.getActivePoint() < 100)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 100 && user.getActivePoint() < 120)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 120 && user.getActivePoint() < 140)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 140 && user.getActivePoint() < 160)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));

        userList.stream()
                .filter(user -> user.getActivePoint() >= 160)
                .sorted(Comparator.comparing(User::getTime))
                .forEach(user -> System.out.println(user.getUserId() + "\t"
                        + user.getActivePoint() + "\t"
                        + dateFormat.format(user.getTime())));


    }
}
