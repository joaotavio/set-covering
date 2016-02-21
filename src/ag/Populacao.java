package ag;

import java.util.ArrayList;

public class Populacao {
    
    private int tam_populacao;
    private Cromossomo[] populacao;

    public Populacao(int tam_populacao) {
        this.tam_populacao = tam_populacao;
        populacao = new Cromossomo[tam_populacao];
    }

    public Cromossomo[] getPopulacao() {
        return populacao;
    }
    
    public void gerarPopulacaoInicial(ArrayList<Integer>[] listaLinha, ArrayList<Integer>[] listaColuna, Double[] listaCusto){
        for (int i = 0; i < tam_populacao; i++) {
            Cromossomo c = new Cromossomo();
            c.gerarIndividuo(listaLinha, listaColuna, listaCusto);
            c.eliminaRedundancia(listaColuna, listaCusto);
            populacao[i] = c;
        }
        /*int ig = 0;
        for(int ia = 0; ia<tam_populacao-1; ia++){
            //System.out.print(ia + " --> ");
            for(int ib = ia+1; ib < tam_populacao; ib++){
                //System.out.print(ib + "--");
                if(populacao[ia].getColunas().equals(populacao[ib].getColunas())){
                    //System.out.println(ia + " e " + ib + "--> igual!!");
                    ig++;
                }
            }
            //System.out.println();
        }
        
        System.out.println(ig);*/
    }
}
