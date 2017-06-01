import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by forch on 01-Jun-17.
 */
public class BabyPackage implements Serializable {
    int operationType = 0;
    Baby baby = null;
    LinkedList<Baby> list = null;
    String string = null;

    public BabyPackage(int operationType, Baby baby, LinkedList<Baby> list, String string) {
        this.operationType = operationType;
        this.baby = baby;
        this.list = list;
        this.string = string;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }


    public LinkedList<Baby> getList() {
        return list;
    }

    public void setList(LinkedList<Baby> list) {
        this.list = list;
    }

}
