/*
* Asive Madladla
* 217068332
* EmployeeGui
 */

package za.ac.cput.PRT362s_Project_2021.Employee;

public class Employee {
    private String id;
    private String department;
    private String name;
    private double salary;

    public Employee(String id, String department, String name, double salary) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.salary = salary;
    }

    public boolean saveToFile() {
        return false;
    }
}