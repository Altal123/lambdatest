public class RegExpression {
    public static void main(String[] args) {

        //matches - сравнивает строку и строку/либо регулярное выражение
        /*
            \\d - одна любая цифра
            +  - 1 или более цифр
            *  - 0 или более цифр
        */
        String a = "12332s";
        System.out.println(a.matches("\\d*"));
    }
}
