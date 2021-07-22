package main.java.com.insurance.main;

import main.java.com.insurance.menu.InsSysMenu;

import java.sql.SQLException;

public class Main {

    public static InsSysMenu insSysMenu = new InsSysMenu();
    public static void main(String[] args) throws SQLException {
       insSysMenu.initialMenu();

    }


}
