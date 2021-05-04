package user;

public class User {
    private String userId;
    private int activePoint;
    private long time;

    public User() {
    }

    public User(String userId, int activePoint, long time) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setActivePoint(int activePoint) {
        this.activePoint = activePoint;
    }

    public void setTime(long time) {
        this.time = time;
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

