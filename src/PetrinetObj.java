
public class PetrinetObj {
	private String name;

	public PetrinetObj(String name) {
		        super();
	        this.name = name;
	    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
