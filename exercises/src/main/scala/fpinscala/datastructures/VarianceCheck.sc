
abstract class Animal {
  def name: String
}

case class Cat(name: String) extends Animal

case class Dog(name: String) extends Animal

//Covariance
def printAnimalNames(animals: List[Animal]): Unit = {
  animals.foreach {
    animal => println(animal.name)
  }
}

val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))


printAnimalNames(cats)

printAnimalNames(dogs)

//Contravariance
abstract class Printer[-A] {
  def print(value: A): Unit
}

class AnimalPrinter extends Printer[Animal] {
  override def print(value: Animal): Unit = println("The Animals name is: " + value.name)
}

class CatPrinter extends Printer[Cat] {
  override def print(value: Cat): Unit = println("The cats name is " + value.name)
}

val cat: Cat = Cat("boots")
val animalPrinter: AnimalPrinter = new AnimalPrinter
val catPrinter: CatPrinter = new CatPrinter

def printMyCat(printer: Printer[Cat]): Unit = {
  printer.print(cat)
}

printMyCat(catPrinter)

printMyCat(animalPrinter)


//More Contravariance

abstract class Type [-T]{
  def typeName : Unit
}

class SuperType extends Type[AnyVal]{
  override def typeName: Unit = {
    println("SuperType")
  }
}
class SubType extends Type[Int]{
  override def typeName: Unit = {
    println("SubType")
  }
}

class TypeCarer{
  def display(t: Type[Int]){
    t.typeName
  }
}

object ScalaContravarianceTest {

  def main(args: Array[String]) {
    val superType = new SuperType
    val subType = new SubType

    val typeCarer = new TypeCarer

    typeCarer.display(subType)
    typeCarer.display(superType)
  }

}

ScalaContravarianceTest.main(Array("dummy"))




