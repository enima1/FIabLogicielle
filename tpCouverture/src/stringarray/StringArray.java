package stringarray;

import java.util.Arrays;

/**
 * This class is not documented
 * The specifications of the functions are not given
 * It seems related to arrays of Strings and elimination of
 * duplicate elements
 * @version unknown
 * @author web 
 *
 */
public class StringArray {

	private final String [] mlist;

	public StringArray(String[] list){
		int dupl=0;
		if (list.length>0) {
			String last=list[0];
			int index=0;
			while (++index<list.length){
				String comp=list[index];
				int diff = last.compareTo(comp);
				if (diff>0){
					Arrays.sort(list);
					last=list[index];

				} else if (diff<0) {
					last=comp;
				} else {
					dupl++;
				}
			}	
		}
		if (dupl >0){
			String [] uniques=new String[list.length -dupl];
			String last=uniques[0]=list[0];
			int index=0;
			int fill=1;
			while (++index<list.length) {
				if (!last.equals(list[index])) {
					last=list[index];
					uniques[fill++]=last;
				}
			}
			mlist=uniques;
		} else {
			mlist=list;		
		}
	}

	public String getString(int index) throws ArrayIndexOutOfBoundsException{
		return mlist[index];
	}

	public int IndexOf( String value){
		int base=0;
		int limit=mlist.length -1;
		while (base<= limit){
			int cur=(base+limit)>>1;
		int diff=value.compareTo(mlist[cur]);
		if (diff<0){
			limit=cur-1;
		} else if (diff>0) {
			base=cur+1;
		} else {
			return cur;
		}
		}
		return -1;
	}
	public int size(){
		return mlist.length;
	}

}