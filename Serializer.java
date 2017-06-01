/**
 * Created by forch on 31-May-17.
 */
import java.io.*;

public class Serializer {
    public static byte[] serialize(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            byte[] ans = baos.toByteArray();
            baos.close();
            return ans;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return new byte[1024];
    }

    public static BabyPackage deserialize(byte[] bytes) {
        BabyPackage object;
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            object = (BabyPackage) ois.readObject();
            return object;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return new BabyPackage(0, null, null, null);
    }

}
