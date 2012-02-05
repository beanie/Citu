package citugreen

import org.ccil.cowan.tagsoup.*
import groovy.util.XmlSlurper
import grails.converters.*


class BusController {

	def  getBusTimes = {
		
		def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
		
		def url = new URL("http://wypte.acislive.com/pip/stop.asp?naptan=45010086&pscode=1&dest=&offset=0&textonly=1")
		
		def timeTable = new ArrayList()
		def map
		
		url.withReader { reader ->
		
			def html = slurper.parse(reader)
			
			String busNo = ""
			String destination = ""
			String busDue = ""
			
			def busNos = html.'**'.findAll{ it.@width == '25%'}
			def destinations = html.'**'.findAll{ it.@class == 'destination'}
			def busTimes = html.'**'.findAll{ it.@align == 'right'}
		
			def x = 0
			for ( i in busNos ) {
				busNo = busNos[x].toString()
				destination = destinations[x].toString()
				busDue = busTimes[x].toString()
				map = [busNo:busNo, busDetination:destination, busDue:busDue]
				timeTable.add(map)
				x++
			}
		
		}
		
		render timeTable as JSON
		
	}
}
