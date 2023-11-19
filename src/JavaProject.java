import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = FileHandler.readFile();
        int choice;


        choice = mm.getChoice();

        switch (choice) {
            case 1: {
                mem = mm.addMembers(members);
                System.out.println(mem);
                fh.appendFile(mem);
                break;
            }

            case 2: {
                mm.removeMember(members);
                break;
            }
        }


    }
}

/*
Класс JavaProject содержит единственный метод main().

В методе main() определяются пять переменных:

String mem;
MembershipManagement mm;
FileHandler fh;
LinkedList<Member> members;
int choice;

Объект mm используется для вызова метода getChoice() в классе MembershipManagement.
 Метод выводит список вариантов и возвращает вариант, выбранный пользователем, вызывающей стороне.

Доступны следующие варианты:
1) Add Member
2) Remove Member
3) Display Member Information

Также пользователь может ввести –1 для завершения программы.

Если выбран вариант 1, метод addMembers() класса MembershipManagement используется для
 добавления данных посетителя в LinkedList. Метод addMembers() предлагает пользователю
  ввести данные нового посетителя; эти данные используются для обновления переданной
  коллекции LinkedList. Кроме того, он возвращает строку с представлением добавленного
  посетителя. Эта строка присваивается локальной переменной mem.
  Затем метод appendFile()
в классе FileHandler используется для добавления посетителя в файл members.csv. Этот метод
 не возвращает никакого значения.

Если выбран вариант 2, метод removeMember() используется для удаления посетителя, а
 метод overwriteFile() обновляет CSV-файл.

Если выбран вариант 3, метод printMemberInfo() выводит информацию о посетителе.



 */