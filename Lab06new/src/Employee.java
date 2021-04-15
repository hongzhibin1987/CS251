public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private final String birthDay;
    private final String departmentName;
    private final String employeeType;


    public Employee(String firstName, String lastName, String socialSecurityNumber, String birthDay, String departmentName, String employeeType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDay = birthDay;
        this.departmentName = departmentName;
        this.employeeType = employeeType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getBirthDay(){
        return birthDay;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getEmployeeType() {
        return employeeType;
    }
    @Override
    public String toString()
    {
        return String.format ("%s %s%nsocial security number: %s", getFirstName(), getLastName(), getSocialSecurityNumber(), getBirthDay(), getDepartmentName());
    }

    public abstract double earnings();

}
