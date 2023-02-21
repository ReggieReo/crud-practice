public class Student {
    
    private String name;
    private int idNumber;
    private String contactNumber;

    // a constructor without parameter
    public Student() {
    }
    // a constructor with parameter
    public Student(String name, int idNumber, String contactNumber) {
        this.setName(name);
        this.setIdNumber(idNumber);
        this.setContactNumber(contactNumber);
    }

    //getter of name
    public String getName() {
        return name;
    }

    //setter of name
    public void setName(String name) {
        this.name = name;
    }

    //getter of idNumber
    public int getIdNumber() {
        return idNumber;
    }

    // a setter of IdNumber must have 8 digit
    public void setIdNumber(int idNumber) {
        // convert idNumber to String
        String stringIdNumber = Integer.toString(idNumber);
        // count length of idNumber
        int checkInt = stringIdNumber.length();
        // check if it meets the condition
        if (checkInt == 8) {
            // Integer.toInt
            this.idNumber = idNumber;
        } else {
            System.out.println("idNumber must not start with 0 and have 8 digits");
        }

    }

    //getter of contractNumber
    public String getContactNumber() {
        return contactNumber;
    }

    //setter of contractNumber
    //if a land line must start with 0 and should start with 02 and 9 digits long
    //if a mobile number should startwith 0 and 10 digits long
    public void setContactNumber(String contactNumber) {
        // check if contactNumber is a landline or a mobile phone
        if (contactNumber.startsWith("0") && contactNumber.length() == 10) {
            this.contactNumber = contactNumber;
        } else if (contactNumber.startsWith("02") && contactNumber.length() == 9) {
            this.contactNumber = contactNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", idNumber=" + idNumber + ", contactNumber=" + contactNumber + "]";
    }
    
}
