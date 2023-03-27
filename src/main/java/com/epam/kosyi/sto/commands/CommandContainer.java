package com.epam.kosyi.sto.commands;

import com.epam.kosyi.sto.commands.admin.*;
import com.epam.kosyi.sto.commands.common.*;
import com.epam.kosyi.sto.commands.user.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("adminMenu", new AdminMenuCommand());
        commands.put("blockUser", new BlockUserCommand());
        commands.put("blockCar", new BlockCarCommand());
        commands.put("showCars", new ShowCarsCommand());
        commands.put("showAllCars", new ShowAllCarsCommand());
        commands.put("showRequests", new ShowRequestsCommand());
        commands.put("editRequestPage", new EditRequestPageCommand());
        commands.put("editRequest", new EditRequestCommand());
        commands.put("removeRequest", new RemoveRequestCommand());
        commands.put("unblockUser", new UnblockUserCommand());
        commands.put("giveMoney", new GiveMoneyCommand());

        commands.put("login", new LoginCommand());
        commands.put("login_page", new LoginPageCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("reg", new RegistrationCommand());
        commands.put("reg_page", new RegistrationPageCommand());
        commands.put("switchLanguage", new SwitchLanguageCommand());

        commands.put("main", new MainMenuCommand());
        commands.put("addCar", new AddCarCommand());
        commands.put("getCar", new CarDetailCommand());
        commands.put("removeCar", new RemoveCarCommand());
        commands.put("addRequestPage", new AddRepairPageCommand());
        commands.put("addRepair", new AddRepair());
        commands.put("payCommand", new PayCommand());
        commands.put("payPageCommand", new PayPageCommand());
        commands.put("backCommand", new BackCommand());
        commands.put("submitPage", new SubmitPageCommand());

    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
