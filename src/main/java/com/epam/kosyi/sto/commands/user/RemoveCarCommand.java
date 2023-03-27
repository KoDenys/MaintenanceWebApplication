package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Car;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.entities.User;
import com.epam.kosyi.sto.Path;
import org.iban4j.Iban;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

import static com.epam.kosyi.sto.Path.*;

public class RemoveCarCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Car car = DAOFactory.getDAOFactory().CarDAO().getCar(Integer.parseInt(request.getParameter("carId")));

        Path path = null;

       if(DAOFactory.getDAOFactory().CarDAO().removeCar(car, user)) {
           session.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));
           if(user.getUserType().getUserType().equals("admin")) path = new Path(ADMIN_MENU, true);
              else{
                  path = new Path(CAR_MENU, true);
              }
       }
       else{
           Repair repair = new Repair();
           repair.setDescription("Request to remove car");
           repair.setRepairSum(0);
           repair.setDiscount(0);
           repair.setCount(1);
           repair.setWorkerId(58);
           repair.setRepairStateId(1);
           repair.setCarId(car.getCarId());
           repair.setCurrentMileage(car.getCarMileage());
           repair.setRepairTypeId(777);
           repair.setPriceListId(777);


           if (DAOFactory.getDAOFactory().getRepairDAO().createRepair(repair)){

               return new Path(Path.SUCCESS_REQUEST_PAGE, true);
           }
           else{
               return new Path(Path.FAILED_PAGE, true);
           }
       }

        session.setAttribute("currentPage", path);

        return path;
    }

}