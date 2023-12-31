/*
 * Created by JFormDesigner on Mon Dec 25 05:45:33 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.text.AbstractDocument;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.*;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class AddModifySocios extends JFrame {
    protected static Action accion;
    protected static String tempNumeroSocio;
    private static AddModifySocios instance;
    private AddModifySocios() {
        initComponents();
        setActions();
        setFilters();
    }

    public static AddModifySocios getInstance() {
        if (instance == null) {
            instance = new AddModifySocios();
        }
        return instance;
    }

    private void setActions() {
        javax.swing.Action enterAction = new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarBtnActionPerformed(e);
            }
        };
        JPanel contentPane = (JPanel) this.getContentPane();
        KeyStroke nuevoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(nuevoKeyStroke, "Enter");
        contentPane.getActionMap().put("Enter", enterAction);
    }

    private void setFilters() {
        java.util.List<JTextField> lista = List.of(nombreField,dniField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                List<String> ins = new ArrayList<>();
                ins.add(nombreField.getText());
                ins.add(dniField.getText());
                ins.add(telefonoField.getText());
                ins.add(emailField.getText());
                ins.add(socioField.getText());
                ins.add(huertoField.getText());
                ins.add(Parsers.dashDateParserReversed(altaField.getText()));
                ins.add(Parsers.dashDateParserReversed(entregaField.getText()));
                ins.add(Parsers.dashDateParserReversed(bajaField.getText()));
                ins.add(notasField.getText());
                ins.add((String) tipoSocioComboBox.getSelectedItem());
                System.out.println(ins);
                if(DNIValidator.validarDNI(dniField.getText()) || DNIValidator.validarNIE(dniField.getText())) {
                    ContasocDAO.insert("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                    "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                            ins.toArray(String[]::new));
                    GUIManager.populateGUITables();
                    for(JTextField jtf : List.of(nombreField, dniField, telefonoField, emailField, socioField, huertoField,
                            altaField, entregaField, bajaField, notasField)) {
                        jtf.setText("");
                    }
                    this.dispose();
                } else {
                    ErrorHandler.errorAlLeerDNI();
                }
                break;
            case "MODIFY":
                List<String> upd = new ArrayList<>();
                upd.add(nombreField.getText());
                upd.add(dniField.getText());
                upd.add(telefonoField.getText());
                upd.add(emailField.getText());
                upd.add(socioField.getText());
                upd.add(huertoField.getText());
                upd.add(Parsers.dashDateParserReversed(altaField.getText()));
                upd.add(Parsers.dashDateParserReversed(entregaField.getText()));
                upd.add(Parsers.dashDateParserReversed(bajaField.getText()));
                upd.add(notasField.getText());
                upd.add((String) tipoSocioComboBox.getSelectedItem());
                if(DNIValidator.validarDNI(dniField.getText()) || DNIValidator.validarNIE(dniField.getText())) {
                    ContasocDAO.update("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                    "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                            upd.toArray(String[]::new),
                            new String[] {"numeroSocio =" + tempNumeroSocio
                            });
                    GUIManager.populateGUITables();
                    for(JTextField jtf : List.of(nombreField, dniField, telefonoField, emailField, socioField, huertoField,
                            altaField, entregaField, bajaField, notasField)) {
                        jtf.setText("");
                    }
                    this.dispose();
                } else {
                    ErrorHandler.errorAlLeerDNI();
                }
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        nombreField = new JTextField();
        dniLabel = new JLabel();
        dniField = new JTextField();
        telefonoLabel = new JLabel();
        telefonoField = new JTextField();
        emailLabel = new JLabel();
        emailField = new JTextField();
        socioLabel = new JLabel();
        socioField = new JTextField();
        huertoLabel = new JLabel();
        huertoField = new JTextField();
        altaLabel = new JLabel();
        altaField = new JTextField();
        entregaLabel = new JLabel();
        entregaField = new JTextField();
        bajaLabel = new JLabel();
        bajaField = new JTextField();
        notasLabel = new JLabel();
        notasField = new JTextField();
        estadoLabel = new JLabel();
        tipoSocioComboBox = new JComboBox<>();
        separator2 = new JSeparator();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} socio");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 24 12 26",
            // columns
            "[grow,fill]" +
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Nombre:");
        nombreLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        nombreLabel.setFont(nombreLabel.getFont().deriveFont(nombreLabel.getFont().getSize() + 4f));
        contentPane.add(nombreLabel, "cell 0 0,width 64:64:64,height 32:32:32");

        //---- nombreField ----
        nombreField.setFont(nombreField.getFont().deriveFont(nombreField.getFont().getSize() + 4f));
        nombreField.setNextFocusableComponent(dniField);
        contentPane.add(nombreField, "cell 0 0,width 200:200:200,height 24:24:24");

        //---- dniLabel ----
        dniLabel.setText("DNI/NIE:");
        dniLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        dniLabel.setFont(dniLabel.getFont().deriveFont(dniLabel.getFont().getSize() + 4f));
        contentPane.add(dniLabel, "cell 0 1,width 64:64:64,height 32:32:32");

        //---- dniField ----
        dniField.setFont(dniField.getFont().deriveFont(dniField.getFont().getSize() + 4f));
        dniField.setNextFocusableComponent(telefonoField);
        contentPane.add(dniField, "cell 0 1,width 200:200:200,height 24:24:24");

        //---- telefonoLabel ----
        telefonoLabel.setText("Tel\u00e9fono:");
        telefonoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        telefonoLabel.setFont(telefonoLabel.getFont().deriveFont(telefonoLabel.getFont().getSize() + 4f));
        contentPane.add(telefonoLabel, "cell 0 2,width 64:64:64,height 32:32:32");

        //---- telefonoField ----
        telefonoField.setFont(telefonoField.getFont().deriveFont(telefonoField.getFont().getSize() + 4f));
        telefonoField.setNextFocusableComponent(emailField);
        contentPane.add(telefonoField, "cell 0 2,width 200:200:200,height 24:24:24");

        //---- emailLabel ----
        emailLabel.setText("Email:");
        emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getSize() + 4f));
        contentPane.add(emailLabel, "cell 0 3,width 64:64:64,height 32:32:32");

        //---- emailField ----
        emailField.setFont(emailField.getFont().deriveFont(emailField.getFont().getSize() + 4f));
        emailField.setNextFocusableComponent(socioField);
        contentPane.add(emailField, "cell 0 3,width 200:200:200,height 24:24:24");

        //---- socioLabel ----
        socioLabel.setText("Socio:");
        socioLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        socioLabel.setFont(socioLabel.getFont().deriveFont(socioLabel.getFont().getSize() + 4f));
        contentPane.add(socioLabel, "cell 0 4,width 64:64:64,height 32:32:32");

        //---- socioField ----
        socioField.setFont(socioField.getFont().deriveFont(socioField.getFont().getSize() + 4f));
        socioField.setToolTipText("Si no se pone se autoincrementa");
        socioField.setNextFocusableComponent(huertoField);
        contentPane.add(socioField, "cell 0 4,width 200:200:200,height 24:24:24");

        //---- huertoLabel ----
        huertoLabel.setText("Huerto:");
        huertoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        huertoLabel.setFont(huertoLabel.getFont().deriveFont(huertoLabel.getFont().getSize() + 4f));
        contentPane.add(huertoLabel, "cell 0 5,width 64:64:64,height 32:32:32");

        //---- huertoField ----
        huertoField.setFont(huertoField.getFont().deriveFont(huertoField.getFont().getSize() + 4f));
        huertoField.setNextFocusableComponent(altaField);
        contentPane.add(huertoField, "cell 0 5,width 200:200:200,height 24:24:24");

        //---- altaLabel ----
        altaLabel.setText("Alta:");
        altaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        altaLabel.setFont(altaLabel.getFont().deriveFont(altaLabel.getFont().getSize() + 4f));
        contentPane.add(altaLabel, "cell 1 0,width 64:64:64,height 32:32:32");

        //---- altaField ----
        altaField.setFont(altaField.getFont().deriveFont(altaField.getFont().getSize() + 4f));
        altaField.setNextFocusableComponent(entregaField);
        contentPane.add(altaField, "cell 1 0,width 200:200:200,height 24:24:24");

        //---- entregaLabel ----
        entregaLabel.setText("Entrega:");
        entregaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        entregaLabel.setFont(entregaLabel.getFont().deriveFont(entregaLabel.getFont().getSize() + 4f));
        contentPane.add(entregaLabel, "cell 1 1,width 64:64:64,height 32:32:32");

        //---- entregaField ----
        entregaField.setFont(entregaField.getFont().deriveFont(entregaField.getFont().getSize() + 4f));
        entregaField.setNextFocusableComponent(bajaField);
        contentPane.add(entregaField, "cell 1 1,width 200:200:200,height 24:24:24");

        //---- bajaLabel ----
        bajaLabel.setText("Baja:");
        bajaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bajaLabel.setFont(bajaLabel.getFont().deriveFont(bajaLabel.getFont().getSize() + 4f));
        contentPane.add(bajaLabel, "cell 1 2,width 64:64:64,height 32:32:32");

        //---- bajaField ----
        bajaField.setFont(bajaField.getFont().deriveFont(bajaField.getFont().getSize() + 4f));
        bajaField.setNextFocusableComponent(notasField);
        contentPane.add(bajaField, "cell 1 2,width 200:200:200,height 24:24:24");

        //---- notasLabel ----
        notasLabel.setText("Notas:");
        notasLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        notasLabel.setFont(notasLabel.getFont().deriveFont(notasLabel.getFont().getSize() + 4f));
        contentPane.add(notasLabel, "cell 1 3,width 64:64:64,height 32:32:32");

        //---- notasField ----
        notasField.setFont(notasField.getFont().deriveFont(notasField.getFont().getSize() + 4f));
        notasField.setNextFocusableComponent(nombreField);
        contentPane.add(notasField, "cell 1 3,width 200:200:200,height 24:24:24");

        //---- estadoLabel ----
        estadoLabel.setText("Tipo:");
        estadoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        estadoLabel.setFont(estadoLabel.getFont().deriveFont(estadoLabel.getFont().getSize() + 4f));
        contentPane.add(estadoLabel, "cell 1 4,width 64:64:64,height 32:32:32");

        //---- tipoSocioComboBox ----
        tipoSocioComboBox.setFont(tipoSocioComboBox.getFont().deriveFont(tipoSocioComboBox.getFont().getSize() + 4f));
        tipoSocioComboBox.setSelectedItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO_INVERNADERO");
        tipoSocioComboBox.addItem("COLABORADOR");
        tipoSocioComboBox.addItem("LISTA_ESPERA");
        contentPane.add(tipoSocioComboBox, "cell 1 4,width 200:200:200,height 24:24:24");

        //---- separator2 ----
        separator2.setBackground(new Color(0xf7f8fa));
        separator2.setForeground(new Color(0xf7f8fa));
        contentPane.add(separator2, "cell 1 5,width 64:64:64");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(aceptarBtn.getFont().deriveFont(aceptarBtn.getFont().getSize() + 4f));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 5,height 24:24:24");
        setSize(615, 350);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JTextField nombreField;
    protected static JLabel dniLabel;
    protected static JTextField dniField;
    protected static JLabel telefonoLabel;
    protected static JTextField telefonoField;
    protected static JLabel emailLabel;
    protected static JTextField emailField;
    protected static JLabel socioLabel;
    protected static JTextField socioField;
    protected static JLabel huertoLabel;
    protected static JTextField huertoField;
    protected static JLabel altaLabel;
    protected static JTextField altaField;
    protected static JLabel entregaLabel;
    protected static JTextField entregaField;
    protected static JLabel bajaLabel;
    protected static JTextField bajaField;
    protected static JLabel notasLabel;
    protected static JTextField notasField;
    protected static JLabel estadoLabel;
    protected static JComboBox<String> tipoSocioComboBox;
    protected static JSeparator separator2;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
