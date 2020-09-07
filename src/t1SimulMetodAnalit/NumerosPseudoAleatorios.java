package t1SimulMetodAnalit;

import java.util.ArrayList;
import java.util.Random;

public class NumerosPseudoAleatorios {
	
	private Random gerador;
	
	public NumerosPseudoAleatorios() {
		this.gerador = new Random();
	}

	public double getNumero() {
		double paraRetornar = gerador.nextDouble();
		return paraRetornar;
	}

	public ArrayList<Double> getNumeros(int quantNumeros) {
		ArrayList<Double> paraRetornar = new ArrayList<Double>();
		
		for (int i = 0; i < quantNumeros; i++) {
			double numeroGerado = gerador.nextDouble();
			paraRetornar.add(numeroGerado);
		}
		return paraRetornar;
	}

}
