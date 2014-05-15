package mod.badores.util;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class JavaUtils {

	public static <T> T selectRandom(Random r, List<T> list) {
		return list.get(r.nextInt(list.size()));
	}

}
