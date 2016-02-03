package ag;

import java.util.ArrayList;
import java.util.Random;

public class Populacao {
    
    private int tam_populacao;
    
    private Cromossomo[] populacao;
    
    private Random random;

    public Populacao(int tam_populacao) {
        this.tam_populacao = tam_populacao;
        populacao = new Cromossomo[tam_populacao];
        random = new Random();
    }
    
    public void gerarPopulacaoInicial(ArrayList<Integer>[] listaLinha, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        //listaLinha = ALFA
        //listaColuna = BETA
        
        ArrayList<Integer> linhasDescobertas = new ArrayList<>();
        for (int i = 0; i < listaLinha.length; i++) {
            linhasDescobertas.add(i);
        }
        
        int w[] = new int[listaLinha.length];
        Cromossomo cromossomo = new Cromossomo();
        
        while (!linhasDescobertas.isEmpty()){
            int random_pos = random.nextInt(linhasDescobertas.size());
            
            ArrayList<Integer> alfa = listaLinha[random_pos];
            double custo = listaCusto[alfa.get(0)];
            int intersecao_size = sizeIntersecao(linhasDescobertas, listaColuna[alfa.get(0)]);
            double menor = custo / intersecao_size;
            int menorColuna = 0;
            for (int i = 1; i < alfa.size(); i++) {
                custo = listaCusto[alfa.get(i)];
                intersecao_size = sizeIntersecao(linhasDescobertas, listaColuna[alfa.get(i)]);
                if ((custo / intersecao_size) < menor){
                    menor = custo / intersecao_size;
                    menorColuna = i;
                }
            }
            
            cromossomo.addColuna(menorColuna, listaCusto[menorColuna]);
            //wi
            System.out.println(linhasDescobertas);
            linhasDescobertas.removeAll(listaColuna[menorColuna]);
            
        }
        
    }
    
    private int sizeIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        ArrayList<Integer> intersec = new ArrayList<>();
        for (Integer l1 : lista1) {
            if(lista2.contains(l1)){
                intersec.add(l1);
            }
        }
        return intersec.size();
    }
    
}
