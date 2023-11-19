import java.io.*;
import java.util.LinkedList;

public class FileHandler {
    static String path = "src/dataBase/members.csv";

    public static LinkedList<Member> readFile() {

        LinkedList<Member> members = new LinkedList<>();

        try {
            File file = new File( path);
            FileReader fileReader = new FileReader( file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] p = line.split(";");
                line = bufferedReader.readLine();

                members.add(new Member((p[0].charAt(0)), Integer.parseInt(p[1]), p[2], Double.parseDouble(p[3])));
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Reading error!");
            throw new RuntimeException(e);
        }

        return members;
    }

    public static void appendFile(String mem) {

        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(mem + System.lineSeparator());
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void overwriteFile(LinkedList<Member> m) {

        File fileTemp = new File("src/dataBase/members.temp");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp))) {
            for (Member mem : m) {
                writer.write(mem.getMemberType() + ";" + mem.getMemberID() +
                        ";" + mem.getName() + ";" + mem.getFees());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File fileForDel = new File("src/dataBase/members.csv");

        if (fileForDel.exists() && fileForDel.canWrite()) { // Проверить, что файл существует и можно в него писать
            boolean deleted = fileForDel.delete();    // Возвращает true, если файл был успешно удалён
            if (!deleted) {
                System.err.println("Failed to delete file");
            }
        }

        boolean renamed = fileTemp.renameTo(fileForDel);
        if (!renamed) {
            System.err.println("Failed to rename file");
        }
    }

}

