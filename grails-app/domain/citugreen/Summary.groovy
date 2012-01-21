package citugreen

class Summary {
	
	ElecSummary elecSummary
	HeatSummary heatSummary
	WaterSummary waterSummary
	
	static transients = ['elecSummary','heatSummary','waterSummary']
	
	static constraints = {
    }
}

class ElecSummary {
	
	ArrayList elecReadings
	
	static transients = ['totalElecUsage']
	
	Float getTotalElecUsage() {
		BillUtils.calcTotal(this.elecReadings.elecReading)
	}
}

class HeatSummary {
	
	ArrayList heatReadings
	
	static transients = ['totalHeatUsage','totalHeatCost']
	
	Float getTotalHeatUsage() {
		BillUtils.calcTotal(this.heatReadings.heatReading)
	}
	
	Float getTotalHeatCost() {
		BillUtils.calcTotal(this.heatReadings.heatCost)
	}
}

class WaterSummary {
	
	ArrayList waterReadings

	static transients = ['totalColdWater','totalHotWater','totalGreyWater']
	
	Float getTotalColdWater() {
		BillUtils.calcTotal(this.waterReadings.coldWater)
	}
	
	Float getTotalHotWater() {
		BillUtils.calcTotal(this.waterReadings.hotWater)
	}
	
	Float getTotalGreyWater() {
		BillUtils.calcTotal(this.waterReadings.greyWater)
	}
	
}
