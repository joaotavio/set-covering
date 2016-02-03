package ag;

import java.util.ArrayList;

public class Cromossomo {
    
    //Lista de indices das colunas que pertencem a solução
    private ArrayList<Integer> solucao;
    private double custoTotal;

    public Cromossomo() {
        solucao = new ArrayList<>();
        custoTotal = 0;
    }
    
    public void addColuna(int index, double custo){
        solucao.add(index);
        custoTotal += custo;
    }
}
