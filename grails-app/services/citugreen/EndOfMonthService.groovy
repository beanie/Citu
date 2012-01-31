package citugreen

import java.text.*
import grails.converters.*

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
		now.set(tmpYear, 1, 1)
		log.info("**** Processing End Of Month on "+ new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz").format(now.getTime()))

		// Get all the premises
		/*
		 def premiseList = Premise.getAll()
		 log.info("list "+ premiseList.size())
		 premiseList.each {premise ->
		 log.info("premise" + premise)
		 }
		 */
		def formatter = new SimpleDateFormat("dd.MM.YYYY")
		def monthView = new MonthView()
		def premise = Premise.findByFlatNo("106")
		def elecReadings = ElecReading.findAllByPremiseAndFileDateBetween(premise, (now.getTime() + 6), now.getTime(), [sort:"fileDate", order:"desc"])

		log.info("Week 1 : "+ now.getTime() +" - "+ (now.getTime() + 6))
		def eWeek1 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
		monthView.week1 = formatter.format(now.getTime()) +" - "+ formatter.format(now.getTime() + 6)

		log.info("Week 2 : "+ (now.getTime() + 7) +" - "+ (now.getTime() + 13))
		def eWeek2 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
		monthView.week2 = formatter.format(now.getTime() + 7) +" - "+ formatter.format(now.getTime() + 13)

		log.info("Week 3 : "+ (now.getTime() + 14) +" - "+ (now.getTime() + 20))
		def eWeek3 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
		monthView.week3 = formatter.format(now.getTime() + 14) +" - "+ formatter.format(now.getTime() + 20)

		if (now.getActualMaximum(Calendar.DAY_OF_MONTH) <= 28) {
			log.info("Week 4 : "+ (now.getTime() + 21) +" - "+ (now.getTime() + (now.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)))
			def eWeek4 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
			monthView.week4 = formatter.format(now.getTime() + 21) +" - "+ formatter.format(now.getTime() + (now.getActualMaximum(Calendar.DAY_OF_MONTH) - 1))
		} else {
			log.info("Week 4 : "+ (now.getTime() + 21) +" - "+ (now.getTime() + 27))
			def eWeek4 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
			monthView.week4 = formatter.format(now.getTime() + 21) +" - "+ formatter.format(now.getTime() + 27)

		}
		if (now.getActualMaximum(Calendar.DAY_OF_MONTH) > 28) {
			log.info("Week 5 : "+ (now.getTime() + 28) +" - "+ (now.getTime() + (now.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)))
			def eWeek5 = new WeekHolder(utilityType:"elec", utilityUse:BillUtils.calcTotal(elecReadings.elecReading), utilityCost:BillUtils.calcTotal(elecReadings.elecReading))
			monthView.week5 = formatter.format(now.getTime() + 28) +" - "+ formatter.format(now.getTime() + (now.getActualMaximum(Calendar.DAY_OF_MONTH) - 1))
		}

		if (!eWeek1) {
			monthView.elecReadings.add(eWeek1)
		}

		log.info(monthView as JSON)
	}

	def serviceMethod() {

	}
}
