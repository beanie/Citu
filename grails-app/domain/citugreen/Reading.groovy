package citugreen

class Reading {
	
	/*
	Float elecReading
	Float heatReading
	Float heatCost
	Float greyWater
	Float hotWater
	Float coldWater
	*/
	
	Date fileDate
	Date dateCreated
	
	static belongsTo = [premise:Premise]
	
	String toString() {
		return premise.flatNo+ " on " +dateCreated
	}

    static constraints = {
		/*
		elecReading(blank:true, nullable: true)
		heatReading(blank:true, nullable: true)
		heatCost(blank:true, nullable: true)
		greyWater(blank:true, nullable: true)
		hotWater(blank:true, nullable: true)
		coldWater(blank:true, nullable: true)
		*/
		fileDate(blank:true, nullable: true)
    }
}
