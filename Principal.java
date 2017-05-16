import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import static java.util.Comparator.*;

public class Principal {

	public static void main(String[] args) {

		List<String> palavras = new ArrayList<String>();
		palavras.add("alura online");
		palavras.add("casa do c�digo");
		palavras.add("caelum");

		/**
		 * TODO Vale lembrar que podemos criar uma lista de objetos diretamente
		 * via Arrays.asList diferen�a � que n�o se pode mudar a quantidade de
		 * elementos de uma lista devolvida por esse m�todo.
		 */
		// List<String> palavras = Arrays.asList("alura online", "casa do
		// c�digo", "caelum");

		/**
		 * Como fazemos para ordenar essa lista? Podemos fazer isso sem usar
		 * nenhuma novidade: com o Collections.sort:
		 **/
		// Collections.sort(palavras);
		// System.out.println(palavras);

		/**
		 * para ordenar com esse novo crit�rio de compara��o (tamanho da
		 * string), podemos fazer:
		 **/
		// Comparator<String> comparador = new ComparadorDeStringPorTamanho();
		// Collections.sort(palavras, comparador);

		// System.out.println(palavras);
		/** At� aqui, nenhuma novidade!! **/

		/***************************************************************************************************/

		/** Vamos comecar com as novidades **/

		/**
		 * Em vez de usar o Collections.sort, podemos invocar essa opera��o na
		 * pr�pria List! Veja:
		 **/
		/**
		 * Parece pouco, mas h� muita coisa por tr�s. Em primeiro lugar, esse
		 * m�todo sort n�o existia antes na interface List, nem em suas m�es
		 * (Collection e Iterable).
		 **/
		// Comparator<String> comparador = new ComparadorDeStringPorTamanho();
		// palavras.sort(comparador);
		// System.out.println(palavras);

		/**
		 * Ser� ent�o que simplesmente adicionaram um novo m�todo? Se tivessem
		 * feito assim, haveria um grande problema: todas as classes que
		 * implementam List parariam de compilar, pois n�o teriam o m�todo sort.
		 * E h� muitas, muitas classes que implementam essas interfaces b�sicas
		 * do Java. H� implementa��es no Hibernate, no Google Collections e
		 * muito mais.
		 */

		/**
		 * -------------------------- Default Methods -------------------------
		 * Default Methods Para evitar essa quebra, o Java 8 optou por criar um
		 * novo recurso que possibilitasse adicionar m�todos em interfaces e
		 * implement�-los ali mesmo! Se voc� abrir o c�digo fonte da interface
		 * List, ver� esse m�todo: ---------------------------------------------
		 * default void sort(Comparator<? super E> c) { Collections.sort(this,
		 * c); }
		 */

		/**
		 * � um default method! Um m�todo de interface que voc� n�o precisa
		 * implementar na sua classe se n�o quiser, pois voc� ter� j� essa
		 * implementa��o default. Repare que ele simplesmente delega a invoca��o
		 * para o bom e velho Collections.sort, mas veremos que outros m�todos
		 * fazem muito mais.
		 * 
		 * Default methods foi uma forma que o Java encontrou para evoluir
		 * interfaces antigas, sem gerar incompatibilidades.--------------
		 * Repare que � diferente de uma classe abstrata: em uma interface voc�
		 * n�o pode ter atributos de inst�ncia, apenas esses m�todos que delegam
		 * chamadas ou trabalham com os pr�prios m�todos da interface.
		 */

		/**
		 * ------foreach, Consumer e interfaces no java.util.functions-----
		 * Vamos a um outro m�todo default adicionado as cole��es do Java: o
		 * forEach na interface Iterable. Como Iterable � m�e de Collection,
		 * temos acesso a esse m�todo na nossa lista.
		 * 
		 */

		/**
		 * Se voc� abrir o JavaDoc ou utilizar o auto complete do Eclipse, ver�
		 * que List.forEach recebe um Consumer, que � uma das muitas interfaces
		 * do novo pacote java.util.functions. Ent�o vamos criar um consumidor
		 * de String:
		 */
		// Consumer<String> consumidor = new ConsumidorDeString();
		// palavras.sort(comparador);
		// palavras.forEach(consumidor);

		/**
		 * Interessante? Ainda n�o muito. Talvez fosse mais direto e simples
		 * escrever um for(String s : lista).
		 * 
		 * Default methods � o primeiro recurso que conhecemos. Sim, � bastante
		 * simples e parece n�o trazer grandes melhorias. O segredo �
		 * utiliz�-los junto com lambdas, que voc� ver� a seguir, e trar� um
		 * impacto significativo para o seu c�digo.
		 */

		/**
		 * Se voc� j� est� acostumado com Java h� mais tempo, sabe que nesses
		 * casos n�o criamos uma classe isolada. Fazemos tudo ao mesmo tempo,
		 * criando a classe e instanciando-a:
		 */

		/*
		 * Comparator<String> comparador = new Comparator<String>() {
		 * 
		 * @Override public int compare(String s1, String s2) { if (s1.length()
		 * < s2.length()) return -1; if (s1.length() > s2.length()) return 1;
		 * return 0; }
		 * 
		 * };
		 * 
		 * Consumer<String> consumidor = new Consumer<String>() { public void
		 * accept(String s) { System.out.println(s); } };
		 * 
		 * palavras.sort(comparador); palavras.forEach(consumidor);
		 */
		/**
		 * Poder�amos at� mesmo evitar a cria��o da vari�vel consumidor,
		 * passando a classe an�nima diretamente para o forEach:
		 */
		/**
		 * classes an�nimas, que usamos com frequ�ncia para implementar
		 * listeners e callbacks que n�o ter�o reaproveitamento.
		 * 
		 */
		/*
		 * palavras.sort(new Comparator<String>() {
		 * 
		 * @Override public int compare(String s1, String s2) { if (s1.length()
		 * < s2.length()) return -1; if (s1.length() > s2.length()) return 1;
		 * return 0; }
		 * 
		 * });
		 */

		/*
		 * palavras.forEach(new Consumer<String>() {
		 * 
		 * @Override public void accept(String t) { System.out.println(t);
		 * 
		 * } });
		 */

		/**
		 * -----------------Lambda para simplificar----------------------------
		 * Tendo essas dificuldade e verbosidade da sintaxe das classes an�nimas
		 * em vista, o Java 8 traz uma nova forma de implementar essas
		 * interfaces ainda mais sucinta. � a sintaxe do lambda. Em vez de
		 * escrever a classe an�nima, deixamos de escrever alguns itens que
		 * podem ser inferidos.
		 */
		/*
		 * palavras.forEach((String s) -> { System.out.println(s); });
		 */

		/**
		 * Como essa interface(Consumer) s� tem um m�todo default, n�o
		 * precisamos escrever o nome do m�todo. Tamb�m n�o daremos new. Apenas
		 * declararemos os argumentos e o bloco a ser executado, separados por
		 * ->
		 */

		/**
		 * � uma forma bem mais sucinta de escrever! Essa sintaxe funciona para
		 * qualquer interface que tenha apenas um m�todo abstrato, e � por esse
		 * motivo que nem precisamos falar que estamos implementando o m�todo
		 * accept, j� que n�o h� outra possibilidade. Podemos ir al�m e remover
		 * a declara��o do tipo do par�mtro, que o compilador tamb�m infere:
		 */
		/*
		 * palavras.forEach((s) -> { System.out.println(s); });
		 */

		/**
		 * Quando h� apenas um par�metro, nem mesmo os parenteses s�o
		 * necess�rios:
		 */

		/*
		 * palavras.forEach(s -> { System.out.println(s); });
		 */

		/**
		 * D� pra melhorar? Sim. podemos remover as chaves de declara��o do
		 * bloco, assim como o ponto e v�rgula, pois s� existe uma �nica
		 * instru��o:
		 */
		// palavras.forEach(s -> System.out.println(s));

		/**
		 * Pronto. Em vez de usarmos classes an�nimas, utilizamos o lambda para
		 * escrever c�digos simples e sucintos nesses casos. Uma interface que
		 * possui apenas um m�todo abstrato � agora conhecida como interface
		 * funcional e pode ser utilizada dessa forma.
		 */

		/** Vamos olhar a evolucao do Comparator **/

		/** 1 **/
		palavras.sort((s1, s2) -> {
			if (s1.length() < s2.length())
				return -1;
			if (s1.length() > s2.length())
				return 1;
			return 0;
		});

		/** 2 **/
		palavras.sort((s1, s2) -> {
			return Integer.compare(s1.length(), s2.length());
		});

		/** 3 **/
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		/** 4 **/
		palavras.sort((s1, s2) -> s1.length() - s2.length());

		palavras.forEach(s -> System.out.println(s));

		/**
		 * ---------------------M�todos default em Comparator ----------------
		 * H� v�rios m�todos auxiliares no Java 8. At� em interfaces como o
		 * Comparator. E voc� pode ter um m�todo default que � est�tico. Esse �
		 * o caso do Comparator.comparing, que � uma f�brica, uma factory, de
		 * Comparator. Passamos o lambda para dizer qual ser� o crit�rio de
		 * compara��o desse Comparator, repare:
		 * 
		 * 
		 */

		/**
		 * A interface Function vai nos ajudar a passar um objeto para o
		 * Comparator.comparing que diz qual ser� a informa��o que queremos usar
		 * como crit�rio de compara��o. Ela recebe dois tipos gen�ricos. No
		 * nosso caso, recebe uma String, que � o tipo que queremos comparar, e
		 * um Integer, que � o que queremos extrair dessa string para usar como
		 * crit�rio. Poderia at� mesmo criar uma classe an�nima para implementar
		 * essa Function e seu m�todo apply, sem utilizar nenhum lambda. O
		 * c�digo ficaria grande e tedioso.
		 */

		/** 1 **/
		Function<String, Integer> funcao = s -> s.length();
		Comparator<String> comparador = Comparator.comparing(funcao);
		palavras.sort(comparador);

		/** 2 **/
		Comparator<String> comparador2 = Comparator.comparing(s -> s.length());
		palavras.sort(comparador2);

		/** 3 **/
		palavras.sort(Comparator.comparing(s -> s.length()));

		/**
		 * Quisemos quebrar em tr�s linhas para que voc� pudesse enxergar o que
		 * ocorre por tr�s exatamente. Sem d�vida o
		 * palavras.sort(Comparator.comparing(s -> s.length())) � mais f�cil de
		 * ler. D� para melhorar ainda mais? Sim!
		 */

		/** Method reference **/
		/**
		 * � muito comum escrevermos lambdas curtos, que simplesmente invocam um
		 * �nico m�todo. � o exemplo de s -> s.length(). Dada uma String,
		 * invoque e retorne o m�todo length. Por esse motivo, h� uma forma de
		 * escrever esse tipo de lambda de uma forma ainda mais reduzida. Em vez
		 * de fazer:
		 **/
		palavras.sort(Comparator.comparing(s -> s.length()));

		/** Fazemos uma refer�ncia ao m�todo (method reference): **/
		palavras.sort(Comparator.comparing(String::length));

		/**
		 * S�o equivalentes nesse caso! Sim, � estranho ver String::length e
		 * dizer que � equivalente a um lambda, pois n�o h� nem a -> e nem os
		 * par�nteses de invoca��o ao m�todo. Por isso � chamado de method
		 * reference. Ela pode ficar ainda mais curta com o import static:
		 * import static java.util.Comparator.*;
		 * palavras.sort(comparing(String::length));.
		 */
		palavras.sort(comparing(String::length));

		/**
		 * Vamos ver melhor a semelhan�a entre um lambda e seu method reference
		 * equivalente. Veja as duas declara��es a seguir:
		 */

		Function<String, Integer> funcao1 = s -> s.length();
		Function<String, Integer> funcao2 = String::length;

		/**
		 * 
		 * Elas ambas geram a mesma fun��o: dada um String, invoca o m�todo
		 * length e devolve este Integer. As duas ser�o avaliadas/resolvidas
		 * (evaluated) para Functions equivalentes.
		 * 
		 * Quer um outro exemplo? Vejamos o nosso forEach, que recebe um
		 * Consumer:
		 */

		palavras.forEach(s -> System.out.println(s));

		/**
		 * Dada uma String, invoque o System.out.println passando-a como
		 * argumento. � poss�vel usar method reference aqui tamb�m! Queremos
		 * invocar o println de System.out:
		 */

		palavras.forEach(System.out::println);

		/**
		 * Novamente pode parecer estranho. N�o h� os par�nteses, n�o h� a
		 * flechinha (->), nem os argumentos que o Consumer recebe. Fica tudo
		 * impl�cito. Dessa vez, o argumento recebido (isso �, cada palavra
		 * dentro da lista palavras), n�o ser� a vari�vel onde o m�todo ser�
		 * invocado. O Java 8 consegue perceber que tem um println que recebe
		 * objetos, e invocar� esse m�todo, passando a String da vez.
		 * 
		 * Quando usar lambda e quando usar method reference? Algumas vezes n�o
		 * � poss�vel usar method references. Se voc� tiver, por exemplo, um
		 * lambda que dada uma String, pega os 5 primeiros caracteres, far�amos
		 * s -> s.substring(0, 5). Esse lambda n�o pode ser escrito como method
		 * reference! Pois n�o � uma simples invoca��o de m�todos onde os
		 * par�metros s�o os mesmos que os do lambda.
		 * 
		 */

	}
}
