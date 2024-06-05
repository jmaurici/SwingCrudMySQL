package model.com.company;
// POJO
public class Asignatura {
    private int id;
    private String nombre;
    private float creditos;
    private String tipo;
    private int curso ;
    private int cuatrimestre;
    private int idProfesor;
    private int idGrado;

    public Asignatura(int id, String nombre, float creditos, String tipo, int curso, int cuatrimestre, int idProfesor, int idGrado) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.tipo = tipo;
        this.curso = curso;
        this.cuatrimestre = cuatrimestre;
        this.idProfesor = idProfesor;
        this.idGrado = idGrado;
    }

    public Asignatura() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }
}