package Controler.com.company;

import dao.AsignaturaDAO;
import dao.GradoDAO;
import model.com.company.Asignatura;
import model.com.company.Grado;
import model.com.company.Persona;
import view.com.company.AsignaturaTableModel;
import view.com.company.ViewPanelEntrada;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

public class AsignaturasController implements ActionListener {
    private ViewPanelEntrada formEntrada;

    public AsignaturasController(ViewPanelEntrada frEntrada) {
        this.formEntrada = frEntrada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AsignaturaDAO asigDAO = new AsignaturaDAO();
        JButton[] botones = {formEntrada.getAddButton(), formEntrada.getDeleteButton(), formEntrada.getSaveButton()};
        AsignaturaTableModel tableModel = new AsignaturaTableModel(asigDAO.getAllAsignaturas(), botones);
        JTable tabla = formEntrada.getTable1();
        formEntrada.getTituloTabla().setText("A S I G N A T U R A S");
        tabla.setModel(tableModel);
        // asignamos combo a columna tipo
        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"obligatoria", "optativa", "básica"});
        TableColumn tipoColumn = tabla.getColumnModel().getColumn(3); // cols. desde 0
        tipoColumn.setCellEditor(new DefaultCellEditor(tipoComboBox));


        // asignamos combo a columna id_grado, leyendo la tabla grado
        /*List<Grado> lGrados = new GradoDAO().getAllGrados();
        JComboBox<Grado> comboGrados = new JComboBox<Grado>();
        for (Grado grado : lGrados) {
            comboGrados.addItem(grado);
        }
        TableColumn idGradoColumn = tabla.getColumnModel().getColumn(7); // cols. desde 0
        idGradoColumn.setCellEditor(new DefaultCellEditor(comboGrados));

*/
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
        botones[0].setEnabled(true);
        //formEntrada.getAddButton().setEnabled(true); // al inicio lo habilitamos..
        // asignamos vida a los botones operacionales..
        formEntrada.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Asignatura asignatura = new Asignatura(0, "Gimnasia", 1.2f, "básica", 123,
                        2, 3, 1);

                if (asigDAO.insertAsignatura(asignatura)) {
                    tableModel.addAsignatura(asignatura);
                    tabla.setRowSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
                    // Optionally, scroll the table to make the selected row visible
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
                    Asignatura asignatura = tableModel.getAsignaturaAt(selectedRow);
                    if (asigDAO.deleteAsignatura(asignatura.getId())) // verifica confirmación
                        tableModel.removeAsignatura(selectedRow);
                }
            }
        });
        formEntrada.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Asignatura asignatura = tableModel.getAsignaturaAt(i);
                    botones[0].setEnabled(true);
                    botones[2].setEnabled(false);
                    try {
                        asigDAO.updateAsignatura(asignatura);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error UPDATE", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}

