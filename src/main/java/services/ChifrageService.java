package services;

public class ChifrageService {
	
	public String ChifreId(int incr,String chifrageLetres) {
		int nbr = incr;
		String res = chifrageLetres;
		for(int i=1000; i > 0; i/=10) {
			res += nbr/i;
			if(nbr/i != 0) {
				nbr = nbr%i;
			}
		}
		return res;
	}
	public int GetCurrentIncr(String str) {
		return Integer.parseInt(str.substring(4,8));
	}	
}