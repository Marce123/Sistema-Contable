
package ContaProject.modelo;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private static final Map<String, Usuario> USUARIO = new HashMap<String, Usuario>();
     //Variables
    private String id;
    private String email = "";
    private String phone = "";
    private boolean subscribed;
    private String address = "";
    
    //Metodo que devuelve un usuario
    public static Usuario of(String id) 
    {   //objeto de la clase Usuario
        Usuario user = USUARIO.get(id);
        if (user == null) {
            user = new Usuario(id);
            USUARIO.put(id, user);
        }
        return user;
    }

    private Usuario(String id) {
        this.id = id;
    }
   
    public String getId() {
        return id;
    }
    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
