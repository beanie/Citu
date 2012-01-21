package citugreen

import java.util.ArrayList;

class ReadingsHolder {
	
	static hasMany=[elecReadings:ElecReading,waterReadings:WaterReading,heatReadings:HeatReading]
	
	static mapping = {
		elecReadings sort:'fileDate'
		heatReadings sort:'fileDate'
		waterReadings sort:'dateCreated'
	}
	
	static transients = ['totalColdWater','totalHotWater','totalGreyWater','totalHeatUsage','totalHeatCost', 'totalElecUsage']
	
	Float getTotalColdWater() {
		BillUtils.calcTotal(this.waterReadings.coldWater)
	}
	
	Float getTotalHotWater() {
		BillUtils.calcTotal(this.waterReadings.hotWater)
	}
	
	Float getTotalGreyWater() {
		BillUtils.calcTotal(this.waterReadings.greyWater)
	}
	
	Float getTotalHeatUsage() {
		BillUtils.calcTotal(this.heatReadings.heatReading)
	}
	
	Float getTotalHeatCost() {
		BillUtils.calcTotal(this.heatReadings.heatCost)
	}
	
	Float getTotalElecUsage() {
		BillUtils.calcTotal(this.elecReadings.elecReading)
	}

    static constraints = {
    }
}
