import java.util.ArrayList;
import java.util.List;

public class Transition extends PetrinetObj {
	protected Transition(String name) {
		super(name);
	}

	private List<Edge> incoming = new ArrayList<Edge>();
	private List<Edge> outgoing = new ArrayList<Edge>();

	public boolean canFire() {
		boolean canFire = true;
		canFire = !this.isNotConnected();

		for (Edge edge : incoming) {
			canFire = canFire & edge.canFire();
		}

		for (Edge edge : outgoing) {
			canFire = canFire & edge.canFire();
		}
		return canFire;
	}

	public void fire() {
		for (Edge edge : incoming) {
			edge.fire();
		}

		for (Edge edge : outgoing) {
			edge.fire();
		}
	}

	public void addIncoming(Edge edge) {
		this.incoming.add(edge);
	}

	public void addOutgoing(Edge edge) {
		this.outgoing.add(edge);
	}

	public boolean isNotConnected() {
		return incoming.isEmpty() && outgoing.isEmpty();
	}

	@Override
	public String toString() {
		return super.toString() + (isNotConnected() ? " IS NOT CONNECTED" : "") + (canFire() ? " READY TO FIRE" : "");
	}

}
