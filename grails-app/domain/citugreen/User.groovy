package citugreen

class User {
	
	String firstName
	String lastName
	
	String userName
	String contactEmail
	Long vmUserId
	
	Date dateCreated 
	
	String toString() {
		return userName
	}

    // I added some constraints for the hell of it
	static constraints = {
		vmUserId(blank:false, nullable: false)
		vmUserId(blank:false, nullable: false)		
		userName(blank:false, nullable: false, size:3..80)
		contactEmail(blank:false, nullable: false, email:true)
		vmUserId(blank:false, nullable: false)
    }
}
