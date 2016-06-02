
public class Place extends PetrinetObj{
public static final int UNLIMITED = -1;
    
    private int tokens = 0;

    protected Place(String name) {
        super(name);
    }

    protected Place(String name, int initial) {
        this(name);
        this.tokens = initial;
    }
   
    public boolean hasAtLeastTokens(int threshold) {
        return (tokens >= threshold);
    }
    
    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void addTokens(int weight) {
        this.tokens += weight;
    }

    public void removeTokens(int weight) {
        this.tokens -= weight;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               " Tokens=" + this.tokens;
    }
}
