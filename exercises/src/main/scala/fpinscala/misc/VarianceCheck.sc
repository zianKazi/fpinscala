
//Creation of parent and child types
abstract class Animal {
  def name: String
}

case class Cat(name: String) extends Animal

case class Dog(name: String) extends Animal


/**
  * Wikipedia definition of Variance ()
  * Variance refers to how subtyping between more complex types relates to subtyping between their components.
  * For instance, if the type Cat is a subtype of Animal, then an expression of type Cat can be used wherever an expression of type Animal is used.
  * How should a list of Cats relate to a list of Animals? Or how should a function returning Cat relate to a function returning Animal?
  * Depending on the variance of the type constructor, the subtyping relation of the simple types may be either preserved, reversed, or ignored for the respective complex types. In the OCaml programming language, for example, "list of Cat" is a subtype of "list of Animal" because the list constructor is covariant. This means that the subtyping relation of the simple types are preserved for the complex types, while "function from Animal to String" is a subtype of "function from Cat to String" because the function type constructor is contravariant in the argument type.
  * Here the subtyping relation of the simple types is reversed for the complex types.
  *
  */

//Quick note: In Scala "-" before type parameter denotes Contravariance and "+" denotes Covariance
//Covariance
def printAnimalNames(animals: List[Animal]): Unit = {
  animals.foreach {
    animal => println(animal.name)
  }
}

//List is a covariant type which means that a List[Cat] is a subtype of a List[Animal]
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

//Printer is contravariant which means that a Printer[Animal] is a subtype of Printer[Cat].
// This is why in this example, we can pass Printer[Animal] where a Printer[Cat] is expected.
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




