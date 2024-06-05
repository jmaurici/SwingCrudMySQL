package Controler.com.company;

import dao.AsignaturaDAO;
import dao.PersonaDAO;
import model.com.company.Asignatura;
import model.com.company.Persona;
import view.com.company.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.sql.SQLException;

public class ControllerEntrada implements ActionListener {
    private final ViewPanelEntrada frEntrada = new ViewPanelEntrada();

    public ControllerEntrada() {
        iniciarEventos();
        frEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frEntrada.setVisible(true);
    }

    public void iniciarEventos() {
        // botones tablas
        frEntrada.getAsignaturasButton().addActionListener(this::actionPerformed);
        //  frEntrada.getPersonasButton().addActionListener(this::actionPerformed);
        frEntrada.getPersonasButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonaDAO personaDAO = new PersonaDAO();
//                PersonTableModel tableModel = new PersonTableModel(personaDAO.getAllPersonas());
                JButton[] botones = {frEntrada.getAddButton(), frEntrada.getDeleteButton(), frEntrada.getSaveButton()};
                PersonTableModel tableModel = new PersonTableModel(personaDAO.getAllPersonas(), botones);
                JTable tabla = frEntrada.getTable1();
                frEntrada.getTituloTabla().setText("P E R S O N A S");
                tabla.setModel(tableModel);
                // asignamos combo a columna sexo
                JComboBox<String> sexoComboBox = new JComboBox<>(new String[]{"M", "H"});
                TableColumn sexoColumn = tabla.getColumnModel().getColumn(9); // cols. desde 0
                sexoColumn.setCellEditor(new DefaultCellEditor(sexoComboBox));
                // asignamos combo a columna tipo
                JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"profesor", "alumno"});
                TableColumn tipoColumn = tabla.getColumnModel().getColumn(10); // cols. desde 0
                tipoColumn.setCellEditor(new DefaultCellEditor(tipoComboBox));
                // para activar el botón "Borrar" , al hacer click en la tabla
                tabla.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frEntrada.getDeleteButton().setEnabled(true);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                frEntrada.getAddButton().setEnabled(true); // al inicio lo habilitamos..
                // asignamos vida a los botones operacionales..
                frEntrada.getAddButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Persona person = new Persona(0, "NIF", "Pepito", "APELLIDO1", "APELLIDO2",
                                "CIUDAD", "DIRECCION", "TELEFONO", "1985,5,18", "H", "alumno");


                        if (personaDAO.insertPersona(person)) {
                            tableModel.addPerson(person);
                            tabla.setRowSelectionInterval(tableModel.getRowCount()-1,tableModel.getRowCount()-1 );
                            // Optionally, scroll the table to make the selected row visible
                            tabla.scrollRectToVisible(tabla.getCellRect(tableModel.getRowCount()-1, 0, true));
                            frEntrada.getDeleteButton().setEnabled(true);
                        }
                    }
                });
                frEntrada.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = frEntrada.getTable1().getSelectedRow();
                        if (selectedRow >= 0) {
                            Persona persona = tableModel.getPersonAt(selectedRow);
                            personaDAO.deletePersona(persona.getId());
                            tableModel.removePerson(selectedRow);
                        }
                    }
                });
                frEntrada.getSaveButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            Persona person = tableModel.getPersonAt(i);
                            botones[0].setEnabled(true);
                            botones[2].setEnabled(false);
                            try {
                                personaDAO.updatePersona(person);

                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error UPDATE", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("??????????????");
    }
}
