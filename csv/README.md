Ciao,

premessa: mi rendo conto che per alcuni questi mie tentativi son banali, ad ogni modo, quando ho un po' di tempo mi piace sperimentare e vorrei condividere questo con qualcuno che mi possa dare qualche consiglio.

Sto cercando di capire se vale la pena provare ad usare stream e lambda per convertire un file CSV in cui ci sono 2 campi in una Map<String, List<String>>

Questo il file in input:

tv, led
,oled
,led
,3d
,4k
frigorifero,doppia porta
,da incasso
,no frost
...


Ora non per tutte le righe il primo campo è valorizzato. Quando manca prende il valore della prima riga in cui è valorizzato (scorrendo all'indietro). Spero di essermi spiegato.



Ora supponiamo che di aver già letto il file CSV e avere una collection List<String[2]> valorizzata con i campi del file sorgente.

Questo è il mio tentativo:

	AtomicReference<String> atomicKey = new AtomicReference<String>();
	atomicKey.set("");

	Map<String, List<String>> lines = csvLines
	.stream()
	.sequential()
	.collect(Collectors.groupingBy(v -> {

			if (atomicKey.get().isEmpty() && v[0].isEmpty())
				throw new RuntimeException("key cannot be empty");

			if (atomicKey.get().isEmpty())
				atomicKey.set(v[0]);
			else if (v[0].isEmpty())
				v[0] = atomicKey.get();
			else
				atomicKey.set(v[0]);
			return v[0];

		},
		Collectors.mapping(v -> v[1], Collectors.toList())
	));

Ho pensato di sovrascrivere la key corrente nel metodo collect, che ho letto essere stateful, cosa ne pensate?

L'uso del metodo sequential() mi mette al sicuro da eventuali problemi di concorrenza?
