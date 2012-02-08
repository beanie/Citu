package citugreen

import java.text.*;

import grails.converters.*
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class PremiseController {

	EndOfMonthService endOfMonthService
	
    def scaffold = true
	
	/*
	def dateFoo = {
		endOfMonthService.processMonthEnd()
	}
	*/
	
	def processView = {
		def Premise premiseInstance = BillUtils.getPremise(params)
		
		def now = Calendar.getInstance()
		def srcYear
		def srcMonth
		def srcDay
		
		if (params.day && params.month && params.year) {
			log.info("day view")
			now.set(params.int("year"), params.int("month")-1, params.int("day"))
		} else if (params.month && params.year) {
			log.info("month view")
			now.set(params.int("year"), params.int("month")-1, 1)
		} else if (params.year) {
			log.info("year view")
			now.set(params.int("year"), 0, 1)
		} else if (params.daysBack ){
			log.info(params.daysBack +" days back view")
			def nowDate = new Date() - params.int("daysBack")
			now.setTime(nowDate)
		} else {
			render("Invalid date params sent")
		}
		
		// TODO add business logic to pull out view
		log.info("**** Processing End Of Month on "+ new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz").format(now.getTime()))
	}	
	
	/*
	* Property Summary call - 3 request params
	* 'flatNo' or 'macAddress' of property
	* 'elec', 'heat' or 'water' as a 'viewType'
	* 'daysBack' for time period wanted
	*/
	
	def readingSummary = {
		
		def errorCode = 0
		
		def Premise premiseInstance = BillUtils.getPremise(params)
		
		def now = new Date()
		def daysBack = now - params.int('daysBack')
		def tmpReadings
		def sum
		
		if (params.viewType.equals("elec")) {
			tmpReadings = ElecReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			sum = new ElecSummary(elecReadings:tmpReadings)
		} else if (params.viewType.equals("water")) {
			tmpReadings = WaterReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			sum = new WaterSummary(waterReadings:tmpReadings)
		} else if (params.viewType.equals("heat")) {
			tmpReadings = HeatReading.findAllByPremiseAndDateCreatedBetween(premiseInstance, daysBack, now, [sort:"dateCreated", order:"desc"])
			sum = new HeatSummary(heatReadings:tmpReadings)
		} else {
			errorCode = 1	
			render("invalid or missing viewType")
		}
		
		if (errorCode == 0) {
			
			log.info(params.viewType +" summary with "+ tmpReadings.size() +" records")
						
			render sum as JSON
		}
	}
	
	/*
	 * Property Summary web page
	 */
	def summary = {
		
		def Premise premiseInstance = BillUtils.getPremise(params)
		
		if (params.daysBack) {
			def now = new Date()
			def daysBack = now - params.int('daysBack')
			premiseInstance.elecReadings = ElecReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			premiseInstance.waterReadings = WaterReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			premiseInstance.heatReadings = HeatReading.findAllByPremiseAndDateCreatedBetween(premiseInstance, daysBack, now, [sort:"dateCreated", order:"desc"])
		}
			
		if (!premiseInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'premise.label', default: 'Premise'), params.id])}"
			redirect(action: "list")
		} else {
			log.info(premiseInstance)
			[premiseInstance: premiseInstance]
		}
	
	}
	
	def mobilesummary = {
		
		def Premise premiseInstance = BillUtils.getPremise(params)
		
		if (params.daysBack) {
			def now = new Date()
			def daysBack = now - params.int('daysBack')
			premiseInstance.elecReadings = ElecReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			premiseInstance.waterReadings = WaterReading.findAllByPremiseAndFileDateBetween(premiseInstance, daysBack, now, [sort:"fileDate", order:"desc"])
			premiseInstance.heatReadings = HeatReading.findAllByPremiseAndDateCreatedBetween(premiseInstance, daysBack, now, [sort:"dateCreated", order:"desc"])
		}
			
		if (!premiseInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'premise.label', default: 'Premise'), params.id])}"
			redirect(action: "list")
		}
		else {

			// render premiseInstance as JSON
			[premiseInstance: premiseInstance]
		}
	
	}
	
	
	
}
