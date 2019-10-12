package logic;

public class Registro {
	private String descripcion;
	private double cantidad, anio;
	
	public Registro(String descripcion, double anio, double cantidad) {
		super();
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.anio = anio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getAnio() {
		return anio;
	}

	public void setAnio(double anio) {
		this.anio = anio;
	}
	
}
