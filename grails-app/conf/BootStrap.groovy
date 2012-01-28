import citugreen.*;

class BootStrap {

    def init = { servletContext ->
		
		// only add the data if none exists
		if (!Tarrifs.count()) {
			// create some tarrifs
			Tarrifs initial = new Tarrifs(coldWaterTarrif:0.003339, hotWaterTarrif:0.006249, greyWaterTarrif:0.001365, elecTarrif:0.108, heatTarrif:0.1163).save()
			
		
			// create some dummy users
			User phil = new User(userName:'psellick', firstName:'Phil', lastName:'Sellick', contactEmail:'sonic@sellick.org', vmUserId:'45465654').save()
			User ben = new User(userName:'bhanson', firstName:'Ben', lastName:'Hanson', contactEmail:'ben@chilling.co.uk', vmUserId:'12154852').save()
			User neil = new User(userName:'nillingworth', firstName:'Neil', lastName:'Illingworth', contactEmail:'neil@virginmedia.com', vmUserId:'12154852').save()
			User helen = new User(userName:'hsellick', firstName:'Helen', lastName:'Sellick', contactEmail:'helen@virginmedia.com', vmUserId:'12154852').save()
			User john = new User(userName:'jhiggins', firstName:'John', lastName:'Higgins', contactEmail:'john@virginmedia.com', vmUserId:'12154852').save()
				
			// create some dummy premises and assign the users
			Premise philsFlat = new Premise(flatNo:'308', addressLine1:'Sonic House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:phil).save()
			Premise bensFlat = new Premise(flatNo:'309', addressLine1:'Beanie House', addressLine2:'CituGreen Est.', postCode:'SW15 5AS', user:ben).save()
			Premise bobsFlat = new Premise(flatNo:'607', addressLine1:'Sonic House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:phil).save()
			Premise keithsFlat = new Premise(flatNo:'215',addressLine1:'Beanie House', addressLine2:'CituGreen Est.', postCode:'SW15 5AS', user:ben).save()
			Premise johnsFlat = new Premise(flatNo:'606', addressLine1:'Sonic House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:john).save()
			Premise helenFlat = new Premise(flatNo:'608', addressLine1:'Beanie House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:helen).save()
			Premise jacksFlat = new Premise(flatNo:'106', addressLine1:'Sonic House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:phil).save()
			Premise neilsFlat = new Premise(flatNo:'610', addressLine1:'Beanie House', addressLine2:'CituGreen Est.', postCode:'SW5 3AP', user:neil).save()
			
			// create the STBs
			SetTopBox stb1 = new SetTopBox(macAddress:'AA:FF:EE:00:EE', TSMid:'12315464', premise:philsFlat).save()
			SetTopBox stb2 = new SetTopBox(macAddress:'A4:FF:TT:60:EE', TSMid:'345345', premise:bensFlat).save()
			SetTopBox stb3 = new SetTopBox(macAddress:'6A:FF:CC:50:EE', TSMid:'345564', premise:bobsFlat).save()
			SetTopBox stb4 = new SetTopBox(macAddress:'56:FF:YY:06:EE', TSMid:'5467345', premise:keithsFlat).save()
			SetTopBox stb5 = new SetTopBox(macAddress:'AA:34:CC:70:EE', TSMid:'876345354', premise:johnsFlat).save()
			SetTopBox stb6 = new SetTopBox(macAddress:'5A:FF:CC:u0:EE', TSMid:'834234', premise:helenFlat).save()
			SetTopBox stb7 = new SetTopBox(macAddress:'AA:FF:65:06:EE', TSMid:'23546', premise:jacksFlat).save()
			SetTopBox stb8 = new SetTopBox(macAddress:'FA:BF:45:00:EE', TSMid:'36315464', premise:neilsFlat).save()
			SetTopBox stb9 = new SetTopBox(macAddress:'WR:BF:T5:00:EE', TSMid:'6345345', premise:neilsFlat).save()
		}
		
    }
    def destroy = {
    }
}
