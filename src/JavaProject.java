import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = FileHandler.readFile();
        int choice = 0;


        while (choice != -1) {

            choice = mm.getChoice();

            switch (choice) {
                case 1:
                    mem = mm.addMembers(members);
                    System.out.println(mem);
                    fh.appendFile(mem);
                    break;
                case 2:
                    mm.removeMember(members);
                    fh.overwriteFile(members);
                    break;
                case 3:
                    mm.printMemberInfo(members);
                    break;
            }
        }
    }
}

/*

Если выбран вариант 2, метод removeMember() используется для удаления посетителя, а
 метод overwriteFile() обновляет CSV-файл.
 */