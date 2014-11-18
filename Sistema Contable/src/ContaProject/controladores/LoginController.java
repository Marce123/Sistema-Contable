
package ContaProject.controladores;
import ContaProject.bd.Conexion;
import ContaProject.modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Login Controller.
 */
public class LoginController extends AnchorPane {
    
    ContaProyectMain vistaMain;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Label lblMessage;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
//    Button login;

    private void btnLoginAction(ActionEvent event)throws Exception{
        if(txtUsername.getText().equals("") || txtPassword.getText().equals("")){
            lblMessage.setText("Los campos son obligatorios");
        }
        else{
//            int vista = validarUsuario(txtUsername.getText(), txtPassword.getText());
            String vista = validarUsuario(txtUsername.getText(), txtPassword.getText());
            String tipo = " ";
            
            try {
                        
            String sentenciaSql = "SELECT * FROM roles;";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareCall(sentenciaSql);
            ResultSet resultadoConsulta = preparedStatement.executeQuery();
            while (resultadoConsulta.next()) {
                
                String idRol = resultadoConsulta.getString("idRoles");
                String nombreRol = resultadoConsulta.getString("rol");
                
                if(vista.equalsIgnoreCase(idRol.trim())){
                    System.out.println("idRol "+idRol+" tiporol: "+nombreRol);
                    tipo = nombreRol.trim();
                }
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //***************************************Asignar vista*********************************************************
//    Este switch asigna la vista dependiendo del rol que del usuario 
            System.out.println("tiporo: "+tipo);
            switch(tipo){
//********* si el rol es administrador**********************************************************               
                case "Administrador":
                    //vista para administrador
                     vistaMain.MenuPrincipal();

                    break;
//********* si el rol es usuario**********************************************************
                case "Usuario":
                    //vista para usuario
                    break;
//********* si el rol es visitante**********************************************************
                case "Visitante":
                    //vista para visitante
                    break;
                    
                default:
                    lblMessage.setText("El usuario o contraseña es incorrecto");
                    break;                    
            }
            
        }
        
    }
    
    
//**********************ValidarUsuario*************************************************************************
//Este metodo valida el nombre y password de usuario con el que esta en la 
//    base de datos
    public String validarUsuario(String elUsr, String elPw)  throws IOException{
        
        try {
                        
            String sentenciaSql = "SELECT * FROM usuario;";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareCall(sentenciaSql);
            ResultSet resultadoConsulta = preparedStatement.executeQuery();
            while (resultadoConsulta.next()) {
                
                String idusuario = resultadoConsulta.getString("idusuario");
                String nombre = resultadoConsulta.getString("login");            
                String password = resultadoConsulta.getString("password");
                String userRol = resultadoConsulta.getString("userRol");

                
                if(elUsr.equalsIgnoreCase(nombre.trim())){
                    System.out.println("el usuario es valido");
                    if(elPw.equals(password.trim())){
                        System.out.println("el password es valido");
                        
                        return userRol.trim();

                    }

                }
                         
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    

//***************************************************************************************************
    private ContaProyectMain application;
    
    
    
    public void setApp(ContaProyectMain application){
        this.application = application;
    }
    
   
    
}
