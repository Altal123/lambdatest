import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpression {

    public static void main(String[] args) {
        //http://regexlib.com/CheatSheet.aspx  - отличный сайт с инфой по регулярным выражениям
//        pattern_matcher();
        matches();
    }

    public static void matches() {
        //matches - сравнивает строку и строку/либо регулярное выражение
        /*
            \\d - одна цифра
            \\w - одна английская буква

            +  - 1 или более
            *  - 0 или более
            ? - 0 или 1 символов может быть перед данным знаком вопроса
            (x|y) - одна строка из множества строк, | это ИЛИ
            [a-zA-Z] = \\w - описываем все возможные англ. символы, т.е. множество
            [abc] = (a|b|c)  єто одно и то же
            [0-9] = \\d  - одно и то же
            [^abc] = [^a-c] - все символы кроме a, b, и c допускаются

            . - любой символ

            {2} - 2 символа до , например \\d{2} - должно быть ровно 2 цифры
            {2,} - 2 или более сомвола
            {2,4} - от 2х до 4х символов
        */
        String a = "-454";
        String b = "3444";
        System.out.println(a.matches("-?\\d*"));
        System.out.println(b.matches("(-|\\+)?\\d+"));

        String d = "222se111fe12232";
        System.out.println(d.matches("[a-zA-Z21]+\\d+"));

        String e = "sdf";
        System.out.println(e.matches("[^abc]*"));

        String f = "123";
        System.out.println(f.matches("\\d{2,}"));
    }

    public static void split(){
        //split - разбивает строку на массив строк
        String a = "Hello3434there45454hey";
        String[] words = a.split("\\d+"); //разделили строку по числам на массив исп. рег.выражение
        System.out.println(Arrays.toString(words));
    }

    public static void replace(){
        String a = "Hello there hey";
        String a1 = "Hello342e2there3534hey";
        String b = a.replace(" ", "."); //заменяем пробелы на точку, исп. строку
        String c = a1.replaceAll("\\d+", "-"); //заменяем числа на тире, исп. рег выражение
        String d = a1.replaceFirst("\\d+", "."); //заменяем первое появление чисел на точку, исп. рег выражение
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static void pattern_matcher(){
        String text = "Hello, guys! I sent you at ole@gmail.com\n" +
                ". And this is my address: Sanya@ukr.net. Let`s stay in touch..";

        //создаем паттерн по поиску определенного адреса єлектронной почты
        Pattern email = Pattern.compile("(\\w+)@(gmail|ukr)\\.(com|net)"); //так создаем новый паттерн! Не через new Pattern()!!!
        Matcher matcher = email.matcher(text); //ищем включения в нашем тексте используя созданный паттерн

        //ищем все совпадения по тексту
        while (matcher.find()){
            System.out.println(matcher.group()); //выводим на экран все найденные совпадения
            System.out.println(matcher.group(1)); //выводим на экран только часть совпадения, т.е. группы/подстроки, заключенной в круглые скобки
            System.out.println(matcher.group(2)); //группа 2: (gmail|ukr)
            System.out.println(matcher.group(3)); //группа 3: (com|net)
        }


    }
}


