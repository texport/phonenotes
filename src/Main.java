import java.util.*;

public class Main {

    private static final HashMap<String, String> phoneNotes = new HashMap<>(){{
        put("Сергей", "87474524135");
        put("Павел", "87087315296");
    }};
    private static TreeMap<String, String> phoneNotesTree = new TreeMap<>();

    public static void main(String[] args)
    {
        String text = " ";

        while (!text.equalsIgnoreCase("EXIT"))
        {
            text = getText();

            if (text.equalsIgnoreCase("LIST"))
            {
                printPhoneNotes();
            }
            else if (text.equalsIgnoreCase("LISTABC"))
            {
                setABCPhoneNotes();
                printABCPhoneNotes();
            }
            else if (text.equalsIgnoreCase("ADD"))
            {
                System.out.println("Введите номер телефона или имя");
                text = getText();

                if (checkNumber(text))
                {
                    if (phoneNotes.containsValue(text))
                    {
                        System.out.println("Такой номер телефона уже зарегестрирован у контакта");
                        for (String key : findKeysByValue(phoneNotes, text))
                        {
                            System.out.println(text + " " + key);
                        }
                    }
                    else
                    {
                        System.out.println("Вы ввели новый номер телефона, мы его добавили в справочник");
                        String temp1 = text;
                        System.out.println("Теперь введите имя");
                        text = getText();
                        String temp2 = text;
                        addNumber(temp2, temp1);
                    }
                }
                else if (checkName(text))
                {
                    if (phoneNotes.containsKey(text))
                    {
                        System.out.println("Контакт с таким именем уже есть");
                        System.out.println(phoneNotes.get(text));
                    }
                    else
                    {
                        System.out.println("Вы новый контакт в справочник, мы сейчас его добавим");
                        String temp1 = text;
                        System.out.println("Теперь введите его номер");
                        text = getText();
                        String temp2 = text;
                        addNumber(temp1, temp2);
                    }
                }
            }
            else
            {
                System.out.println("ты ввел что то не то, пытайся");
            }
        }
    }

    // Печатаем все то что есть в phoneNotes HashMap
    private static void printPhoneNotes()
    {
        for (String key : phoneNotes.keySet())
        {
            System.out.println(key + " - " + phoneNotes.get(key));
        }
    }

    // Печатаем все то что есть в phoneNotes TreeMap(Алфавитный порядок)
    private static void printABCPhoneNotes()
    {
        for (String key : phoneNotesTree.keySet())
        {
            System.out.println(key + " - " + phoneNotesTree.get(key));
        }
    }

    // Сделать справочник в алфавитном порядке
    private static void setABCPhoneNotes()
    {
        phoneNotesTree.putAll(phoneNotes);
    }

    // Добавляем запись в телефонную книгу
    private static void setPhoneNotes(String name, String number)
    {
        phoneNotes.put(name, number);
    }

    // Считываем данные из консоли
    private static String getText()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean checkNumber(String number)
    {
        return number.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
    }

    private static boolean checkName(String name)
    {
        return name.matches("^[a-zA-Zа-яА-Я]+$");
    }

    private static void addNumber(String name, String number)
    {
        phoneNotes.put(name, number);
    }

    private static Set<String> findKeysByValue(Map<String, String> map, String value)
    {
        Set<String> result = new HashSet<>();
        if (map.containsValue(value))
        {
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                if (Objects.equals(entry.getValue(), value))
                {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }
}