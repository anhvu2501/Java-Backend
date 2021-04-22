package User;

import java.util.Date;

public class User {
    private String userId;
    private int activePoint;
    private long time;

    public User (String userId, int activePoint, long time) {
        this.userId = userId;
        this.activePoint = activePoint;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public int getActivePoint() {
        return activePoint;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", activePoint=" + activePoint +
                ", time=" + time +
                '}';
    }


}

