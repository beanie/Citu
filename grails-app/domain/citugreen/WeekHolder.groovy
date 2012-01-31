package citugreen

class WeekHolder {
	
	String utilityType
	Float utilityUse
	Float utilityCost
	
	static transients = ['utilityType','utilityUse','utilityCost']

    static constraints = {
    }
}
