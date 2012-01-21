package citugreen

class BillUtils {
	
	static Float calcTotal(ArrayList costs) {
		def tmpFloat = 0
		for (i in costs) {
			if (i != null) {
				tmpFloat += i
			}
		}		
		return tmpFloat
	} 

}
