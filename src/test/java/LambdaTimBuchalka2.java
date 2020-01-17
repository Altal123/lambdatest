import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class LambdaTimBuchalka2 {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red Riddik", 35);
        Employee charm = new Employee("Prince Dueyn", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charm);

        Function<Employee, String> getLastName = (Employee employee)-> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        Function<Employee, String> getFirstName = employee-> employee.getName().substring(0, employee.getName().indexOf(' '));

//        String lastName = getLastName.apply(employees.get(1));
//        System.out.println(lastName);

        Random random = new Random();
        for (Employee employee: employees){
            if (random.nextBoolean()){
                System.out.println(getAName(getFirstName, employee));
            }else {
                System.out.println(getAName(getLastName, employee));
            }
        }
    }

    private static String getAName (Function<Employee, String > getName, Employee employee){
        return getName.apply(employee);
    }
}
