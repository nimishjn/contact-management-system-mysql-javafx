package app;

public final class UserSession {

    private static UserSession instance;
    private static String userName;

    private UserSession(String userName) {
        UserSession.userName = userName;
    }

    public static UserSession getInstace(String userName) {
        if(instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public static void setUserName(String user) {
        UserSession.userName = user;
    }

    public static String getUserName() {
        return UserSession.userName;
    }

    public static void cleanUserSession() {
        UserSession.userName = "";
    }

    @Override
    public String toString() {
        return "UserSession{" + "userName='" + userName + '\'' + '}';
    }
}