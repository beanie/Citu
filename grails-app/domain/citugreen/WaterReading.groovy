package citugreen

class WaterReading extends Reading {
	
	Float greyWater
	Float hotWater
	Float coldWater
	
	Float greyWaterPrice = 0.001
	Float hotWaterPrice = 0.006
	Float coldWaterPrice = 0.003

    static constraints = {
    }
}
