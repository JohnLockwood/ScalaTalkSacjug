def roseColored(beloved: Any): Any = {
	beloved match {
		case "green eggs and ham" => -1
		case _ => beloved.toString() + " is beautiful!"
	}
}