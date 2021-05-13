package Pack1;
import java.io.*;
import java.util.Arrays;
public class Main130521 {
    public static void main(String[] args) {
        Animal[] pets = {new Animal("Cat"), new Animal("Dog")};
        System.out.println(Arrays.toString(pets));
        byte [] petsAsBytes = getByteArrayFromAnyObejct(pets);
        pets = null;
        pets = getAnimalsFromByteArray(petsAsBytes);
        System.out.println("Животные после \"воскрешения\" из байтового массива:");
        System.out.println(Arrays.toString(pets));
    }
    private static byte [] getByteArrayFromAnyObejct(Object transformObj) {
        ByteArrayOutputStream byteArrayRet = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(byteArrayRet));
            oos.writeObject(transformObj);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayRet.toByteArray();
    }
    private static Animal[] getAnimalsFromByteArray(byte [] animalsAsBytes){
        Animal[] animals = new Animal[0];
        try {
            ObjectInputStream objInpStream = new ObjectInputStream(
                    new BufferedInputStream(
                            new ByteArrayInputStream(animalsAsBytes)));
            animals = (Animal[])objInpStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return animals;
    }




}
