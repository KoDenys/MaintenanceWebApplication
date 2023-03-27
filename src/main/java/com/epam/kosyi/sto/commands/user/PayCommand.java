package com.epam.kosyi.sto.commands.user;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.Repair;
import com.epam.kosyi.sto.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PayCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path;

       Repair repair = (Repair) request.getSession().getAttribute("repair");
       repair.setDiscount(Double.parseDouble(request.getParameter("discount")));
        System.out.println("PAY Discount ="+ repair.getDiscount());
       if(DAOFactory.getDAOFactory().getRepairDAO().makePayment(repair)){
           path = new Path(Path.SUCCESS_REQUEST_PAGE, false);
       }
       else {
           path = new Path(Path.FAILED_PAGE, false);
       }

        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
