package dev.galliard.contasoc;

import dev.galliard.contasoc.ui.ContasocLaf;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.util.FileManager;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ErrorHandler;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class Contasoc {
    public static final String ESCRITORIO = "C:/Users/" + System.getProperty("user.name") + "/Desktop";
    public static final String BASEDIR = "C:/Contasoc/";
    public static final String VERSION = "6.0";
    public static void main(String[] args) throws SQLException {
        ContasocLaf.setup();
        if(Files.notExists(Path.of(BASEDIR+"contasoc2.db"))) {
            try {
                FileManager.createFile("contasoc2.db", BASEDIR);
                //ContasocDAO.tablesFromScript("createTables.sql");
                //ContasocDAO.triggersFromScript("createTriggers.sql");
                ContasocDAO.createTablesAndTriggers();
            } catch (Exception e) {
                ErrorHandler.errorAlCrearBDD();
            }
        }
        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}