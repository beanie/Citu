package citugreen

import citugreen.ReadingService;

class HeatReadingJob {

	ReadingService readingService

    def cronExpression = "0 0 0 * * ?" // run on 0 second 0 minute 0 hour all the time

    def execute() {
        readingService.processWater()
    }
}
