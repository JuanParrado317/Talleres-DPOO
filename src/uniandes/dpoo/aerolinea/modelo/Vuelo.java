package uniandes.dpoo.aerolinea.modelo;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;


import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new HashMap<String, Tiquete>();
		
	}
	
	public Ruta getRuta() {
		return this.ruta;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	public Avion getAvion() {
		return this.avion;
	}
	
	public Collection<Tiquete> getTiquete(){
		return this.tiquetes.values();
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException{
		if (this.tiquetes.size() + cantidad > this.avion.getCapacidad()) {
			throw new VueloSobrevendidoException(this);
		}
		int valorTotal = 0;
		for (int i = 0; i< cantidad; i++) {
			int tarifa = calculadora.calcularTarifa(this, cliente);
			Tiquete nuevoTiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
			this.tiquetes.put(nuevoTiquete.getCodigo(), nuevoTiquete);
			valorTotal += tarifa;
		}
		return valorTotal;
	}
	
	@Override
	public boolean equals(Object obj) {

	    if (this == obj) {
	        return true;
	    }

	    if (obj == null || !this.getClass().equals(obj.getClass())) {
	        return false;
	    }

	    Vuelo otroVuelo = (Vuelo) obj;

	    return this.ruta.getCodigoRuta().equals(otroVuelo.getRuta().getCodigoRuta())
	            && this.fecha.equals(otroVuelo.getFecha());
	}
}

