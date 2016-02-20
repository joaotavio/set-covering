package ag;

import java.util.ArrayList;
import java.util.Random;


public class Util {
    private static Random random = new Random();
    
    public static int getRandomInt(int n){
        return random.nextInt(n);
    }
    
    public static int sizeIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        ArrayList<Integer> intersec = new ArrayList<>();
        for (Integer l1 : lista1) {
            if(lista2.contains(l1)){
                intersec.add(l1);
            }
        }
        return intersec.size();
    }
}
