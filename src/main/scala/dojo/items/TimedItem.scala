package dojo.items

trait TimedItem {
  val readynessDelay:Int
  var startTime:Int = 0
  val timedAction:Any

  def ready(now:Int) = now-startTime>=readynessDelay

  def act(now:Int) : Option[Any] = if(ready(now)) Some(timedAction) else None
  
  def startClock(now:Int) = startTime = now

}
