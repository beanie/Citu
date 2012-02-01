package citugreen

class Premise {

	String flatNo
	String addressLine1
	String addressLine2
	String postCode
	int rooms
	int squareArea

	User user

	static hasMany=[elecReadings:ElecReading,waterReadings:WaterReading,heatReadings:HeatReading,setTobBoxes:SetTopBox]

	static mapping = {
		elecReadings sort:'fileDate'
		heatReadings sort:'fileDate'
		waterReadings sort:'dateCreated'
	}

	Float getTotalElecUsage() {
		BillUtils.calcTotal(this.elecReadings.elecReading)
	}

	Float getTotalHeatUsage() {
		BillUtils.calcTotal(this.heatReadings.heatReading)
	}


	Float getTotalColdWater() {
		BillUtils.calcTotal(this.waterReadings.coldWater)
	}

	Float getTotalHotWater() {
		BillUtils.calcTotal(this.waterReadings.hotWater)
	}

	Float getTotalGreyWater() {
		BillUtils.calcTotal(this.waterReadings.greyWater)
	}


	Float getTotalHeatCost() {
		BillUtils.calcTotalHeatCost(this.heatReadings.heatReading)

	}

	Float getTotalElecCost() {
		BillUtils.calcTotalElecCost(this.elecReadings.elecReading)
	}

	Float getTotalHotWaterCost() {
		BillUtils.calcTotalHotWaterCost(this.waterReadings.hotWater)
	}

	Float getTotalColdWaterCost() {
		BillUtils.calcTotalColdWaterCost(this.waterReadings.coldWater)
	}

	Float getTotalGreyWaterCost() {
		BillUtils.calcTotalGreyWaterCost(this.waterReadings.greyWater)
	}


	Float getAveHotWaterByRoom() {
		BillUtils.aveHotWaterByRoom(this.waterReadings.hotWater, this.rooms)
	}


	String toString() {
		return flatNo
	}

	static transients = ['totalHeatUsage','totalElecUsage','totalHotWater','totalColdWater','totalGreyWater', ,'totalHeatCost','totalElecCost','totalHotWaterCost',
		'totalColdWaterCost','totalGreyWaterCost','aveHotWaterByRoom']

    static constraints = {
		addressLine1(blank:false, nullable: false)
		addressLine2(blank:false, nullable: false)
    }
}
