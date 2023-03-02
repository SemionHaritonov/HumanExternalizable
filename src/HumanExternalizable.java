import java.io.*;

public class HumanExternalizable implements Externalizable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String passportNumber;

    public HumanExternalizable() {

    }

    public HumanExternalizable(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        StringReader pasportStringReader = new StringReader(this.getPassportNumber());
        int currentInt = pasportStringReader.read();
        while (currentInt != -1) {
            int nextInt = pasportStringReader.read();
            if (nextInt != -1) {
                out.write(((currentInt - '0' + nextInt - '0') % 10 + '0'));
            } else {
                out.write(currentInt);
            }
            currentInt = nextInt;
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();

        StringBuilder stringBuilderPassport = new StringBuilder();
        int currentInt;
        while ((currentInt = in.read()) != -1) {
            stringBuilderPassport.append((char) currentInt);
        }

        char[] subResult = new char[stringBuilderPassport.length()];

        stringBuilderPassport.getChars(0, stringBuilderPassport.length(), subResult, 0);

        for (int counter = subResult.length - 2; counter >= 0; counter--) {
            if (subResult[counter] >= subResult[counter + 1]) {
                subResult[counter] = (char) ((int) subResult[counter]
                        - (int) subResult[counter + 1] + '0');
            } else {
                subResult[counter] = (char) ((int) subResult[counter]
                        - (int) subResult[counter + 1] + 10 + '0');
            }
        }

        passportNumber = String.copyValueOf(subResult);

    }


    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}