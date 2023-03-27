package com.epam.kosyi.sto.dao.mysqldao;

import com.epam.kosyi.sto.dao.factory.Connector;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.*;
import com.epam.kosyi.sto.dao.interfaces.UserDAO;
import com.epam.kosyi.sto.sql.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMySqlDAO implements UserDAO {

    private static volatile UserMySqlDAO userMySqlDAO;

    public static UserMySqlDAO getInstance() {
        UserMySqlDAO localInstance = userMySqlDAO;
        if (localInstance == null) {
            synchronized (UserMySqlDAO.class) {
                localInstance = userMySqlDAO;
                if (localInstance == null) {
                    userMySqlDAO = localInstance = new UserMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private UserMySqlDAO() {
    }

    @Override
    public User getUser(String login, String pass) {
        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_USER_BY_LOGIN_AND_PASS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setCars(DAOFactory.getDAOFactory().CarDAO().getCarsForUser(user));
                    user.setCoupon(getCoupon(user.getUserId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(int id) {
        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setCars(DAOFactory.getDAOFactory().CarDAO().getCarsForUser(user));
                    user.setCoupon(getCoupon(user.getUserId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }


    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = new UserInfo();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserInfoSQL.GET_USER_INFO_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userInfo = mapUserInfo(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userInfo;
    }

    @Override
    public UserType getUserType(int id) {
        UserType userType = new UserType();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserTypeSQL.GET_USER_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = mapUserType(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userType;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_ALL_USERS)) {
            preparedStatement.setInt(1, 1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setCars(DAOFactory.getDAOFactory().CarDAO().getCarsForUser(user));
                    user.setCoupon(getCoupon(user.getUserId()));
                    userList.add(user);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userList;
    }

    @Override
    public List<User> getStaffUsers(int id) {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_STAFF_USERS)) {
            preparedStatement.setInt(1, 1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = mapUser(resultSet);
                    if(user.getUserId() == id){
                        continue;
                    }
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setCars(DAOFactory.getDAOFactory().CarDAO().getCarsForUser(user));
                    user.setCoupon(getCoupon(user.getUserId()));
                    userList.add(user);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserSQL.ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isActive());
            preparedStatement.setInt(4, user.getUserType().getTypeId());
            preparedStatement.setObject(5, addUserInfo(user.getUserInfo()));

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        user.setUserId(resultSet.getInt(1));
                        System.out.println("CREATE USER# userId = "+ user.getUserId());
                        createCoupon(user.getUserId());
                        result = true;
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean setActiveForUser(User user, boolean state) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.BLOCK_USER)) {
            preparedStatement.setBoolean(1, state);
            preparedStatement.setInt(2, user.getUserId());

            if (preparedStatement.executeUpdate() > 0){
                user.setActive(state);
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public Integer addUserInfo(UserInfo userInfo) {
        Integer result = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserInfoSQL.ADD_USER_INFO, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getLastName());
            preparedStatement.setDouble(3, userInfo.getMoney());
            preparedStatement.setString(4, userInfo.getPhoneNumber());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userInfo.setId(resultSet.getInt(1));
                        result = userInfo.getId();
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public boolean giveMoney(Double sum, UserInfo userInfo){
        boolean result = false;
        userInfo.setMoney(userInfo.getMoney()+sum);
            try (Connection connection = Connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UserInfoSQL.UPDATE_MONEY)) {
                preparedStatement.setDouble(1, userInfo.getMoney());
                preparedStatement.setInt(2, userInfo.getId());
                if (preparedStatement.executeUpdate() > 0) {
                    result = true;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        return result;
    }

    public boolean createCoupon(int id){
        boolean result = false;

        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CouponSQL.CREATE_COUPON_FOR_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            if ( preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        result = true;
                    }
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

        return result;
    }

    public Coupon getCoupon(int id) {
        Coupon coupon = new Coupon();

        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CouponSQL.GET_COUPON_FOR_USER)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    coupon = mapCoupon(resultSet);
                }
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }

        return coupon;
    }

    private Coupon mapCoupon(ResultSet resultSet) throws SQLException {
        Coupon coupon = new Coupon();

        coupon.setCouponId(resultSet.getInt(CouponSQL.COUPON_ID));
        coupon.setBalance(resultSet.getDouble(CouponSQL.BALANCE));
        coupon.setUser(resultSet.getInt(CouponSQL.USER));

        return coupon;
    }


    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getInt(UserSQL.ID));
        user.setLogin(resultSet.getString(UserSQL.LOGIN));
        user.setPassword(resultSet.getString(UserSQL.PASSWORD));
        user.setUserTypeId(resultSet.getInt(UserSQL.USER_TYPE));
        user.setActive(resultSet.getBoolean(UserSQL.IS_ACTIVE));
        user.setUserInfoId(resultSet.getInt(UserSQL.USER_INFO));

        return user;
    }

    private UserInfo mapUserInfo(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(resultSet.getInt(UserInfoSQL.USER_INFO_ID));
        userInfo.setFirstName(resultSet.getString(UserInfoSQL.FIRST_NAME));
        userInfo.setLastName(resultSet.getString(UserInfoSQL.LAST_NAME));
        userInfo.setMoney(resultSet.getDouble(UserInfoSQL.MONEY));
        userInfo.setPhoneNumber(resultSet.getString(UserInfoSQL.PHONE_NUMBER));

        return userInfo;
    }

    private UserType mapUserType(ResultSet resultSet) throws SQLException {
        UserType userType = new UserType();

        userType.setTypeId(resultSet.getInt(UserTypeSQL.USER_TYPE_ID));
        userType.setUserType(resultSet.getString(UserTypeSQL.USER_TYPE));

        return userType;
    }

}
