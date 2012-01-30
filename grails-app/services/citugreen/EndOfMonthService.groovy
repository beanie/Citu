package citugreen

import java.text.*;

class EndOfMonthService {

    static transactional = true
	
	def processMonthEnd() {
		
		Calendar now = Calendar.getInstance()
		def tmpYear = new SimpleDateFormat("YYYY").format(now.getTime()).toInteger()
		def tmpMonth = new SimpleDateFormat("M").format(now.getTime()).toInteger()
		if (tmpMonth == 1) {
			tmpMonth = 11
			tmpYear = (tmpYear - 1)
		} else {
			tmpMonth = (tmpMonth - 1)
		}
		now.set(tmpYear, tmpMonth, 1)
		log.info("its "+ new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz").format(now.getTime()))
		log.info("Days of Month : "+ now.getActualMaximum(Calendar.DAY_OF_MONTH))
	}

    def serviceMethod() {

    }
}
