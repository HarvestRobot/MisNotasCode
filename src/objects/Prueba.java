/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import noname.DBConnection;

/**
 *
 * @author lucia
 */
public class Prueba {

    int idPrueba;
    int idAsignatura;
    String nombrePrueba;
    String fechaPrueba;
    int trimestre;
    double peso;

    public Prueba(int idPrueba, int idAsignatura, String nombrePrueba, String fechaPrueba, int trimestre, double peso) {
        this.idPrueba = idPrueba;
        this.idAsignatura = idAsignatura;
        this.nombrePrueba = nombrePrueba;
        this.fechaPrueba = fechaPrueba;
        this.trimestre = trimestre;
        this.peso = peso;
    }
    
    public Prueba(){
        
    }
    
    public void cargarPrueba(int idPrueba) throws SQLException {
        String sqlPruebas = "SELECT * FROM prueba WHERE idPrueba = " + idPrueba;

        Statement st = DBConnection.getConnection().createStatement();
        ResultSet resultPruebas = st.executeQuery(sqlPruebas);

        while (resultPruebas.next()) {
            this.idPrueba = resultPruebas.getInt("idPrueba");
            this.idAsignatura = resultPruebas.getInt("idAsignatura");
            this.nombrePrueba = resultPruebas.getString("nombrePrueba");
            this.fechaPrueba = resultPruebas.getString("fechaPrueba");
            this.trimestre = resultPruebas.getInt("trimestre");
            this.peso = resultPruebas.getDouble("peso");

        }
    }

    public void commitPrueba(Prueba prueba) {
        String sqlPrueba = "INSERT INTO prueba (idAsignatura, nombrePrueba, fechaPrueba, trimestre, peso) VALUES ('" 
                + prueba.getIdAsignatura() + "', '"
                + prueba.getNombrePrueba() + "', '"
                + prueba.getFechaPrueba() + "', '"
                + prueba.getTrimestre() + "', '"
                + prueba.getPeso() + "'"
                + ")";
        try {
            Statement st = DBConnection.getConnection().createStatement();
            st.executeUpdate(sqlPrueba);
        } catch (Exception e) {
            System.out.println("commitPrueba dice: " + e.toString());
        }
    }

    public int getIdPrueba() {
        return idPrueba;
    }
    
    public int getIdAsignatura() {
        return idAsignatura;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public String getFechaPrueba() {
        return fechaPrueba;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public double getPeso() {
        return peso;
    }
    
     public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    public void setFechaPrueba(String fechaPrueba) {
        this.fechaPrueba = fechaPrueba;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
