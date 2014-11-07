val shouting2 = "HEY SHOUTING IS IMPOLITE".split(" ")
val whisper2 = for (s <- shouting2) yield {
	val t = s + "_NERD_SAYS_WHAT"
	t.toLowerCase()
}