package com.epam.kosyi.sto.dao.interfaces;

import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.entities.UserInfo;
import com.epam.kosyi.sto.entities.UserType;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    List<User> getStaffUsers(int id);

    User getUser(String login, String pass);

    User getUser(int id);

    UserInfo getUserInfo(int id);

    UserType getUserType(int id);

    boolean addUser(User user);

    boolean setActiveForUser(User user, boolean state);

    Integer addUserInfo(UserInfo userInfo);

    boolean giveMoney(Double sum, UserInfo userInfo);
}
