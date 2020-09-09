package t1SimulMetodAnalit;

public class SimulacaoFilaSimples {

	private FilaSimples filaSimples;
	private Escalonador escalonador;

	public SimulacaoFilaSimples(FilaSimples filaSimples) {
		// recebe a fila simples (modelo de fila com os valores)
		this.filaSimples = filaSimples;
		// instancia o escalonador de eventos
		escalonador = new Escalonador(filaSimples);
	}

	public void exec() {
		for (int i = 0; i < filaSimples.getMedia(); i++) {
			// insere o primeiro cliente na fila
			preparaExecucao();

			while (filaSimples.getAleatorios() > 0) {
				// pega o proximo evento do escalonador
				Evento evento = escalonador.getProximoEvento();

				// atualiza o tempo da execucao
				contabilizaTempoGlobal(evento.getTempoGlobal());

				// certificacao de que valor e valido
				if (evento != null) {
					// se for uma chegada
					if (evento.getTipo().contentEquals("CHEGADA")) {
						lidarComChegadas(evento);

						// se for uma saida
					} else {
						lidarComSaidas(evento);
					}
				}
			}
			// printa o resultado da execucao
			filaSimples.print();
			escalonador = new Escalonador(filaSimples);
		}
		// finalizou a execucao, guarda o resultado
		filaSimples.resultadoFinal();

	}

	private void lidarComSaidas(Evento evento) {
		// fila--
		filaSimples.contabilizaTempo(evento.getTempoGlobal(), evento.getTipo());
		// se fila>=1 agenda saida
		if (filaSimples.existeAlguemParaSerAtendido()) {
			escalonador.agendaSaida(filaSimples.getAtendimentoMIN(), filaSimples.getAtendimentoMAX(),
					filaSimples.getTempo());
		}
	}

	private void lidarComChegadas(Evento evento) {
		// verifica se possui espaco na fila
		if (filaSimples.possuiEspaco()) {
			// fila++
			filaSimples.contabilizaTempo(evento.getTempoGlobal(), evento.getTipo());
			// verifica se pode agendar uma saida
			if (filaSimples.podeAgendarSaida()) {
				escalonador.agendaSaida(filaSimples.getAtendimentoMIN(), filaSimples.getAtendimentoMAX(),
						filaSimples.getTempo());
			}
		} else {
			// se nao possui espaco, perda
			filaSimples.contabilizaPerda();
			//
			filaSimples.contabilizaTempo(evento.getTempoGlobal(), evento.getTipo());
		}
		// sempre agenda chegada
		escalonador.agendaChegada(filaSimples.getChegadaMIN(), filaSimples.getChegadaMAX(), filaSimples.getTempo());

	}

	private void contabilizaTempoGlobal(double tempo) {
		filaSimples.setTempo(tempo);
	}

	private void preparaExecucao() {
		// adiciona o primeiro cliente no escalonador
		escalonador.add(filaSimples.primeiroCliente());
	}

}
