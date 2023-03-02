import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HumanExternalizable human = new HumanExternalizable("human", "123456");

        File file = new File(".\\testHuman.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(human);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        HumanExternalizable humanExternalizable = (HumanExternalizable) objectInputStream.readObject();
        System.out.println(humanExternalizable.getName() + "   " + humanExternalizable.getPassportNumber());
    }
}