package dojo.items.house
import dojo.items.TimedItem

class JukeBox extends HouseItem with TimedItem{
  val readynessDelay = 100
  val timedAction = "Blah-Blah"
	val price = 5
}
