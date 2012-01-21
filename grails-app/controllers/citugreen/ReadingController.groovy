package citugreen

import groovy.util.XmlSlurper;
import java.text.*;

class ReadingController {
	
	ReadingService readingService
	
	def scaffold = true
	
	/*
	 *  Exposed methods for manual ingest of data
	 */
	def processElecReadings = {
		readingService.processElec()
		render("Electricity Readings Manually Parsed OK")
	}
	
	def processWaterReadings = {
		readingService.processWater()
		render("Water Readings Manually Parsed OK")
	}
	
	def processHeatReadings = {
		readingService.processHeat()
		render("Heat Readings Manually Parsed OK")
	}
	
}
