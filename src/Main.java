import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		// costruzione di uno stream da un array
		Integer[] integerArray = new Integer[] {2, 5, 8, 15, 21, 12, 5, 7, 15};
		Stream<Integer> stream = Arrays.stream(integerArray);
		
		// costruzione da una lista di elementi
		stream = Stream.of(2, 5, 8, 15, 21, 12, 5, 7, 15);
		
		// costruzione da una collezione
		List<Integer> integerList = Arrays.asList(integerArray);
		stream = integerList.stream();
		
		// creazione di uno stream vuoto, utile per non tornare null
		Stream<String> stream2 = Stream.empty();
		
		// costruzione diretta di uno stream
		Stream<Integer> streamBuild = Stream.<Integer> builder().add(2).add(5).add(8).add(15).add(21)
				.add(12).add(5).add(7).add(15).build();
		
		// usando il metodo generate(Supplier<? extends T> s), utilizzare limit per definire un limite
		Stream<Integer> streamRandom = Stream.generate(new Random()::nextInt).limit(10);
		
		// con il metodo iterate(T seed, UnaryOperator<T> f)
		Stream<Integer> streamIterate = Stream.iterate(2, n -> n * 2).limit(100);
		
		// IntStream, LongStream e DoubleStream sono classi per creare stream di questi dati primitivi
		IntStream intStream = IntStream.range(1, 10);
		LongStream longStream = LongStream.range(1, 10);
		DoubleStream doubleStream = intStream.asDoubleStream();
		
		// operazioni che si possono eseguire sugli stream
		List<Integer> integerList1 = integerList.stream()
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		
		System.out.println(integerList1);
		
		// operazioni di matching
		boolean b = integerList.stream()
				.anyMatch(n -> n > 10);
		
		System.out.println(b);
		
		// operazioni di filtraggio
		List<Integer> integerList2 = integerList.stream()
				.filter(Objects::nonNull)
				.filter(n -> n >= 0)
				.filter(n -> n % 2 == 0)
				.collect(Collectors.toList());
		
		System.out.println(integerList2);
		
		// trasformare un elemento in un altro mediante una funzione
		Stream<Integer> mesiIntStream = Stream.iterate(0, n -> n + 1).limit(12);
		Stream<String> mesiStringStream = mesiIntStream.map(numb -> new DateFormatSymbols()
				.getMonths()[numb]);
		
		List<String> mesiList = mesiStringStream.collect(Collectors.toList());
		System.out.println(mesiList);
		
		// creare uno stream di una proprietà di un determinato elemento
		List<Marca> marche = new ArrayList<>();
		marche.add(new Marca("Fiat",Arrays.asList("Panda","Punto","500")));
		marche.add(new Marca("Renault",Arrays.asList("Clio","Twingo")));
		marche.add(new Marca("Ford",Arrays.asList("Focus","C-Max")));

		Stream<String> stream1 = marche.stream().flatMap(marca -> marca.getModelli().stream());
		stream1.forEach(System.out::println);
		
		// creare una mappa
		Map<Integer, List<String>> map = marche.stream().flatMap(marca -> marca.getModelli().stream())
				.collect(Collectors.groupingBy(String::length));
		System.out.println(map);
	}
}
