<%@ page import="citugreen.Premise" %>
<%@ page import="java.text.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'premise.label', default: 'Premise')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <gui:resources components="['tabView']"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Summary for: ${fieldValue(bean: premiseInstance, field: "flatNo")} ${fieldValue(bean: premiseInstance, field: "addressLine1")}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                       <tr class="prop">
                       
                            <td valign="top" class="name"><g:message code="premise.user.label" default="Name" /></td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${premiseInstance?.user?.id}">${premiseInstance?.user?.firstName?.encodeAsHTML()} ${premiseInstance?.user?.lastName?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                       
                            <td valign="top" class="name"><g:message code="premise.user.label" default="Username" /></td>
                            
                            <td valign="top" class="value">${premiseInstance?.user?.userName?.encodeAsHTML()} - ${premiseInstance?.user?.contactEmail?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            
                            <td valign="top" class="name"><g:message code="premise.flatNo.label" default="Address:" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: premiseInstance, field: "flatNo")} ${fieldValue(bean: premiseInstance, field: "addressLine1")}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            
                            <td valign="top" class="name">&nbsp;</td>
                            
                            <td valign="top" class="value">${fieldValue(bean: premiseInstance, field: "addressLine2")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="premise.postCode.label" default="Post Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: premiseInstance, field: "postCode")}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            
                            <td valign="top" style="text-align: left;" class="value" colspan="2">
                                <ul>
                                <table>
				                    <thead>
				                        <tr>
				                        
				                            <g:sortableColumn property="fileDate" title="${message(code: 'r.fileDate.label', default: 'Date')}" />
				                        
				                            <g:sortableColumn property="elecReading" title="${message(code: 'r.elecReading.label', default: 'Elec Reading')}" />
				                        
				                        	<g:sortableColumn property="heatReading" title="${message(code: 'r.heatReading.label', default: 'Heat Reading')}" />
				                        
											<g:sortableColumn property="heatCost" title="${message(code: 'r.heatCost.label', default: 'Heat Cost')}" />
											
											<g:sortableColumn property="coldWater" title="${message(code: 'r.coldWater.label', default: 'Cold Water Reading')}" />
											
											<g:sortableColumn property="hotWater" title="${message(code: 'r.hotWater.label', default: 'Hot Water Reading')}" />
											
											<g:sortableColumn property="greyWater" title="${message(code: 'r.greyWater.label', default: 'Grey Water Reading')}" />				                                    
				                        
				                        </tr>
				                    </thead>
				                    <tbody>
				        
                                <g:each in="${premiseInstance.elecReadings}" var="r">
                                    
				                        <tr class="">
				                        
				                            <td><g:formatDate date="${r.fileDate}" type="date" style="SHORT"/></td>
				                        
				                            <td>${fieldValue(bean: r, field: "elecReading")}</td>
				                        
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                        
				                        </tr>
				                    
                                </g:each>
                                <g:each in="${premiseInstance.heatReadings}" var="r">
                                    
				                        <tr class="">
				                        
				                            <td><g:formatDate date="${r.dateCreated}" type="date" style="SHORT"/></td>
				                        
				                            <td>&nbsp;</td>
				                        
				                            <td>${fieldValue(bean: r, field: "heatReading")}</td>
				                            
				                            <td>${fieldValue(bean: r, field: "heatCost")}</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                        
				                        </tr>
				                    
                                </g:each>
                                <g:each in="${premiseInstance.waterReadings}" var="r">
                                    
				                        <tr class="">
				                        
				                            <td><g:formatDate date="${r.fileDate}" type="date" style="SHORT"/></td>
				                        
				                            <td>&nbsp;</td>
				                        
				                            <td>&nbsp;</td>
				                            
				                            <td>&nbsp;</td>
				                            
				                            <td>${fieldValue(bean: r, field: "coldWater")}</td>
				                            
				                            <td>${fieldValue(bean: r, field: "hotWater")}</td>
				                            
				                            <td>${fieldValue(bean: r, field: "greyWater")}</td>
				                        
				                        </tr>
				                    
                                </g:each>
                                <tr class="">
				                        
				                            <td><b>Totals:</b></td>
				                        
				                            <td>${fieldValue(bean: premiseInstance, field: "totalElecUsage")}kWh</td>
				                            
				                            <td>&pound;${fieldValue(bean: premiseInstance, field: "totalElecCost")}</td>
				                            
				                            <td>${fieldValue(bean: premiseInstance, field: "totalHeatUsage")}kWh</td>
				                        
				                            <td>&pound;${fieldValue(bean: premiseInstance, field: "totalHeatCost")}</td>
				                            
				                            <td>${fieldValue(bean: premiseInstance, field: "totalColdWater")}L</td>
				                            
				                            <td>${fieldValue(bean: premiseInstance, field: "totalHotWater")}L</td>
				                            
				                            <td>${fieldValue(bean: premiseInstance, field: "totalGreyWater")}L</td>
				                        
				                </tr>
                                </tbody>
				                </table>
                                </ul>
                            </td>
                            
                        </tr>
                        
                        <tr class="prop">
                            
                            <td valign="top" class="yui-skin-sam" colspan="2" align="center">
								
								<% 
								def elecGraphData = []
								for (i in premiseInstance.elecReadings) {
									def dataObj = [[DateFormat.getDateInstance(DateFormat.SHORT).format(i.fileDate), i.elecReading]]
									elecGraphData += dataObj
								}
								def waterGraphData = []
								for (i in premiseInstance.waterReadings) {
									def dataObj = [[DateFormat.getDateInstance(DateFormat.SHORT).format(i.fileDate), i.coldWater, i.hotWater, i.greyWater]]
									waterGraphData += dataObj
								}
								def heatGraphData = []
								for (i in premiseInstance.heatReadings) {
									def dataObj = [[DateFormat.getDateInstance(DateFormat.SHORT).format(i.dateCreated), i.heatReading]]
									heatGraphData += dataObj
								}
								 %>								
								<gui:tabView>
								    <gui:tab label="Electricity Readings" active="true">
								        <gvisualization:barCoreChart elementId="elecGraph" title="My Daily Electricity Usage" width="${450}" height="${300}" columns="[['string', 'Usage'], ['number', 'Electricity (kWh)']]" data="${elecGraphData}" />
										<div id="elecGraph"></div>
								    </gui:tab>
								    <gui:tab label="Heat Readings">
								        <gvisualization:barCoreChart elementId="heatGraph" title="My Daily Heat Usage" width="${450}" height="${300}" columns="[['string', 'Usage'], ['number', 'Electricity (kWh)']]" data="${heatGraphData}" />
										<div id="heatGraph"></div>
								    </gui:tab>
								    <gui:tab label="Water Readings">
								        <gvisualization:barCoreChart elementId="waterGraph" title="My Daily Water Usage" width="${450}" height="${300}" columns="[['string', 'Usage'], ['number', 'Cold Water (m3)'], ['number', 'Hot Water (m3)'], ['number', 'Grey Water (m3)']]" data="${waterGraphData}" />
										<div id="waterGraph"></div>
								    </gui:tab>
								</gui:tabView>
								
							</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
