import scala.io._

def list(file: String) : Unit = {
	Source.fromFile(file).getLines.foreach( println )
}
