import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class LambdaTimBuchalka {

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

//        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));

//        employees.forEach((item)-> {
//            System.out.println(item.getName());
//            System.out.println(item.getAge());
//        });

        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee -> employee.getAge() <=30);
        printEmployeesByAge(employees, "Employees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.test(10));
        System.out.println(greaterThan15.test(20));
        System.out.println(greaterThan15.and(lessThan100).test(50));

    }

    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition){
        System.out.println(ageText);
        System.out.println("===============");
        for (Employee employee: employees){
            if (ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        };
    }


//        UpperConcat uc = (s1, s2) -> s1.toUpperCase() +  s2.toUpperCase();
//        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
//        System.out.println(sillyString);
//
//        AnotherClass anotherClass = new AnotherClass();
//        System.out.println(anotherClass.doSomething());
//    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

class Employee {

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class AnotherClass{
    public String doSomething(){

        int i=0;
//        return LambdaTimBuchalka.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }, "String1", "String2");

        UpperConcat upperConcat = (s1, s2) -> {
            System.out.println("The lambda expression`s class is " + getClass().getSimpleName());
            System.out.println("i in the lambda expression: " + i);
            return s1.toUpperCase() + s2.toUpperCase();
        };

        System.out.println("the AnotherClass class`s name is " + getClass().getSimpleName());
        return LambdaTimBuchalka.doStringStuff(upperConcat,"String1", "String2");
    }
}



