package Pack1;
import java.io.*;
import java.util.Arrays;
public class Main130521 {
    public static void main(String[] args) {
        Animal[] pets = {new Animal("Cat"), new Animal("Dog")};
        System.out.println(Arrays.toString(pets));
        byte [] petsAsBytes = getByteArrayFromAnyObejct(pets);
        //pets = null;
        pets = getAnimalsFromByteArray(petsAsBytes);
        System.out.println("Животные после \"воскрешения\" из байтового массива:");
        System.out.println(Arrays.toString(pets));
    }
    private static byte [] getByteArrayFromAnyObejct(Object transformObj) {
        ByteArrayOutputStream byteArrayRet = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayRet);
            oos.writeObject(transformObj);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayRet.toByteArray();
    }
    private static Animal[] getAnimalsFromByteArray(byte [] animalsAsBytes){
        Animal[] animals = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(animalsAsBytes));
            int numAnimals = ois.readInt();
            animals = new Animal[numAnimals];
            for (int i = 0; i < numAnimals; i++) {
                animals[i] = (Animal) ois.readObject();
            }
            ois.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return animals;
    }




}
