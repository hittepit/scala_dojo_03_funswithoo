package dojo.items

class User(var cash:Int) {
  def buy(purchasable: Purchasable){
    cash = cash-purchasable.price
  }

}
