package com.epam.kosyi.sto.dao.factory;

import com.epam.kosyi.sto.dao.interfaces.CarDAO;
import com.epam.kosyi.sto.dao.interfaces.RepairDAO;
import com.epam.kosyi.sto.dao.mysqldao.CarMySqlDAO;
import com.epam.kosyi.sto.dao.mysqldao.RepairMySqlDAO;
import com.epam.kosyi.sto.dao.mysqldao.UserMySqlDAO;

public class MySqlDAO extends DAOFactory {
    private static volatile MySqlDAO mySqlDAO;

    public static MySqlDAO getInstance() {
        MySqlDAO localInstance = mySqlDAO;
        if (localInstance == null) {
            synchronized (MySqlDAO.class) {
                localInstance = mySqlDAO;
                if (localInstance == null) {
                    mySqlDAO = localInstance = new MySqlDAO();
                }
            }
        }
        return localInstance;
    }

    @Override
    public UserMySqlDAO getUserDAO() {
        return UserMySqlDAO.getInstance();
    }

    @Override
    public CarDAO CarDAO() { return CarMySqlDAO.getInstance(); }

    @Override
    public RepairDAO getRepairDAO() {
        return RepairMySqlDAO.getInstance();
    }
}
