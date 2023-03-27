package com.epam.kosyi.sto.dao.factory;

import com.epam.kosyi.sto.dao.interfaces.CarDAO;
import com.epam.kosyi.sto.dao.interfaces.RepairDAO;
import com.epam.kosyi.sto.dao.interfaces.UserDAO;


public abstract class DAOFactory {
    /**
     * @return a DAO depending on its type
     */
    public static DAOFactory getDAOFactory() {
        return new MySqlDAO();
    }

    /**
     * @return DAO object for User
     */
    public abstract UserDAO getUserDAO();
    /**
     * @return DAO object for Car
     */
    public abstract CarDAO CarDAO();
    /**
     * @return DAO object for Payments
     */
    public abstract RepairDAO getRepairDAO();
}
