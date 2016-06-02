import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Read {

	private static int LIMIT_COUNT = 2;
	private static String FILE_NAME = "./text";

	public static void main(String[] args) {
		try {
			LIMIT_COUNT=Integer.valueOf(args[1]);
			FILE_NAME=args[0];
			
			FileReader reader = new FileReader(FILE_NAME);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			Petrinet petrinet = new Petrinet("my_petrinet");
			String[] strings;
			HashMap<String, Place> placemap = new HashMap<>();
			HashMap<String, Transition> transmap = new HashMap<>();
			int count_fire = 0;
			while ((str = br.readLine()) != null) {
				strings = str.split(" ");
				if (strings[0].equalsIgnoreCase("place")) {

					Place tem_p = petrinet.place(strings[1], Integer.valueOf(strings[2]));
					placemap.put(strings[1], tem_p);

				} else if (strings[0].equalsIgnoreCase("transition")) {

					Transition tem_t = petrinet.transition(strings[1]);
					transmap.put(strings[1], tem_t);

				} else if (strings[0].equalsIgnoreCase("edge")) {

					if ((placemap.get(strings[1])) == null) {

						petrinet.edge(strings[0], transmap.get(strings[1]), placemap.get(strings[2]));
					} else {
						petrinet.edge(strings[0], placemap.get(strings[1]), transmap.get(strings[2]));
					}
				}
			}

			System.out.println("Original condition is:" + petrinet);

			int tmp = -1;
			while (tmp != count_fire) {
				tmp = count_fire;// test if any fire happens in following code
				for (Map.Entry<String, Transition> entry : transmap.entrySet()) {
					if (count_fire < LIMIT_COUNT) {
						if (entry.getValue().canFire()) {
							entry.getValue().fire();
							System.out.println("No. " + ++count_fire + " fire" + petrinet);
						}
					} else {
						System.out.println("We reach the cycle limit.");
						break;
					}
				}
			}

			if (count_fire != LIMIT_COUNT)
				System.out.println("No more fire will happen.");

			br.close();
			reader.close();
		} catch (

		IOException e)

		{
			e.printStackTrace();
		}

	}
}
