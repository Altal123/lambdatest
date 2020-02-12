import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("bla bla bla")).start();
//        Различия лямбд и анонимного класса:
//        У метода анономного класса своя область видимости (scope) и там видно переменные которые были объявлены
//        в текущем классе и их можно использовать/переопределять, а в лямбдах нет своей области видимости, но они
//        могут использовать переменные определенные в текущем классе, но переопределять их не могут.

        int[] arr1 = new int[10];
        List<Integer> list1 = new ArrayList<>();
        fillArr(arr1);
        fillList(list1);

        //После использования терминальных операций другие терминальные или промежуточные операции к этому же потоку не могут быть применены, поток уже употреблен!!!
        //map method - каждому элементу массива а сопоставится элемент a * 2
        //stream - переводит наш массив в поток, возвращает его и уже работаем с ним
        //map() -отображение - берет каждый элемент из набора данных(множества) и сопоставляет ему новый элемент из нового множества
        //map - Промежуточная операция!
        arr1 = Arrays.stream(arr1).map(a -> a * 2).toArray(); // каждый элемент массива arr1 умножится на 2
        list1 = list1.stream().map(a -> a * 2).collect(Collectors.toList());

        System.out.println(Arrays.toString(arr1));
        System.out.println(list1);

        //filter method - если параметр boolean = true - то значение сохраняется в множестве, если false - убирается из него
        //filter - Промежуточная операция!
        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();

        fillArr(arr2);
        fillList(list2);

        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();  //все четные сохраняем в множестве
        list2 = list2.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());

        System.out.println(Arrays.toString(arr2));
        System.out.println(list2);

        //forEach - перебираем все элементы множества и работаем с каждым, замена цикла forEach
        //forEach - Терминальная операция! т.е. конечная, закрывающая поток
        Arrays.stream(arr2).forEach(System.out::println);
        list2.forEach(System.out::println);

        //reduce - берет набор данных и сжимает его в один елемент
        //reduce - Терминальная операция!
        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList<>();

        fillArr(arr3);
        fillList(list3);

        //сумма и произведение всех єлементов, acc - єто аккумулятор (аля sum, накапливает значения), b - текущее значние
        int sum1 = Arrays.stream(arr3).reduce((acc, b) -> acc + b).getAsInt();
        int sum2 = list3.stream().reduce((acc, b) -> acc * b).get();
        System.out.println(sum1);
        System.out.println(sum2);

        int[] arr4 = new int[10];
        fillArr(arr4);

        //берем все нечетные числа, умножаем их на 2 и помещаем в новый массив
        int[] newArray = Arrays.stream(arr4).filter(a -> a % 2 != 0).map(a -> a * 2).toArray();
        System.out.println(Arrays.toString(newArray));

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(5);
        System.out.println(set);
        set = set.stream().map(a -> a * 3).collect(Collectors.toSet());
        System.out.println(set);

        //Метод takeWhile() выбирает из потока элементы, пока они соответствуют условию. Если попадается элемент,
        // который не соответствует условию, то метод завершает свою работу. Выбранные элементы возвращаются в виде потока.
        Stream<Integer> numbers = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers.takeWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));

        //Метод dropWhile() выполняет обратную задачу - он пропускает элементы потока, которые соответствуют условию до тех пор,
        // пока не встретит элемент, который НЕ соответствует условию:
        Stream<Integer> numbers2 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers2.sorted().dropWhile(n -> n < 0)
                .forEach(System.out::println);

        //Статический метод concat() объединяет элементы двух потоков, возвращая объединенный поток
        Stream<String> people1 = Stream.of("Tom", "Bob", "Sam");
        Stream<String> people2 = Stream.of("Alice", "Kate", "Sam");
        Stream.concat(people1, people2).forEach(n -> System.out.println(n));
    }

    private static void fillList(List<Integer> list) {
        for (int i= 0; i < 10; i++)
            list.add(i + 1);
    }

    private static void fillArr(int[] arr) {
        for (int i= 0; i < 10; i++)
            arr[i] = i + 1;
    }

    public static void method(){
        int a = 122;
        Runner runner = new Runner();
        runner.run(new Executable() {
            @Override
            public int execute(int x, int y) {
                System.out.println("Hello2");
                int a = 2;
                return x+y;
            }
        });

        int b = 13;
        runner.run((y, o) -> {
            System.out.println("Hello3");
            System.out.println("Good Bye!");
            return y + 2 + b;
        });

        runner.run((z, r) -> z + r);
    }

}



interface Executable{
    int execute(int x, int y);
}

class Runner{
    public void run(Executable e){
        System.out.println(e.execute(10, 11));
    };
}

//class ExecutableImplementation implements Executable{
//    @Override
//    public void execute() {
//        System.out.println("Hello");
//    }
//}

//    List <WebElement> dropdownPELEManagerRoleValues;
//    List<String> list = new ArrayList<>();
//dropdownPELEManagerRoleValues.forEach(item -> list.add(item.getText()));
//
//        List <WebElement> dropdownPELEManagerRoleValues;
//        dropdownPELEManagerRoleValues.stream()
//        .filter(item -> item.getText().equals(role))
//        .forEach(item -> {
//        if(item.getAttribute("class").contains("ui-state-disabled"))
//        logger.debug("Неможливо обрати повторно роль [%s]");
//        else throw new AssertionError("Manager role doesn't contain atribute 'class' with value 'ui-state-disabled'");
//        });
//
//        List <WebElement> dropdownPELEManagerRoleValues;
//        dropdownPELEManagerRoleValues.stream()
//        .filter(item -> item.getText().equals(value))
//        .forEach(item -> item.click());

// List<String> TABLE_HEADERS = Arrays.asList(
//					"№","ID","ІПН","Документ","ПІБ","Дата народження","Статус","Оператор","Дата");