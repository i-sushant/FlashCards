class Employee {

    String name;

    public Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.address = "unknown";
    }

    public Employee() {
        name = "unknown";
        address = "unknown";
        salary = 0;
    }

    int salary;
    String address;
}