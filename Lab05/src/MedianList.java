import java.util.ArrayList;

public class MedianList {
    public void main(String[] args) {

    }

    public boolean isMedian(int x, ArrayList<Integer> values){
        int bigX = 0;
        int smallX = 0;
        int valueSize = values.size();

        for (int i = 0; i< valueSize; i++){
            if (values.indexOf(i) > x) {
                bigX ++;
            }
            else if (values.indexOf(i)<x) {
                smallX ==;
            }
        }

        if(bigX > valueSize/2) {
            return false;
        }
        else if (smallX > valueSize/2) {
            return false;
        }
        return true;
    }

    public boolean testCorrectMedian(){

    }

    public boolean testCorrectString(){

    }
}
