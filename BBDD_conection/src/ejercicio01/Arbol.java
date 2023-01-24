package ejercicio01;

public class Arbol {
	  private int id;
	    private String nombre_comun;
	    private String nombre_cientifico;
	    private String habitat;
	    private int altura;
	    private String origen;
	    public Arbol() {
	    	
	    }
		public Arbol(int id, String nombre_comun, String nombre_cientifico, String habitat, int altura, String origen) {
			super();
			this.id = id;
			this.nombre_comun = nombre_comun;
			this.nombre_cientifico = nombre_cientifico;
			this.habitat = habitat;
			this.altura = altura;
			this.origen = origen;
		}
		@Override
		public String toString() {
			return "Arbol\nid=" + id + "\nnombre_comun=" + nombre_comun + "\nnombre_cientifico=" + nombre_cientifico
					+ "\nhabitat=" + habitat + "\naltura=" + altura + "\norigen=" + origen;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre_comun() {
			return nombre_comun;
		}
		public void setNombre_comun(String nombre_comun) {
			this.nombre_comun = nombre_comun;
		}
		public String getNombre_cientifico() {
			return nombre_cientifico;
		}
		public void setNombre_cientifico(String nombre_cientifico) {
			this.nombre_cientifico = nombre_cientifico;
		}
		public String getHabitat() {
			return habitat;
		}
		public void setHabitat(String habitat) {
			this.habitat = habitat;
		}
		public int getAltura() {
			return altura;
		}
		public void setAltura(int altura) {
			this.altura = altura;
		}
		public String getOrigen() {
			return origen;
		}
		public void setOrigen(String origen) {
			this.origen = origen;
		}
	    
	    
}
