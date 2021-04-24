import java. util. ArrayList;
public class Box implements Holder {

    // define an array and an object Box()
    private String[] stringArray = new String[1];
    public Box() {
    }

    @Override
    public void add (Object object) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i] == null) {
                stringArray[i] = object.toString();
                String[] tempArray = new String[stringArray.length+1];
                System.arraycopy(stringArray,0,tempArray,0,stringArray.length);
                stringArray = tempArray;
                break;
            }
        }
    }

    @Override
    public void remove (Object object) {
        int objectCount = 0;
        int j = 0;
            String[] tempArray = new String[stringArray.length - 1];
            for(int i = 0; i<stringArray.length; i++) {
                if (stringArray[i] == object.toString() && objectCount == 0) {
                    objectCount ++;
                } else {
                    tempArray[j] = stringArray[i];
                    j++;
                }
            }
            stringArray = tempArray;
    }

    @Override
    public boolean contains (Object object) {
        for(int i = 0; i < stringArray.length; i++) {
            if (stringArray[i] == obj.toString()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty () {
        int numberofElement = 0;
        for(int i = 0; i<stringArray.length; i++) {
            if (stringArray[i] == null) {
                numberofElement++;
            }
        }
        if (numberofElement == stringArray.length) {
            return true;
        }
        return false;
    }
}