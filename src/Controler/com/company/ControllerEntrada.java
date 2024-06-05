package Controler.com.company;

import view.com.company.*;
import javax.swing.*;

public class ControllerEntrada  {
    private final ViewPanelEntrada frEntrada = new ViewPanelEntrada();

    public ControllerEntrada() {
        iniciarEventos();
        frEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frEntrada.setVisible(true);
    }

    public void iniciarEventos() {
        // botones tablas
        frEntrada.getAsignaturasButton().addActionListener(new AsignaturasController(frEntrada));
        frEntrada.getPersonasButton().addActionListener(new PersonasController(frEntrada));
    }
}