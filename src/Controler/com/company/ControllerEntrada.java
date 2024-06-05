package Controler.com.company;

import dao.AsignaturaDAO;
import dao.PersonaDAO;
import model.com.company.Asignatura;
import model.com.company.Persona;
import view.com.company.*;

import javax.swing.*;
import java.awt.event.*;


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