package FunctionLayer;

/**
 * The purpose of User is to...
 * @author kasper
 */
public class User {

   private String l_sEmail;
   private String l_sUsername;
   private String l_sPassword;
   private String l_sRole;

    public User(String l_sEmail, String l_sUsername, String l_sPassword, String l_sRole) {
        this.l_sEmail = l_sEmail;
        this.l_sUsername = l_sUsername;
        this.l_sPassword = l_sPassword;
        this.l_sRole = l_sRole;
    }

    public String getL_sEmail() {
        return l_sEmail;
    }

    public void setL_sEmail(String l_sEmail) {
        this.l_sEmail = l_sEmail;
    }

    public String getL_sUsername() {
        return l_sUsername;
    }

    public void setL_sUsername(String l_sUsername) {
        this.l_sUsername = l_sUsername;
    }

    public String getL_sPassword() {
        return l_sPassword;
    }

    public void setL_sPassword(String l_sPassword) {
        this.l_sPassword = l_sPassword;
    }

    public String getL_sRole() {
        return l_sRole;
    }

    public void setL_sRole(String l_sRole) {
        this.l_sRole = l_sRole;
    }
   

}
