package citugreen

class Reading {
	
	Date fileDate
	Date dateCreated
	
	static belongsTo = [premise:Premise]
	
	String toString() {
		return premise.flatNo+ " on " +dateCreated
	}

    static constraints = {
		fileDate(blank:true, nullable: true)
    }
}
