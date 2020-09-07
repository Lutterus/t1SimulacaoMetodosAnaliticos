package t1SimulMetodAnalit;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {		
		//implementar geração de numeros
		NumerosPseudoAleatorios numerosPseudoAleatorios = new NumerosPseudoAleatorios();
		
		double numero = numerosPseudoAleatorios.getNumero();
		System.out.println("numero unico gerado: " + numero);
		
		ArrayList<Double> sequenciaDeNumeros = numerosPseudoAleatorios.getNumeros(1000);
		for (Double double1 : sequenciaDeNumeros) {
			System.out.println(double1);
		}
	}
}
