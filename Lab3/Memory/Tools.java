import java.util.Random;
import java.util.Arrays;

public class Tools {
    public static void randomOrder(Object[] a) {
        int size = a.length;
        Object[] acopy = new Object[size];
        
        boolean insert;
               
        for (int i = 0; size > i; i++) {
            insert = false;
            while (insert == false) {
                int rand = randInt(0, size-1, new Random());
                if (acopy[rand] == null) {
                     acopy[rand] = a[i];
                     insert = true;
                }
            }
        }
        for (int i = 0; size > i; i++) {
            a[i] = acopy[i];
        }
    }
    
    private static int randInt(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        
        long range = (long)aEnd - (long)aStart + 1;
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);    
        return randomNumber;
  }
}
