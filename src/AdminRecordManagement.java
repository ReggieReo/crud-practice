import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class AdminRecordManagement {
    private LinkedList<Admin> adminList;
    Scanner input = new Scanner(System.in);

    public AdminRecordManagement() {
        // load admin info from the file
        new AdminXMLRecorder();
        this.adminList = AdminXMLRecorder.loadAdminData();
    }

    public boolean isAdmin(String name, String password) {
        for(Admin admin: adminList) {
            if (admin.getAdminUserName().equals(name) && admin.getAdminPassword().equals(password)) {
                return true;
            }
        }
        System.out.println("the username or password is wrong.");
        return false;
    }

    public void addAdmin(String name, String password) {
        Admin newAdmin = new Admin(name, password);
        adminList.add(newAdmin);
        try {
            AdminXMLRecorder.saveAdminData(adminList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAdmin(String name) {
        for(Admin admin: adminList) {
            if (admin.getAdminUserName().equals(name)) {
                adminList.remove(admin);
                try {
                    AdminXMLRecorder.saveAdminData(adminList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("the username or password is wrong.");
    }

    public void editAdmin() {
        for(int i = 0; i < adminList.size(); i++) {
            System.out.println("Admin " + i + ": " + adminList.get(i).getAdminUserName());
        }
        System.out.println("Please enter the number of the admin you want to edit: ");
        int adminNumber = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the new username: ");
        String newUserName = input.nextLine();
        System.out.println("Please enter the new password: ");
        String newPassword = input.nextLine();
        adminList.get(adminNumber).setAdminUserName(newUserName);
        adminList.get(adminNumber).setAdminPassword(newPassword);
        try {
            AdminXMLRecorder.saveAdminData(adminList);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public Admin findAdmin() {
        Admin tempAdmin = null;
        System.out.println("Please enter your username: ");
        String adminUserName = input.nextLine();
        System.out.println("Please enter your password: ");
        String adminPassword = input.nextLine();
        for(Admin admin: adminList) {
            if (admin.getAdminUserName().equals(adminUserName) && admin.getAdminPassword().equals(adminPassword)) {
                tempAdmin = admin;
                break;
            }
        }
        return tempAdmin;
    }

    @Override
    public String toString() {
        return "AdminRecordManagement [adminList=" + adminList + "]";
    }
}