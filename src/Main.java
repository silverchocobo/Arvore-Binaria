public class Main {
    public static void main(String[] args) {

        BinarySearchTree arvore = new BinarySearchTree();

        arvore.inserir(8,8);
        arvore.inserir(4,4);
        arvore.inserir(18,18);
        arvore.inserir(2,2);
        arvore.inserir(6,6);
        arvore.inserir(12,12);
        arvore.inserir(20,20);
        arvore.inserir(10,10);
        arvore.inserir(16,16);
        arvore.inserir(14,14);

        System.out.println("Árvore em pré-ordem: ");
        arvore.preOrdem();

        System.out.println("Gerando arquivo dot...");
        arvore.gerarArqDot("arquivoDotPreOrdem.dot");

        arvore.excluir(18);
        System.out.println("\nExcluindo nodo 18...");
        arvore.gerarArqDot("arquivoDotExclui18.dot");

        System.out.println("\nInserindo nodo 25");
        arvore.inserir(25,25);
        arvore.gerarArqDot("arquivoDotInsere25.dot");

        arvore.inserir(1,1);
        System.out.println("\nInserindo nodo 1...");
        arvore.gerarArqDot("arquivoDotInsere1.dot");

        arvore.excluir(20);
        System.out.println("\nExcluindo nodo 20");
        arvore.gerarArqDot("arquivoDotExclui20.dot");



        System.out.println("\nÁrvore em ordem: ");
        arvore.emOrdem();

        System.out.println("\nÁrvore em pós-ordem: ");
        arvore.posOrdem();

        System.out.println("\nBuscando nodo 10... ");
        arvore.buscar(10);

        System.out.println("\nBuscando nodo 90...");
        arvore.buscar(90);



    }
}