public class Admin {
   private String  adminUserName;
   private String adminPassword;

   public Admin(String usernName, String password) {
    this.adminPassword = password;
    this.adminUserName = usernName;
   }

public String getAdminUserName() {
    return adminUserName;
}

public void setAdminUserName(String adminUserName) {
    this.adminUserName = adminUserName;
}

public String getAdminPassword() {
    return adminPassword;
}

public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
}

@Override
public String toString() {
    return "Admin [adminUserName=" + adminUserName + ", adminPassword=" + adminPassword + "]";
}
}
