package citugreen

import citugreen.ReadingService;

class ReadingsJob {

	ReadingService readingService

	def cronExpression = "0 0 * * * ?" // run on 0 second 0 minute all the time

    def execute() {
        readingService.processWater()
		readingService.processElec()
    }
}
