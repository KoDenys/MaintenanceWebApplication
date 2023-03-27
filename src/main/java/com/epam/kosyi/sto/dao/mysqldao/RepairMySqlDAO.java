package com.epam.kosyi.sto.dao.mysqldao;

import com.epam.kosyi.sto.dao.factory.Connector;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.*;
import com.epam.kosyi.sto.dao.interfaces.RepairDAO;
import com.epam.kosyi.sto.sql.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RepairMySqlDAO implements RepairDAO {
    private static volatile RepairMySqlDAO repairMySqlDAO;

    public static RepairMySqlDAO getInstance() {
        RepairMySqlDAO localInstance = repairMySqlDAO;
        if (localInstance == null) {
            synchronized (RepairMySqlDAO.class) {
                localInstance = repairMySqlDAO;
                if (localInstance == null) {
                    repairMySqlDAO = localInstance = new RepairMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private RepairMySqlDAO() {
    }


    @Override
    public Repair getRepair(int id) {
        Repair repair = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.GET_REPAIR_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    repair = mapRepair(resultSet);
                    repair.setRepairType(getRepairType(repair.getRepairTypeId()));
                    repair.setRepairState(getRepairState(repair.getRepairStateId()));
                    repair.setRepairedCar(DAOFactory.getDAOFactory().CarDAO().getCar(repair.getCarId()));
                    repair.setPriceList(getPriceByRepairId(repair.getRepairTypeId(),repair.getRepairedCar()));
                    User worker = DAOFactory.getDAOFactory().getUserDAO().getUser(repair.getWorkerId());
                    User owner = DAOFactory.getDAOFactory().getUserDAO().getUser(repair.getRepairedCar().getUserId());
                    repair.setWorker(worker);
                    repair.setOwner(owner);
                    repair.setFeedback(getFeedback(repair.getRepairId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repair;
    }

    @Override
    public boolean editRepair(Repair repair) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.UPDATE_REPAIR)) {
            preparedStatement.setDouble(1, repair.getRepairSum());
            preparedStatement.setString(2, repair.getDescription());
            preparedStatement.setDouble(3, repair.getDiscount());
            preparedStatement.setInt(4, repair.getCount());
            preparedStatement.setInt(5, repair.getWorkerId());
            preparedStatement.setInt(6, repair.getRepairStateId());
            preparedStatement.setInt(7, repair.getCarId());
            preparedStatement.setInt(8, repair.getRepairTypeId());
            preparedStatement.setInt(9, repair.getPriceListId());
            preparedStatement.setInt(10, repair.getCurrentMileage());
            preparedStatement.setInt(11, repair.getRepairId());

            if (preparedStatement.executeUpdate() > 0) {
                result = true;
                if(getRepairStateByName("Finished").getRepairStateId()==repair.getRepairStateId()){
                    updateBalance(repair.getOwner().getCoupon(), repair.getRepairSum());
                }


                }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeRepair(User user, Repair repair) {
        boolean result = false;
        String userType = user.getUserType().getUserType();
        if( userType.equals( "admin") || userType.equals( "manager")){
            result=true;
        }
        else if(userType.equals( "user")){
            if(repair.getRepairState().getStateValueEn().equals("Created")){
                result = true;
            }
            else{
                result = false;
            }
        }
        if(result) {
            result = false;
            try (Connection connection = Connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.REMOVE_REPAIR)) {
                preparedStatement.setInt(1, repair.getRepairId());
                if (preparedStatement.executeUpdate() > 0) {
                    result = true;
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
                return false;
            }
        }

        return result;
    }

    public List<Repair> getAllRepairs() {
        List<Repair> repairs = new ArrayList<>();
        Repair repair;
        System.out.println("getAllRepairs");
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.GET_ALL_REPAIRS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    repair = mapRepair(resultSet);
                    repair.setRepairType(getRepairType(repair.getRepairTypeId()));
                    repair.setRepairState(getRepairState(repair.getRepairStateId()));
                    repair.setRepairedCar(DAOFactory.getDAOFactory().CarDAO().getCar(repair.getCarId()));
                    repair.setPriceList(getPriceByRepairId(repair.getRepairTypeId(), repair.getRepairedCar()));
                    User worker = DAOFactory.getDAOFactory().getUserDAO().getUser(repair.getWorkerId());
                    User owner = DAOFactory.getDAOFactory().getUserDAO().getUser(repair.getRepairedCar().getUserId());
                    repair.setWorker(worker);
                    repair.setOwner(owner);
                    repair.setFeedback(getFeedback(repair.getRepairId()));
                    repairs.add(repair);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repairs;
    }

    @Override
    public List<Repair> getRequests(Car car) {
        List<Repair> repairs = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.GET_REPAIR)) {
            preparedStatement.setInt(1, car.getCarId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Repair repair = mapRepair(resultSet);
                    repair.setRepairState(getRepairState(repair.getRepairStateId()));
                    repair.setRepairType(getRepairType(repair.getRepairTypeId()));
                    repair.setPriceList(getPriceByRepairId(repair.getRepairTypeId(), car));
                    repair.setRepairedCar(car);
                    repair.setFeedback(getFeedback(repair.getRepairId()));
                    repairs.add(repair);

                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repairs;
    }

    @Override
    public boolean makePayment(Repair repair){
        boolean result = false;
        double sum;
        Connection connection = Connector.getConnection();
        try {
            assert connection != null;
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            if(repair.getDiscount()<0 || repair.getDiscount()>repair.getRepairSum())return false;
            if(repair.getDiscount()>0) assert updateBalance(repair.getDiscount(), repair.getOwner().getCoupon(), connection);
            sum = repair.getRepairSum() - repair.getDiscount();
            assert sum>=0;
            assert updateMoney(sum, repair.getOwner(), connection);

            repair.setRepairStateId(getRepairStateByName("Payed").getRepairStateId());
            editRepair(repair);
            connection.commit();
            connection.setAutoCommit(true);
            result=true;

        } catch (AssertionError | SQLException exception) {
            try {
                System.out.println("CATCH");
                assert connection != null;
                connection.rollback();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        }
        finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    boolean updateMoney(Double sum, User user, Connection connection){
        boolean result = false;
        double bill = user.getUserInfo().getMoney() - sum;
            if (bill>=0) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(UserInfoSQL.UPDATE_MONEY);
                    preparedStatement.setDouble(1, bill);
                    preparedStatement.setInt(2, user.getUserInfoId());
                    if (preparedStatement.executeUpdate() > 0) {
                        result = true;
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        return result;
    }


    boolean updateBalance(Coupon coupon, Double sum){
        boolean result = false;
        double balance;
        balance = sum*0.05+coupon.getBalance();
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CouponSQL.UPDATE_COUPON_BALANCE)){
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, coupon.getCouponId());
            if(preparedStatement.executeUpdate()>0){
                result = true;
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
        return result;
    }


    boolean updateBalance(Double discount, Coupon coupon, Connection connection){
        boolean result = false;
        coupon.setBalance(coupon.getBalance()-discount);
        if(coupon.getBalance()>=0) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(CouponSQL.UPDATE_COUPON_BALANCE);
                preparedStatement.setDouble(1, coupon.getBalance());
                preparedStatement.setInt(2, coupon.getCouponId());
                if (preparedStatement.executeUpdate() > 0) {
                    result = true;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public RepairState getRepairState(int id) {
        RepairState repairState = new RepairState();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairStateSQL.GET_REPAIR_STATES_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    repairState = mapRepairState(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repairState;
    }

    public RepairType getRepairType(int id) {
        RepairType repairType = new RepairType();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairTypeSQL.GET_REPAIR_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    repairType = mapRepairType(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repairType;
    }


    @Override
    public boolean createFeedback(Feedback feedback){
        boolean result = false;

        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FeedbackSQL.CREATE_FEEDBACK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, feedback.getText());
            preparedStatement.setDouble(2, feedback.getStars());
            preparedStatement.setInt(3, feedback.getRepairId());
            if ( preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        feedback.setFeedbackId(resultSet.getInt(1));
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

    public Feedback getFeedback(int id) {
        Feedback feedback = new Feedback();

        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FeedbackSQL.GET_FEEDBACK)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    feedback = mapFeedback(resultSet);
                }
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }

        return feedback;
    }

    @Override
    public boolean createRepair(Repair repair) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairSQL.CREATE_REPAIR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, repair.getRepairSum());
            preparedStatement.setString(2, repair.getDescription());
            preparedStatement.setDouble(3, repair.getDiscount());
            preparedStatement.setInt(4, repair.getCount());
            preparedStatement.setInt(5, repair.getWorkerId());
            preparedStatement.setInt(6, repair.getRepairStateId());
            preparedStatement.setInt(7, repair.getCarId());
            preparedStatement.setInt(8, repair.getRepairTypeId());
            preparedStatement.setInt(9, repair.getPriceListId());
            preparedStatement.setInt(10, repair.getCurrentMileage());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        repair.setRepairId(resultSet.getInt(1));
                        result = true;
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private Repair mapRepair(ResultSet resultSet) throws SQLException {
        Repair repair = new Repair();

        repair.setRepairId(resultSet.getInt(RepairSQL.REPAIR_ID));
        repair.setRepairSum(resultSet.getDouble(RepairSQL.REPAIR_SUM));
        repair.setDescription(resultSet.getString(RepairSQL.REPAIR_DESCRIPTION));
        repair.setRepairDateTime(resultSet.getTimestamp(RepairSQL.REPAIRE_DATETIME));
        repair.setDiscount(resultSet.getInt(RepairSQL.DISCOUNT));
        repair.setCount(resultSet.getInt(RepairSQL.COUNT));
        repair.setCurrentMileage(resultSet.getInt(RepairSQL.CURRENT_MILEAGE));
        repair.setWorkerId(resultSet.getInt(RepairSQL.WORKER));
        repair.setRepairStateId(resultSet.getInt(RepairSQL.REPAIR_STATE));
        repair.setCarId(resultSet.getInt(RepairSQL.CAR));
        repair.setRepairTypeId(resultSet.getInt(RepairSQL.REPAIR_TYPE));
        repair.setPriceListId(resultSet.getInt(RepairSQL.PRICE_LIST));

        return repair;
    }

    private RepairState mapRepairState(ResultSet resultSet) throws SQLException {
        RepairState repairState = new RepairState();

        repairState.setRepairStateId(resultSet.getInt(RepairStateSQL.REPAIR_STATE_ID));
        repairState.setStateValueEn(resultSet.getString(RepairStateSQL.STATE_VALUE_EN));
        repairState.setStateValueUk(resultSet.getString(RepairStateSQL.STATE_VALUE_UK));

        return repairState;
    }
    private PriceList mapPriceList(ResultSet resultSet) throws SQLException {
        PriceList priceList = new PriceList();

        priceList.setPriceListId(resultSet.getInt(PriceListSQL.PRICE_LIST_ID));
        priceList.setPrice(resultSet.getDouble(PriceListSQL.PRICE));
        priceList.setGuarantee(resultSet.getInt(PriceListSQL.GUARANTEE));
        priceList.setRepairType(resultSet.getInt(PriceListSQL.REPAIR_TYPE));

        return priceList;
    }

    private RepairType mapRepairType(ResultSet resultSet) throws SQLException {
        RepairType repairType = new RepairType();

        repairType.setRepairTypeId(resultSet.getInt(RepairTypeSQL.REPAIR_TYPE_ID));
        repairType.setRepairTypeNameEn(resultSet.getString(RepairTypeSQL.REPAIR_TYPE_NAME_EN));
        repairType.setRepairTypeNameUk(resultSet.getString(RepairTypeSQL.REPAIR_TYPE_NAME_UK));

        return repairType;
    }


    private Feedback mapFeedback(ResultSet resultSet) throws SQLException{
        Feedback feedback = new Feedback();

        feedback.setFeedbackId(resultSet.getInt(FeedbackSQL.FEEDBACK_ID));
        feedback.setText(resultSet.getString(FeedbackSQL.TEXT));
        feedback.setStars(resultSet.getDouble(FeedbackSQL.STARS));
        feedback.setRepairId(resultSet.getInt(FeedbackSQL.REPAIR_ID));

        return feedback;
    }


    public List<RepairState> getListRepairState(int id) {
        List<RepairState> repairState = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairStateSQL.GET_ALL_REPAIR_STATE)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    RepairState rp = mapRepairState(resultSet);
                    if(rp.getRepairStateId()==id){
                        continue;
                    }
                    repairState.add(rp);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if(repairState.isEmpty()) {
            return null;
        }
        return repairState;
    }

    public RepairState getRepairStateByName(String nameEn) {
        RepairState repairState = new RepairState();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairStateSQL.GET_REPAIR_STATE_BY_NAME)) {
            preparedStatement.setString(1, nameEn);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                     repairState = mapRepairState(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return repairState;
    }

    public PriceList getPriceByRepairId(int repairId, Car car){
        PriceList priceList = new PriceList();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PriceListSQL.GET_PRICE_LIST_BY_REPAIR_ID_FOR_CAR)) {
            preparedStatement.setInt(1, repairId);
            preparedStatement.setString(2, car.getCarName());
            preparedStatement.setString(3, car.getCarModel());
            preparedStatement.setInt(4, car.getCarYear());
            preparedStatement.setInt(5, car.getCarYear());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   priceList = mapPriceList(resultSet);
                }
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        return priceList;
    }

    public Map<Integer,PriceList> getMapPriseList(Car car){
        Map<Integer,PriceList> map = new TreeMap<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PriceListSQL.GET_PRICE_LIST_FOR_CAR)) {
            preparedStatement.setString(1, car.getCarName());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setInt(3, car.getCarYear());
            preparedStatement.setInt(4, car.getCarYear());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PriceList priceList = new PriceList();
                    priceList.setPriceListId(resultSet.getInt(PriceListSQL.PRICE_LIST_ID));
                    priceList.setPrice(resultSet.getDouble(PriceListSQL.PRICE));
                    priceList.setRepairType(resultSet.getInt(PriceListSQL.REPAIR_TYPE));
                   map.put(priceList.getRepairType(), priceList);

                }
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        if(!map.isEmpty()){
            return map;
        }
        return null;
     }

    public List<RepairType> getListRepairType(Car car){
        List<RepairType> list = new ArrayList<>();
        Map<Integer, PriceList> priceMap = DAOFactory.getDAOFactory().getRepairDAO().getMapPriseList(car);
        PriceList priceNull = new PriceList();
        priceNull.setPrice(0);

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairTypeSQL.GET_ALL_REPAIR_TYPE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RepairType rp = new RepairType();
                    rp.setRepairTypeId(resultSet.getInt(RepairTypeSQL.REPAIR_TYPE_ID));
                    rp.setRepairTypeNameEn(resultSet.getString(RepairTypeSQL.REPAIR_TYPE_NAME_EN));
                    rp.setRepairTypeNameUk(resultSet.getString(RepairTypeSQL.REPAIR_TYPE_NAME_UK));
                    if(priceMap==null || rp.getRepairTypeId()==777){
                        rp.setPriceList(priceNull);
                    }
                    else {
                        rp.setPriceList(priceMap.get(rp.getRepairTypeId()));
                    }
                    list.add(rp);
                }
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

}
