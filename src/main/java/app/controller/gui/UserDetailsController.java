package app.controller.gui;

import app.view.UserDetailsView;

public class UserDetailsController {

    private UserDetailsView userDetailsView;

    /*private LocatiiService userService = ServiceSinglePointAccess.getUserService();


    public void startLogic(Clienti user) {
        userDetailsView = new UserDetailsView();
        GUIFrameSinglePointAccess.changePanel(userDetailsView.getMainPanel(), "Welcome " + user.getName());

        userDetailsView.getNameValue().setText(user.getName());
        userDetailsView.getSalaryValue().setText(user.getSalary().toString());
        userDetailsView.getStreetValue().setText(user.getAddress().getStreet());
        userDetailsView.getCityValue().setText(user.getAddress().getCity());

        userDetailsView.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });
    }*/
}
