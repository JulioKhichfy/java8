1 - Qual a interface que o novo m�todo forEach recebe como par�metro?
R: Consumer

2-Pesquise os novos m�todos default adicionados na interface List.
Voc� pode consultar a documenta��o em:
http://docs.oracle.com/javase/8/docs/api/java/util/List.html
Al�m do forEach na interface Iterable e sort na interface List, quais outros voc� achou interessante?
R:Uma adi��o tamb�m interessante foi o replaceAll.
Isso sem considerar os diversos outros que List herda de Collection.

3-O que voc� achou dessa introdu��o do java, os m�todos default?
Qual voc� acha que � a grande vantagem desses m�todos? O que eles possibilitam?
R:A grande vantagem � que agora uma interface pode evoluir sem quebrar compatibilidade.

4-Quantos m�todos uma interface funcional pode ter?
R:1 �nico m�todo abstrato. Al�m desse m�todo ela pode ter outros m�todos, contanto que sejam default.
Essa estrutura � fundamental, pois assim o compilador sabe exatamente que o corpo da express�o lambda que escrevemos � a implementa��o de seu �nico m�todo abstrato

/**
new Thread(() -> System.out.println("Executando um Runnable")).start();
**/