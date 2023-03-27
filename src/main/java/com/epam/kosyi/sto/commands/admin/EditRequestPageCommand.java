package com.epam.kosyi.sto.commands.admin;

import com.epam.kosyi.sto.Path;
import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.entities.*;

import com.epam.kosyi.sto.commands.Command;
import com.epam.kosyi.sto.dao.factory.DAOFactory;
import com.epam.kosyi.sto.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditRequestPageCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EDIT request Command#1");
        Path path = new Path(Path.EDIT_REQUEST_PAGE, false);
        int id = Integer.parseInt(request.getParameter("repairId"));
        Repair repair = DAOFactory.getDAOFactory().getRepairDAO().getRepair(id);
        List<RepairType> repairTypeList = DAOFactory.getDAOFactory().getRepairDAO().getListRepairType(repair.getRepairedCar());
        for(int i = 0; i<repairTypeList.size();i++){
            if(repairTypeList.get(i).getRepairTypeId()==repair.getRepairTypeId()){
                repairTypeList.remove(i);
            }
        }
        List<User> staff = DAOFactory.getDAOFactory().getUserDAO().getStaffUsers(repair.getWorkerId());
        List<RepairState> repairStates = DAOFactory.getDAOFactory().getRepairDAO().getListRepairState(repair.getRepairStateId());



        request.getSession().setAttribute("repair", repair);
        request.getSession().setAttribute("staff", staff);
        request.getSession().setAttribute("statelist", repairStates);
        request.getSession().setAttribute("repairtypelist",repairTypeList);
        request.getSession().setAttribute("currentPage", path);

        return path;
    }

}