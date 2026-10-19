package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTO_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES = 0.2;
	
	public int calcularCostoBase ( Vuelo vuelo, Cliente cliente) {
		
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		
		int costoPorKm;
		if(cliente.getClass().getSimpleName().equals("ClienteCorporativo")) {
			
			costoPorKm = COSTO_POR_KM_CORPORATIVO;
			
		}
		else {
			
			costoPorKm = COSTO_POR_KM_NATURAL;
		}
		
		return costoPorKm * distancia;
	}
	
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
		if(cliente.getClass().getSimpleName().equals("ClienteCorporativo")) {
			
			ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
			
			if (corporativo.getTamanoEmpresa() == ClienteCorporativo.GRANDE) {
				return DESCUENTO_GRANDES;
			}
			
			else if (corporativo.getTamanoEmpresa() == ClienteCorporativo.MEDIANA) {
				return DESCUENTO_MEDIANAS;
			}
			
			else {
				return DESCUENTO_PEQ;
			}
		}
		else {
			return 0;
		}
	}
	
	

}
