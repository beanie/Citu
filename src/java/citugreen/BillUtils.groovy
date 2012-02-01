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

	static Float calcTotalGreyWaterCost(ArrayList costs){
		def tmp = calcTotal(costs)
		println (tmp)
		def tmpCost = citugreen.Tarrifs.get(1).greyWaterTarrif
		return (tmp*tmpCost)
	}

	static Float calcTotalHotWaterCost(ArrayList costs){
		def tmp = calcTotal(costs)
		println (tmp)
		def tmpCost = citugreen.Tarrifs.get(1).hotWaterTarrif
		return (tmp*tmpCost)
	}


	static Float calcTotalColdWaterCost(ArrayList costs){
		def tmp = calcTotal(costs)
		println (tmp)
		def tmpCost = citugreen.Tarrifs.get(1).coldWaterTarrif
		return (tmp*tmpCost)
	}


	static Float aveHotWaterByRoom(ArrayList costs, int rooms) {
		def tmpFloat = calcTotalColdWaterCost(costs)
		def tmpRoomAve = (tmpFloat/rooms)
		return tmpRoomAve
	}

}