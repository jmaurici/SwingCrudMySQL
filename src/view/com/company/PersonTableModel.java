package view.com.company;


import model.com.company.Persona;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "NIF", "NOMBRE", "APELLIDO1", "APELLIDO2",
            "CIUDAD", "DIRECCION", "TELEFONO", "FECHA NAC.", "SEXO", "TIPO"};
    private List<Persona> personas;
    private JButton[] botones;

    public PersonTableModel(List<Persona> persons, JButton[] botonera) {
        this.personas = persons;
        this.botones = botonera;
    }

    @Override
    public int getRowCount() {
        return personas.size();
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
        Persona person = personas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getNif();
            case 2:
                return person.getNombre();
            case 3:
                return person.getApellido1();
            case 4:
                return person.getApellido2();
            case 5:
                return person.getCiudad();
            case 6:
                return person.getDireccion();
            case 7:
                return person.getTelefono();
            case 8:
                return person.getFechaNac();
            case 9:
                return person.getSexo();
            case 10:
                return person.getTipo();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0; // ID should not be editable
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Persona person = personas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                person.setId(Integer.parseInt((String) aValue));
                break;
            case 1:
                person.setNif((String) aValue);
                break;
            case 2:
                person.setNombre((String) aValue);
                break;
            case 3:
                person.setApellido1((String) aValue);
                break;
            case 4:
                person.setApellido2((String) aValue);
                break;
            case 5:
                person.setCiudad((String) aValue);
                break;
            case 6:
                person.setDireccion((String) aValue);
                break;
            case 7:
                person.setTelefono((String) aValue);
                break;
            case 8:
                person.setFechaNac((String) aValue);
                break;
            case 9:
                person.setSexo((String) aValue);
                break;
            case 10:
                person.setTipo((String) aValue);
                break;
        }
        botones[0].setEnabled(false);
        botones[1].setEnabled(true);
        botones[2].setEnabled(true);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addPerson(Persona person) {
        personas.add(person);
       /* botones[0].setEnabled(true);  // se desactiva el botón "Nuevo"
        botones[1].setEnabled(false);
        botones[2].setEnabled(false);  // se activa el botón "Guardar"
       */ fireTableRowsInserted(personas.size() - 1, personas.size() - 1);
    }

    public void removePerson(int rowIndex) {
        personas.remove(rowIndex);
        /*botones[0].setEnabled(true);  // se activa el botón "Nuevo"
        botones[1].setEnabled(false);
        botones[2].setEnabled(false);
      */  fireTableRowsDeleted(rowIndex, rowIndex);
    }
    public Persona getPersonAt(int rowIndex) {
        return personas.get(rowIndex);
    }
}