package Controler.com.company;

import dao.PersonaDAO;
import model.com.company.Persona;
import view.com.company.PersonTableModel;
import view.com.company.ViewPanelEntrada;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class PersonasController implements ActionListener {
    private ViewPanelEntrada formEntrada;

    public PersonasController(ViewPanelEntrada formEntrada) {
        this.formEntrada = formEntrada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonaDAO personaDAO = new PersonaDAO();
        JButton[] botones = {formEntrada.getAddButton(), formEntrada.getDeleteButton(), formEntrada.getSaveButton()};
        PersonTableModel tableModel = new PersonTableModel(personaDAO.getAllPersonas(), botones);
        JTable tabla = formEntrada.getTable1();
        formEntrada.getTituloTabla().setText("P E R S O N A S");
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
                formEntrada.getDeleteButton().setEnabled(true);
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
        formEntrada.getAddButton().setEnabled(true); // al inicio lo habilitamos..
        formEntrada.getDeleteButton().setEnabled(false);
        // "damos vida" a los 3 botones operacionales..
        formEntrada.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona person = new Persona(0, "NIF", "Pepito", "APELLIDO1", "APELLIDO2",
                        "CIUDAD", "DIRECCION", "TELEFONO", "1985-5-18", "H", "alumno");
                if (personaDAO.insertPersona(person)) {
                    tableModel.addPerson(person);
                    tabla.setRowSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
                    // detección de que se ha pulsado sobre la tabla
                    tabla.scrollRectToVisible(tabla.getCellRect(tableModel.getRowCount() - 1, 0, true));
                    formEntrada.getDeleteButton().setEnabled(true);
                }
            }
        });
        formEntrada.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = formEntrada.getTable1().getSelectedRow();
                if (selectedRow >= 0) {
                    Persona persona = tableModel.getPersonAt(selectedRow);
                    if (personaDAO.deletePersona(persona.getId())) // verifica confirmación
                        tableModel.removePerson(selectedRow);
                }
            }
        });
        formEntrada.getSaveButton().addActionListener(new ActionListener() {
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
}

