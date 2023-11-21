
package proyecto.Capa4_PersistenciaAdmin;

import proyecto.Capa3_DominioAdmin.Horario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;


public class HorarioPostgreSQL {
      private final AccesoDatosJDBC accesoDatosJDBC;

    public HorarioPostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }
     public void guardarhorario2(Horario horario , String horainicio ,String horafinal ,String [][] lista) throws Exception {
      
        String insertSQL = "insert into horarios(id_doctor, fechaatencion, hora,estado) "
                + "values(?,?,?,?)";
        PreparedStatement sentencia;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fecha=dateFormat.format(horario.getFechaatencion());
            String iddoctor=String.valueOf(horario.getDoctor().getIddoctor());
            Horario h = new  Horario();
            lista=h.Generarhorario(horainicio, horafinal,horario);
          
            for(int i =0;i<lista.length;i++){
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);   
            sentencia.setInt(1,Integer.parseInt(iddoctor));
            sentencia.setDate(2,Date.valueOf(fecha));
            sentencia.setTime(3,(Time.valueOf(lista[i][0]))); 
            sentencia.setBoolean(4, false);  
            sentencia.executeUpdate();
            }      
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"ERROR AL REGISTRAR horarios sqllll"+e);
        }
     }
    
   public Vector  ConsultarLista(Horario horario) throws  Exception{ 
          String sql = "select cast(horarios.hora as varchar)  from horarios where horarios.id_doctor=? and horarios.fechaatencion=?";
          PreparedStatement sentencia;
          Vector b = new Vector();
          try{
               DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             String fecha=dateFormat.format(horario.getFechaatencion());
             sentencia=accesoDatosJDBC.prepararSentencia(sql);
            sentencia.setInt(1, horario.getDoctor().getIddoctor());
            sentencia.setDate(2, Date.valueOf(fecha));      
             ResultSet resultado=sentencia.executeQuery();
            ResultSetMetaData d =resultado.getMetaData();
              int ca =d.getColumnCount();
            if(resultado.next()){           
             while (resultado.next()) {               
                b.add(resultado.getString(1));
                 System.out.println("mostrardatoshoy"+b.get(0));
           }
           }
            else{               
               b=null;
            }
              return  b ;  
         } 
          catch (Exception e) {
           JOptionPane.showMessageDialog(null,"ERROR AL REGISTRAR PORQUE ="+e);
        }
          return b;         
         }
   
   public void editarestadohorario(int id) throws Exception{
         String insertSQL = "UPDATE horarios  SET estado='True' WHERE idhorario=?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
            if(sentencia.executeUpdate() > 0){
                
        } }
            catch (SQLException e) {
            throw new Exception("Error.", e);
        }
     }
 
      

}
