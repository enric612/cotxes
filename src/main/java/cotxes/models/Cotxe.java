package cotxes.models;


/**
 * 
 * @author ecliment
 * 
 *  Classe Cotxe
 *  
 */
public class Cotxe {
	
	// Atributs
	private String matricula;
	private String marca;
	private String model;
	private String color;
	private Integer num_portes;
	
		
	// Constructors
	
	/**
	 * Per defecte (Buid)
	 */
	public Cotxe() {
	}


	/**
	 * @param matricula
	 * @param marca
	 * @param model
	 * @param color
	 * @param num_portes
	 */
	public Cotxe(String matricula, String marca, String model, String color, Integer num_portes) {
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.color = color;
		this.num_portes = num_portes;
	}


	
	// Getters i Setters
	
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}


	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}


	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}


	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}


	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}


	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}


	/**
	 * @return the num_portes
	 */
	public Integer getNum_portes() {
		return num_portes;
	}


	/**
	 * @param num_portes the num_portes to set
	 */
	public void setNum_portes(Integer num_portes) {
		this.num_portes = num_portes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cotxe other = (Cotxe) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	

}
