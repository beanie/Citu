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
	
	static Float calcTotalHeatCost(ArrayList costs){
		def tmp = calcTotal(costs)
		println (tmp)
		def tmpCost = citugreen.Tarrifs.get(1).heatTarrif
		return (tmp*tmpCost)
	}
	
	static Float calcTotalElecCost(ArrayList costs){
		def tmp = calcTotal(costs)
		println (tmp)
		def tmpCost = citugreen.Tarrifs.get(1).elecTarrif
		return (tmp*tmpCost)
	}
		
	
	static Float aveTotalbyRoom(ArrayList costs, int rooms) {
		def tmpFloat = 0
		for (i in costs) {
			if (i != null) {
				tmpFloat += i
			}
		}
		def tmpAve = tmpFloat/rooms
		return tmpAve
	}

}