package main.java.Framework.bo;

import static main.java.yandex.product.disk.Configs.USER_LOGIN;
import static main.java.yandex.product.disk.Configs.USER_PASWD;

public class UserCreator {


    public static User fromPropertyCredentialsUser() {
        return new User(USER_LOGIN,USER_PASWD);
    }

    public static User withOwnCredentialsUser(String login, String password) {
        return new User(login,password);
    }

}