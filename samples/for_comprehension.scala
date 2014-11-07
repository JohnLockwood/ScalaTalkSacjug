val shouting = 	"HEY SHOUTING IS IMPOLITE".split(" ")
val whisper = for (s <- shouting) yield s.toLowerCase()		