import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StudentRecordManagement {
    // a LinkedList of student
    private LinkedList<Student> studentList;

    // a constructor
    public StudentRecordManagement() {
        // load student info from the file
        new StudentXMLRecorder();
        this.studentList = StudentXMLRecorder.loadStudentData();
    }

    // add student to a LinkedList
    public void add(Student record) throws ParserConfigurationException, TransformerException {
        studentList.add(record);
        StudentXMLRecorder.saveStudentData(studentList);
    }

    //check if a student is in the list
    public boolean find(int idNumber) {
        // create a boolean for checking
        boolean studentExist = false;
        // loop for each in studentList to check idnumber
        for (Student i: studentList) {
            if (i.getIdNumber() == idNumber) {
                studentExist = true;
                break;
            }
        }

        return studentExist;
    }

    //delete student in the list by index
    public void delete(int recldNumber) throws ParserConfigurationException, TransformerException {
        // delete the student
        this.studentList.remove(recldNumber);
        StudentXMLRecorder.saveStudentData(studentList);
    }

    //find if there is a student and return the student
    public Student findRecord(int idNumber) {
        // make a student variable
        Student wantedStudent = null;
        // loop to check if there is a student
        for (Student student: studentList) {
            if (student.getIdNumber() == idNumber) {
                wantedStudent = student;
                break;
            }
        }
        // return student
        return wantedStudent;
    }

    // don't imprement
    public void update(int id, Scanner input) {
    }

    @Override
    public String toString() {
        return "StudentRecordManagement [studentList=" + studentList + "]";
    }
    
}
