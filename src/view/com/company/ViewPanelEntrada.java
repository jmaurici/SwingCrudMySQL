package view.com.company;

import dao.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewPanelEntrada extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panelEntrada;
    private JButton dialog;
    private JTable table1;
    private JButton asignaturasButton;
    private JButton personasButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;

    public JTextField getTituloTabla() {
        return tituloTabla;
    }

    private JTextField tituloTabla;


    public ViewPanelEntrada() {

        super("PROYECTO PROGRAMACIÓN JAVA / mySQL");
        setContentPane(panelEntrada);
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize((int) ancho/2, (int) alto/2);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("ha salido del programa");
                DBConnection.closeConn();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }
    public JPanel getPanelEntrada() {
        return panelEntrada;
    }
    public JButton getDialog() {
        return dialog;
    }

    public JTable getTable1() {
        return table1;
    }


    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }


    public JButton getPersonasButton() {
        return personasButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
