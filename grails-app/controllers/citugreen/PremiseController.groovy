package citugreen

import java.text.*;

import grails.converters.*
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class PremiseController {

	EndOfMonthService endOfMonthService
	
    def scaffold = true
	
	def dateFoo = {
		endOfMonthService.processMonthEnd()
	}
	
	/*
	* Property Summary call - 3 request params
	* 'flatNo' or 'macAddress' of property
	* 'elec', 'heat' or 'water' as a 'viewType'
	* 'daysBack' for time period wanted
	*/
	
	def readingSummary = {
		
		def errorCode = 0
		def premiseInstance
		
		if (params.flatNo) {
			premiseInstance = Premise.findByFlatNo(params.flatNo)
		} else if (params.macAddress) {
			def stbInstance = SetTopBox.findByMacAddress(params.macAddress)
			premiseInstance = stbInstance.premise
		} else {
			render("invalid premise identifier")
		}
		
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
			log.info(params.viewType +" summary with "+ tmpReadings.size() +" records "+ ConfigurationHolder.getConfig().getProperty('COLD_WATER_COST'))
						
			render sum as JSON
		}
	}
	
	/*
	 * Property Summary web page
	 */
	def summary = {
		
		def premiseInstance
		
		if (params.flatNo) {
			premiseInstance = Premise.findByFlatNo(params.flatNo)
		} else if (params.macAddress) {
			def stbInstance = SetTopBox.findByMacAddress(params.macAddress)
			premiseInstance = stbInstance.premise
		} else {
			render("invalid premise identifier")
		}
		
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
	
	def HTML5summary = {
		
		def premiseInstance
		
		if (params.flatNo) {
			premiseInstance = Premise.findByFlatNo(params.flatNo)
		} else if (params.macAddress) {
			def stbInstance = SetTopBox.findByMacAddress(params.macAddress)
			premiseInstance = stbInstance.premise
		} else {
			render("invalid premise identifier")
		}
		
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
