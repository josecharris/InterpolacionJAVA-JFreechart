package logic;

import java.util.ArrayList;

public class ManejoRegistros {
	private ArrayList<Registro> hombres;
	private ArrayList<Registro> mujeres;
	
	public ManejoRegistros() {
		hombres = new ArrayList<Registro>();
		mujeres = new ArrayList<Registro>();
		insertRecordsMen();
		insertRecordsWomen();
	}
	
	public void insertRecordsMen() {
		hombres.add(new Registro("Hombre", 1950, 12696935));
		hombres.add(new Registro("Hombre", 1960, 17415320));
		hombres.add(new Registro("Hombre", 1970, 24065614));
		hombres.add(new Registro("Hombre", 1990, 39893969));
		hombres.add(new Registro("Hombre", 1995, 44900499));
		hombres.add(new Registro("Hombre", 2000, 47592253));
		hombres.add(new Registro("Hombre", 2005, 50249955));
		hombres.add(new Registro("Hombre", 2010, 54855231));
	}
	
	public void insertRecordsWomen() {
		mujeres.add(new Registro("Mujeres", 1950, 	13094082));
		mujeres.add(new Registro("Mujeres", 1960, 	17507809));
		mujeres.add(new Registro("Mujeres", 1970, 	24159624));
		mujeres.add(new Registro("Mujeres", 1990, 	41355676));
		mujeres.add(new Registro("Mujeres", 1995,   46257791));
		mujeres.add(new Registro("Mujeres", 2000, 	49891159));
		mujeres.add(new Registro("Mujeres", 2005, 	53013433));
		mujeres.add(new Registro("Mujeres", 2010, 	57481307));
	}
	
	public ArrayList<Registro> getMen(){
		return (ArrayList<Registro>) hombres.clone();
	}
	public ArrayList<Registro> getWomen(){
		return (ArrayList<Registro>) mujeres.clone();
	}
}
