def matchIntToString(someNumber: Int): String 
	= someNumber match {
		case 1 => "The loneliest number"
		case 2 => "It's company."
		case 3 => "It's a crowd."
		case _ => someNumber.toString()
	}