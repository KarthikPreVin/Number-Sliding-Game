package Panels;

import java.awt.Component;

public interface AppInterface {
    public void destroy();

    public void call4x4();

    public void call3x3();

    public void displayMenu(String user);

    public void checkHighScore(String mode, int moves);

    public void showCreatePage();

    public void showLoginPage();

    public void callComp();

    public void removeChild();
}
