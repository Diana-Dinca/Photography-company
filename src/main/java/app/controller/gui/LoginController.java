package app.controller.gui;

import app.service.LocatiiService;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.LoginView;

public class LoginController {

    private LoginView loginView;

    private LocatiiService userService = ServiceSinglePointAccess.getLocatiiService();

    public void startLogic() {
        /*loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getLoginPanel(), "Login");

        loginView.getLogInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = loginView.getTextFieldName().getText();
                String password = new String(loginView.getPasswordField().getPassword());

                Clienti user = userService.login(name, password);
                if (user != null) {
                    UserDetailsController userDetailsController = new UserDetailsController();
                    userDetailsController.startLogic(user);
                } else {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid username or password");
                }
            }
        });*/
    }
}
