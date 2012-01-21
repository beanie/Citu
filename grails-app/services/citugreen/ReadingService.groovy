package citugreen

import groovyx.net.http.HTTPBuilder;
import static groovyx.net.http.Method.GET;
import static groovyx.net.http.ContentType.TEXT;
import static groovyx.net.http.ContentType.XML;
import groovy.util.XmlSlurper;
import java.text.*;

class ReadingService {
	
	static transactional = true
	
	def processElec() {
		def fileNames = [
			"http://20.20.3.20/xmlelectricity3.xml",
			"http://20.20.3.23/xmlelectricity6.xml",
			"http://20.20.3.225/xmlelectricity8.xml",
			"http://20.20.3.28/xmlelectricity11.xml"
		]
		fileNames.each {file ->
			processXML(file, "elec")
		}
		log.info("Electricity Readings Parsed OK")
	}
	
	def processWater() {
		def fileNames = [
			"http://20.20.3.20/xmlwater3.xml",
			"http://20.20.3.21/xmlwater4.xml",
			"http://20.20.3.22/xmlwater5.xml",
			"http://20.20.3.23/xmlwater6.xml",
			"http://20.20.3.24/xmlwater7.xml",
			"http://20.20.3.25/xmlwater8.xml",
			"http://20.20.3.26/xmlwater9.xml",
			"http://20.20.3.27/xmlwater10.xml",
			"http://20.20.3.28/xmlwater11.xml",
			"http://20.20.3.29/xmlwater12.xml",
			"http://20.20.3.30/xmlwater13.xml"
		]
		fileNames.each {file -> 
			processXML(file, "water")
		}
		log.info("Water Readings Parsed OK")
	}
	
	def processHeat() {
		def fileNames = ["c:\\files\\10-2011B.CSV"]
		fileNames.each {file -> processHeatXML(file) }
		log.info("Heat Readings Parsed OK")
	}
	
	def processXML(fileRef, type) {
		
		def http = new HTTPBuilder(fileRef)
		
		try {
			
			http.request( GET, TEXT ) {
				
				headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
				
				request.getParams().setParameter("http.connection.timeout", new Integer(5000));
				request.getParams().setParameter("http.socket.timeout", new Integer(5000));
				
				// response handler for a success response code:
				response.success = { resp, reader ->
					log.info("** Got "+ type +" XML "+ resp.statusLine +" **")
					if (type.equals("elec")) {
						processElecXML(reader)
					} else if (type.equals("water")) {
						processWaterXML(reader)
					}
				}
				
				// handler for any failure status code:
				response.failure = { resp -> log.error("Unexpected error: ${resp.status} : ${resp.statusLine.reasonPhrase}")}
			}
		} catch (Exception e) {
			log.error(e)
		}
	}
	
	/*
	 * Process the electricity readings XML
	 */
	def processElecXML(fileRef) {
		
		def readingsXML = new XmlSlurper().parse(fileRef)
		def readings = readingsXML.group
		def fileInfo = readingsXML.project
		
		log.info("*** Parsing Electricity info. for: "+ fileInfo.name +" Created: "+ fileInfo.creationdate +" ***")
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd")
		def tmpFileDate = df.parse(fileInfo.creationdate.toString())
		
		readings.element.each {reading ->
			
			def premise = Premise.findByFlatNo(reading.name.toString())
			
			if (premise) {
				log.info("** Premise found: "+ premise +" - ELEC: "+ reading.item.valuelong.toString())
				def tmpReading = new ElecReading(elecReading:reading.item.valuelong.toString(), fileDate:tmpFileDate, premise:premise)
				tmpReading.save()
			} else {
				log.warn("** Premise not found: "+ reading.name)
			}
		}
		log.info("*** Parsing Electricity info. for: "+ fileInfo.name +" complete ***")
	}
	
	/*
	 * Process the heating readings
	 */
	def processHeatXML(fileRef) {
		
		log.info("*** Parsing Heat info. for: "+ fileRef +" ***")
		
		new File(fileRef).splitEachLine(",") {fields ->
			def premise = Premise.findByFlatNo(fields[0])
			if (premise){
				log.info("** Premise found: "+ premise +" - HEAT: "+ fields[1].toString()+" COST: "+ fields[4].toString())
				def tmpReading = new HeatReading(heatReading:fields[1].toString(), heatCost:fields[4].toString(), premise:premise)
				tmpReading.save()
			} else {
				log.warn("** Premise not found: "+ fields[0])
			}
		}
		log.info("*** Parsing Heat info. for: "+ fileRef +" complete ***")
	}
	
	/*
	 * Process the water readings XML
	 */
	def processWaterXML(fileRef) {
		
		def readingsXML = new XmlSlurper().parse(fileRef)
		def readings = readingsXML.group
		def fileInfo = readingsXML.project
		
		log.info("*** Parsing Water info. for: "+ fileInfo.name +" Created: "+ fileInfo.creationdate +" ***")
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd")
		def tmpFileDate = df.parse(fileInfo.creationdate.toString())
		
		readings.element.each {reading ->
			
			def premise = Premise.findByFlatNo(reading.name.toString())
			
			if (premise) {
				log.info("** Premise found: "+ premise +" - COLD WATER: "+ reading.item[0].valuelong.toString()+" HOT WATER: "+ reading.item[1].valuelong.toString()+" GREY WATER: "+ reading.item[2].valuelong.toString())
				def tmpReading = new WaterReading(fileDate:tmpFileDate, coldWater:reading.item[0].valuelong.toString(), hotWater:reading.item[1].valuelong.toString(), greyWater:reading.item[2].valuelong.toString(), premise:premise)
				tmpReading.save()
			} else {
				log.warn("** Premise not found: "+ reading.name)
			}
		}
		log.info("*** Parsing Water info. for: "+ fileInfo.name +" complete ***")
	}
}
