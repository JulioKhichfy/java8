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
		palavras.add("casa do código");
		palavras.add("caelum");

		/**
		 * TODO Vale lembrar que podemos criar uma lista de objetos diretamente
		 * via Arrays.asList diferença é que não se pode mudar a quantidade de
		 * elementos de uma lista devolvida por esse método.
		 */
		// List<String> palavras = Arrays.asList("alura online", "casa do
		// código", "caelum");

		/**
		 * Como fazemos para ordenar essa lista? Podemos fazer isso sem usar
		 * nenhuma novidade: com o Collections.sort:
		 **/
		// Collections.sort(palavras);
		// System.out.println(palavras);

		/**
		 * para ordenar com esse novo critério de comparação (tamanho da
		 * string), podemos fazer:
		 **/
		// Comparator<String> comparador = new ComparadorDeStringPorTamanho();
		// Collections.sort(palavras, comparador);

		// System.out.println(palavras);
		/** Até aqui, nenhuma novidade!! **/

		/***************************************************************************************************/

		/** Vamos comecar com as novidades **/

		/**
		 * Em vez de usar o Collections.sort, podemos invocar essa operação na
		 * própria List! Veja:
		 **/
		/**
		 * Parece pouco, mas há muita coisa por trás. Em primeiro lugar, esse
		 * método sort não existia antes na interface List, nem em suas mães
		 * (Collection e Iterable).
		 **/
		// Comparator<String> comparador = new ComparadorDeStringPorTamanho();
		// palavras.sort(comparador);
		// System.out.println(palavras);

		/**
		 * Será então que simplesmente adicionaram um novo método? Se tivessem
		 * feito assim, haveria um grande problema: todas as classes que
		 * implementam List parariam de compilar, pois não teriam o método sort.
		 * E há muitas, muitas classes que implementam essas interfaces básicas
		 * do Java. Há implementações no Hibernate, no Google Collections e
		 * muito mais.
		 */

		/**
		 * -------------------------- Default Methods -------------------------
		 * Default Methods Para evitar essa quebra, o Java 8 optou por criar um
		 * novo recurso que possibilitasse adicionar métodos em interfaces e
		 * implementá-los ali mesmo! Se você abrir o código fonte da interface
		 * List, verá esse método: ---------------------------------------------
		 * default void sort(Comparator<? super E> c) { Collections.sort(this,
		 * c); }
		 */

		/**
		 * É um default method! Um método de interface que você não precisa
		 * implementar na sua classe se não quiser, pois você terá já essa
		 * implementação default. Repare que ele simplesmente delega a invocação
		 * para o bom e velho Collections.sort, mas veremos que outros métodos
		 * fazem muito mais.
		 * 
		 * Default methods foi uma forma que o Java encontrou para evoluir
		 * interfaces antigas, sem gerar incompatibilidades.--------------
		 * Repare que é diferente de uma classe abstrata: em uma interface você
		 * não pode ter atributos de instância, apenas esses métodos que delegam
		 * chamadas ou trabalham com os próprios métodos da interface.
		 */

		/**
		 * ------foreach, Consumer e interfaces no java.util.functions-----
		 * Vamos a um outro método default adicionado as coleções do Java: o
		 * forEach na interface Iterable. Como Iterable é mãe de Collection,
		 * temos acesso a esse método na nossa lista.
		 * 
		 */

		/**
		 * Se você abrir o JavaDoc ou utilizar o auto complete do Eclipse, verá
		 * que List.forEach recebe um Consumer, que é uma das muitas interfaces
		 * do novo pacote java.util.functions. Então vamos criar um consumidor
		 * de String:
		 */
		// Consumer<String> consumidor = new ConsumidorDeString();
		// palavras.sort(comparador);
		// palavras.forEach(consumidor);

		/**
		 * Interessante? Ainda não muito. Talvez fosse mais direto e simples
		 * escrever um for(String s : lista).
		 * 
		 * Default methods é o primeiro recurso que conhecemos. Sim, é bastante
		 * simples e parece não trazer grandes melhorias. O segredo é
		 * utilizá-los junto com lambdas, que você verá a seguir, e trará um
		 * impacto significativo para o seu código.
		 */

		/**
		 * Se você já está acostumado com Java há mais tempo, sabe que nesses
		 * casos não criamos uma classe isolada. Fazemos tudo ao mesmo tempo,
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
		 * Poderíamos até mesmo evitar a criação da variável consumidor,
		 * passando a classe anônima diretamente para o forEach:
		 */
		/**
		 * classes anônimas, que usamos com frequência para implementar
		 * listeners e callbacks que não terão reaproveitamento.
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
		 * Tendo essas dificuldade e verbosidade da sintaxe das classes anônimas
		 * em vista, o Java 8 traz uma nova forma de implementar essas
		 * interfaces ainda mais sucinta. É a sintaxe do lambda. Em vez de
		 * escrever a classe anônima, deixamos de escrever alguns itens que
		 * podem ser inferidos.
		 */
		/*
		 * palavras.forEach((String s) -> { System.out.println(s); });
		 */

		/**
		 * Como essa interface(Consumer) só tem um método default, não
		 * precisamos escrever o nome do método. Também não daremos new. Apenas
		 * declararemos os argumentos e o bloco a ser executado, separados por
		 * ->
		 */

		/**
		 * É uma forma bem mais sucinta de escrever! Essa sintaxe funciona para
		 * qualquer interface que tenha apenas um método abstrato, e é por esse
		 * motivo que nem precisamos falar que estamos implementando o método
		 * accept, já que não há outra possibilidade. Podemos ir além e remover
		 * a declaração do tipo do parâmtro, que o compilador também infere:
		 */
		/*
		 * palavras.forEach((s) -> { System.out.println(s); });
		 */

		/**
		 * Quando há apenas um parâmetro, nem mesmo os parenteses são
		 * necessários:
		 */

		/*
		 * palavras.forEach(s -> { System.out.println(s); });
		 */

		/**
		 * Dá pra melhorar? Sim. podemos remover as chaves de declaração do
		 * bloco, assim como o ponto e vírgula, pois só existe uma única
		 * instrução:
		 */
		// palavras.forEach(s -> System.out.println(s));

		/**
		 * Pronto. Em vez de usarmos classes anônimas, utilizamos o lambda para
		 * escrever códigos simples e sucintos nesses casos. Uma interface que
		 * possui apenas um método abstrato é agora conhecida como interface
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
		 * ---------------------Métodos default em Comparator ----------------
		 * Há vários métodos auxiliares no Java 8. Até em interfaces como o
		 * Comparator. E você pode ter um método default que é estático. Esse é
		 * o caso do Comparator.comparing, que é uma fábrica, uma factory, de
		 * Comparator. Passamos o lambda para dizer qual será o critério de
		 * comparação desse Comparator, repare:
		 * 
		 * 
		 */

		/**
		 * A interface Function vai nos ajudar a passar um objeto para o
		 * Comparator.comparing que diz qual será a informação que queremos usar
		 * como critério de comparação. Ela recebe dois tipos genéricos. No
		 * nosso caso, recebe uma String, que é o tipo que queremos comparar, e
		 * um Integer, que é o que queremos extrair dessa string para usar como
		 * critério. Poderia até mesmo criar uma classe anônima para implementar
		 * essa Function e seu método apply, sem utilizar nenhum lambda. O
		 * código ficaria grande e tedioso.
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
		 * Quisemos quebrar em três linhas para que você pudesse enxergar o que
		 * ocorre por trás exatamente. Sem dúvida o
		 * palavras.sort(Comparator.comparing(s -> s.length())) é mais fácil de
		 * ler. Dá para melhorar ainda mais? Sim!
		 */

		/** Method reference **/
		/**
		 * É muito comum escrevermos lambdas curtos, que simplesmente invocam um
		 * único método. É o exemplo de s -> s.length(). Dada uma String,
		 * invoque e retorne o método length. Por esse motivo, há uma forma de
		 * escrever esse tipo de lambda de uma forma ainda mais reduzida. Em vez
		 * de fazer:
		 **/
		palavras.sort(Comparator.comparing(s -> s.length()));

		/** Fazemos uma referência ao método (method reference): **/
		palavras.sort(Comparator.comparing(String::length));

		/**
		 * São equivalentes nesse caso! Sim, é estranho ver String::length e
		 * dizer que é equivalente a um lambda, pois não há nem a -> e nem os
		 * parênteses de invocação ao método. Por isso é chamado de method
		 * reference. Ela pode ficar ainda mais curta com o import static:
		 * import static java.util.Comparator.*;
		 * palavras.sort(comparing(String::length));.
		 */
		palavras.sort(comparing(String::length));

		/**
		 * Vamos ver melhor a semelhança entre um lambda e seu method reference
		 * equivalente. Veja as duas declarações a seguir:
		 */

		Function<String, Integer> funcao1 = s -> s.length();
		Function<String, Integer> funcao2 = String::length;

		/**
		 * 
		 * Elas ambas geram a mesma função: dada um String, invoca o método
		 * length e devolve este Integer. As duas serão avaliadas/resolvidas
		 * (evaluated) para Functions equivalentes.
		 * 
		 * Quer um outro exemplo? Vejamos o nosso forEach, que recebe um
		 * Consumer:
		 */

		palavras.forEach(s -> System.out.println(s));

		/**
		 * Dada uma String, invoque o System.out.println passando-a como
		 * argumento. É possível usar method reference aqui também! Queremos
		 * invocar o println de System.out:
		 */

		palavras.forEach(System.out::println);

		/**
		 * Novamente pode parecer estranho. Não há os parênteses, não há a
		 * flechinha (->), nem os argumentos que o Consumer recebe. Fica tudo
		 * implícito. Dessa vez, o argumento recebido (isso é, cada palavra
		 * dentro da lista palavras), não será a variável onde o método será
		 * invocado. O Java 8 consegue perceber que tem um println que recebe
		 * objetos, e invocará esse método, passando a String da vez.
		 * 
		 * Quando usar lambda e quando usar method reference? Algumas vezes não
		 * é possível usar method references. Se você tiver, por exemplo, um
		 * lambda que dada uma String, pega os 5 primeiros caracteres, faríamos
		 * s -> s.substring(0, 5). Esse lambda não pode ser escrito como method
		 * reference! Pois não é uma simples invocação de métodos onde os
		 * parâmetros são os mesmos que os do lambda.
		 * 
		 */

	}
}
