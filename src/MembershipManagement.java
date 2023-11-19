import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    final private static Scanner reader = new Scanner(System.in);

    public static int getIntInput() {

        System.out.println("Введите цифру");

        //  while (!reader.hasNextLine()) {
        //   System.out.println("Введено не число, попробуйте еще раз");
        // reader.next();
        // }
        int num = reader.nextInt();
        reader.nextLine();
        return num;
    }

    private static void printClubOptions() {
        System.out.println("1) Club Mercury\n" +
                "2) Club Neptune\n" +
                "3) Club Jupiter\n" +
                "4) Multi Clubs");
    }

    public static int getChoice() {
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("================================");
        System.out.println("1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit):");

        int choice = getIntInput();
        return choice;
    }

    public static String addMembers(LinkedList<Member> m) {
        String name; // имя
        int club;    // для вычисления single/multi member
        String mem = null;
        double fees; // взносы
        int memberID; // id
        Member mbr = new Member('0', 0, null, 0);   // новый member для добавление в коллекцию
        Calculator<Integer> cal = null;
        char memberType = 0; // в условиях не указана эта переменная, но без нее не создать новый member


        if (!m.isEmpty()) {  // Проверка: если LinkedList не пуст, то id = id++ последнего элемента
            memberID = m.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }

        printClubOptions(); // Выводим список доступных клубов

        System.out.println("Введите идентификатор клуба: ");

        club = getIntInput();
        if (club == 1 || club == 2 || club == 3) {
            memberType = 'S';
        } else if (club == 4) {
            memberType = 'M';
        }

        System.out.println("Введите имя пользователя"); // Записываем имя
        name = reader.nextLine();

        Calculator<Double> singleMember = (d) -> {
            if (d == 1) {
                return 900.0;
            } else if (d == 2) {
                return 950.0;
            } else if (d == 3) {
                return 1000.0;
            } else if (d == 4) {
                return 1200.0;
            } else return -1;
        };

        fees = singleMember.calculateFees((double) club); // Взносы


        if (club == 1 || club == 2 || club == 3) {
            mbr.setMemberType(memberType);
            mbr.setMemberID(memberID);
            mbr.setName(name);
            mbr.setFees(fees);
            m.add(mbr); // добавляем нового member
            mem = mbr.getMemberType() + ";" + mbr.getMemberID() + ";"
                    + mbr.getName() + ";" + mbr.getFees();
            System.out.println("Посетитель успешно добавлен");

        } else if (club == 4) {
            MultiClubMember vip = new MultiClubMember(memberType, memberID, name, fees, 100);
            m.add(vip);
            mem = vip.getMemberType() + ";" + vip.getMemberID() + ";"
                    + vip.getName() + ";" + vip.getFees() + ";" + 100;
            System.out.println("Новый посетитель нескольких клубов успешно добавлен");
        }
        return mem;
    }

    public static void removeMember(LinkedList<Member> m) {

        int memberId;

        System.out.println("Введите ID пользователя");

        try {
            memberId = getIntInput();
            m.remove(memberId - 1);
        } catch (Exception e) {
            System.out.println("Пользователь не найден");
        }
        FileHandler.overwriteFile(m);
    }


    public static void printMemberInfo(LinkedList<Member> m) {

        int memberId;

        System.out.println("Введите ID пользователя");

        try {
            memberId = getIntInput();
            m.get(memberId).toString();
        } catch (Exception e) {
            System.out.println("Пользователь не найден");
        }

    }

}
