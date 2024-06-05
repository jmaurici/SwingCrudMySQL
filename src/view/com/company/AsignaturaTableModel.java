package view.com.company;

import model.com.company.Asignatura;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AsignaturaTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "NOMBRE", "CREDITOS", "TIPO", "CURSO",
            "CUATRIMESTRE", "ID PROFESOR", "ID GRADO"};
    private List<Asignatura> asignaturas;
    private JButton[] botones;

    public AsignaturaTableModel(List<Asignatura> asignaturas, JButton[] botonera) {
        this.asignaturas = asignaturas;
        this.botones = botonera;
    }

    @Override
    public int getRowCount() {
        return asignaturas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Asignatura asignatura = asignaturas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return asignatura.getId();
            case 1:
                return asignatura.getNombre();
            case 2:
                return asignatura.getCreditos();
            case 3:
                return asignatura.getTipo();
            case 4:
                return asignatura.getCurso();
            case 5:
                return asignatura.getCuatrimestre();
            case 6:
                return asignatura.getIdProfesor();
            case 7:
                return asignatura.getIdGrado();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0; // ID should not be editable
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Asignatura asignatura = asignaturas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                asignatura.setId(Integer.parseInt((String) aValue));
                break;
            case 1:
                asignatura.setNombre((String) aValue);
                break;
            case 2:
                asignatura.setCreditos((Float) aValue);
                break;
            case 3:
                asignatura.setTipo((String) aValue);
                break;
            case 4:
                asignatura.setCurso(Integer.parseInt((String) aValue));
                break;
            case 5:
                asignatura.setCuatrimestre(Integer.parseInt((String) aValue));
                break;
            case 6:
                asignatura.setIdProfesor(Integer.parseInt((String) aValue));
                break;
            case 7:
                asignatura.setIdGrado(Integer.parseInt((String) aValue));
                break;
        }
        botones[0].setEnabled(false);
        botones[1].setEnabled(true);
        botones[2].setEnabled(true);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
        fireTableRowsInserted(asignaturas.size() - 1, asignaturas.size() - 1);
    }

    public void removeAsignatura(int rowIndex) {
        asignaturas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Asignatura getAsignaturaAt(int rowIndex) {
        return asignaturas.get(rowIndex);
    }

    public void fireTableCellUpdated(int row, int column) {

    }

}
