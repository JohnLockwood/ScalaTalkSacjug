package com.codesolid.sacjug

import play.api.libs.json._

object Test extends App {
	/*val json: JsValue = JsObject(Seq(
	   "name" -> JsString("Watership Down")
	))
*/
	val json: JsValue = Json.parse("""
	{
		"publisher":  	"Some Verbose Publishing App",
		"subscribers":	["Some Eager Listening App", "A dumb Google Plus Mashup"]
	}
	""")
	println("The json object is\n" + Json.prettyPrint(json))
	println("The publisher given by json \\ \"publisher\" is " + json \ "publisher")
	println("The subscriber given by (json \\ \"subscribers\")(0) is " + (json \ "subscribers")(0))

}


// case class Message(msgType: String, attributes: Map[String, String], content: String)
// val myMsg1 = Message("Exam", Map("publisher" -> "ABC"), "Not really an exam message")
// val foo = Map("name" -> "John")

