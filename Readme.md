1 - Qual a interface que o novo método forEach recebe como parâmetro?
R: Consumer

2-Pesquise os novos métodos default adicionados na interface List.
Você pode consultar a documentação em:
http://docs.oracle.com/javase/8/docs/api/java/util/List.html
Além do forEach na interface Iterable e sort na interface List, quais outros você achou interessante?
R:Uma adição também interessante foi o replaceAll.
Isso sem considerar os diversos outros que List herda de Collection.

3-O que você achou dessa introdução do java, os métodos default?
Qual você acha que é a grande vantagem desses métodos? O que eles possibilitam?
R:A grande vantagem é que agora uma interface pode evoluir sem quebrar compatibilidade.

4-Quantos métodos uma interface funcional pode ter?
R:1 único método abstrato. Além desse método ela pode ter outros métodos, contanto que sejam default.
Essa estrutura é fundamental, pois assim o compilador sabe exatamente que o corpo da expressão lambda que escrevemos é a implementação de seu único método abstrato

/**
new Thread(() -> System.out.println("Executando um Runnable")).start();
**/