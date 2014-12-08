case class Person(name: String, email: String);

val p1 = Person("John", "john@noneofyourbusiness.com")
val p2 = Person("Jenniffer", "jenniffer@noneofyourbeeswax.com")
val p3 = Person("Patrick", "Patrick@chillax.com")
val p4 = Person("Joe", "joe_sample@noneofyourbeeswax.com")

// A predicate function (could have been a class member, too.)
def isNoneOfYourBeeswax(person: Person): Boolean = {
	person.email.contains("noneofyourbeeswax")
}

def displayPerson(person: Person) {
	println("Matching... " + person)
	person match {
		case Person("John", _) => 
			println("Hi John")
		case Person(_, "jenniffer@noneofyourbeeswax.com") =>
			println("Hi Jenniffer")
		case p : Person if isNoneOfYourBeeswax(p) => 
			println("None Of Your Beeswax, man...")
		case _ => 
			println("I don't know this person!")
	}
}


displayPerson(p1)
displayPerson(p2)
displayPerson(p3)
displayPerson(p4)

