package dojo

import items.{Purchasable, TimedItem, User}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import dojo.items.artifacts.Unicorn
import dojo.items.Item
import dojo.items.fashion.Hat
import dojo.items.house.Chair
import dojo.items.artifacts.MachineGunUnicorn
import dojo.items.house.JukeBox

@RunWith(classOf[JUnitRunner])
class FunsWithOOTests extends FunSuite with ShouldMatchers{

  // all items should have an id
  test("Unicorn should have id of 1"){
    val unicorn = new Unicorn
    unicorn.id should equal(1)
  }

  test("anonymous items should have an id"){
    val item = new Item{}
    item.id should equal(0)
  }

  // all fashion and house items should be able to be bought for cash deducted from user

  test("should buy hat and deduct 5 cash from user"){
    val hat = new Hat
    assertPurchasableItemCashDeduction(hat)
  }

  test("should buy chair and deduct 3 cash from user"){
    val chair = new Chair
    assertPurchasableItemCashDeduction(chair)
  }

  def assertPurchasableItemCashDeduction(item:Purchasable) {
    val balance = 10
    val user = new User(balance)
    user.buy(item)
    user.cash should equal(10-item.price)
  }

  // MachineGunUnicorn and JukeBox have special actions that are available a set time after creation
  // * MachineGunUnicorn prints Bam-Bam
  // * JukeBox prints Blah-Blah

  test("MachineGunUnicorn goes Bam-Bam"){
    val machineGunUnicorn = new MachineGunUnicorn
    val delay=10
    assertTimedItem(Some("Bam-Bam"), machineGunUnicorn, delay)
  }

  test("JukeBox goes Blah-Blah"){
    val jukeBox = new JukeBox
    val delay=100
    assertTimedItem(Some("Blah-Blah"), jukeBox, delay)
  }

  def assertTimedItem(expected: Some[Any], timedItem: TimedItem, delay: Int){
    val now = 10
    timedItem.startClock(now)

    timedItem.ready(now)          should  equal (false)
    timedItem.act(now + delay -1) should  equal (None)

    timedItem.ready(now + delay)  should  equal (true)
    timedItem.act(now + delay)    should  equal (expected)
  }

}
