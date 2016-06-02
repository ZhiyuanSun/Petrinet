import java.util.ArrayList;
import java.util.List;

public class Petrinet extends PetrinetObj {

	private static final String nl = "\n";
	List<Place> places = new ArrayList<Place>();
	List<Transition> transitions = new ArrayList<Transition>();
	List<Edge> arcs = new ArrayList<Edge>();

	public Petrinet(String name) {
		super(name);
	}

	public void add(PetrinetObj o) {
		if (o instanceof Edge) {
			arcs.add((Edge) o);
		} else if (o instanceof Place) {
			places.add((Place) o);
		} else if (o instanceof Transition) {
			transitions.add((Transition) o);
		}
	}

	public List<Transition> getTransitionsAbleToFire() {
		ArrayList<Transition> list = new ArrayList<Transition>();
		for (Transition t : transitions) {
			if (t.canFire()) {
				list.add(t);
			}
		}
		return list;
	}

	public Transition transition(String name) {
		Transition t = new Transition(name);
		transitions.add(t);
		return t;
	}

	public Place place(String name) {
		Place p = new Place(name);
		places.add(p);
		return p;
	}

	public Place place(String name, int initial) {
		Place p = new Place(name, initial);
		places.add(p);
		return p;
	}

	public Edge edge(String name, Place p, Transition t) {
		Edge edge = new Edge(name, p, t);
		arcs.add(edge);
		return edge;
	}

	public Edge edge(String name, Transition t, Place p) {
		Edge edge = new Edge(name, t, p);
		arcs.add(edge);
		return edge;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append("---Places---").append(nl);
		for (Place p : places) {
			sb.append(p).append(nl);
		}
		return sb.toString();
	}

	public List<Place> getPlaces() {
		return places;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public List<Edge> getArcs() {
		return arcs;
	}

}
