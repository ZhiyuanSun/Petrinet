
public class Edge extends PetrinetObj {
	Place place;
	Transition transition;
	Direction direction;
	int weight = 1;

	enum Direction {
		PLACE_TO_TRANSITION {
			@Override
			public boolean canFire(Place p, int weight) {
				return p.hasAtLeastTokens(weight);
			}

			@Override
			public void fire(Place p, int weight) {
				p.removeTokens(weight);
			}
		},
		TRANSITION_TO_PLACE {
			@Override
			public boolean canFire(Place p, int weight) {
				return true;
			}

			@Override
			public void fire(Place p, int weight) {
				p.addTokens(weight);
			}

		};

		public abstract boolean canFire(Place p, int weight);
		public abstract void fire(Place p, int weight);
	}

	private Edge(String name, Direction d, Place p, Transition t) {
		super(name);
		this.direction = d;
		this.place = p;
		this.transition = t;
	}

	protected Edge(String name, Place p, Transition t) {
		this(name, Direction.PLACE_TO_TRANSITION, p, t);
		t.addIncoming(this);
	}

	protected Edge(String name, Transition t, Place p) {
		this(name, Direction.TRANSITION_TO_PLACE, p, t);
		t.addOutgoing(this);
	}

	public boolean canFire() {
		return direction.canFire(place, weight);
	}

	public void fire() {
		this.direction.fire(place, this.weight);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}
}
