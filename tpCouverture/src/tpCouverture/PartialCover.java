package tpCouverture;

/**
 * Class for showing something on coverage0
 * @author PR
 *
 */
public class PartialCover {

	public  int returnZeroOrOne(int x, int y) {
		int res = 0; 
		x = 1;
		y = x > 0 ? -1 : 0;
		for (int i = x; i < y; i++ ) {
			res = x == 0? 1 : 0;
		}
		return res;
	}

	public  void main(final String [] args) {
		int x = 10;
		int y = 10;
		System.out.println(returnZeroOrOne(x, y));
	}
}