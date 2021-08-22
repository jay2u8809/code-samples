package study.collections

import org.junit.jupiter.api.{BeforeEach, Test}

class StudyArrayTest {

  @BeforeEach
  def beforeEachProcess(): Unit = {}

  @Test
  def exampleTest(): Unit = {
    val jvmPlArr:Array[String] = new Array[String](3);
    jvmPlArr.update(0, "Java")
    jvmPlArr.update(1, "Scala")
    jvmPlArr(2) = "Kotlin"
    StudyArray.example(jvmPlArr)

  }

  @Test
  def printArrayInfoTest() = {
    val jvmPlArr: Array[String] = Array[String]("Java", "Scala", "Kotlin")
    var plArr: Array[String] = Array[String]()

    // Append One
    println("===== Append One =====")
    plArr = jvmPlArr
    plArr :+= "Python"
    StudyArray.printArrayInfo(plArr)
    // Append List
    println("===== Append List =====")
    plArr :++= jvmPlArr
    StudyArray.printArrayInfo(plArr)

    // Prepend One
    println("===== Prepend One =====")
    plArr = jvmPlArr
    plArr +:= "Javascript"
    StudyArray.printArrayInfo(plArr)
    // Prepend List
    println("===== Prepend One =====")
    plArr = Array[String]("Ruby")
    plArr ++:= jvmPlArr
    StudyArray.printArrayInfo(plArr)
  }

}
