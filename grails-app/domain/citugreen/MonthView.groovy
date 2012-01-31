package citugreen

class MonthView {
	
	String week1
	String week2
	String week3
	String week4
	String week5
	
	ArrayList elecReadings
	ArrayList waterReadings
	ArrayList heatReadings
	
	static transients = ['week1','week2','week3','week4','week5', 'elecReadings', 'waterReadings', 'heatReadings']

    static constraints = {
		week4(nullable:true, blank:true)
		week5(nullable:true, blank:true)
    }
}
