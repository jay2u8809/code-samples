package study.collections

object StudyArray {

  def example(strArr: Array[String]): Unit = {

    println("Length: " + strArr.length)
    strArr.foreach { f =>
      println("Value: " + f)
    }
    for (elem <- strArr) {
      println("elem: " + elem)
    }
  }

  def printArrayInfo(strArr: Array[String]): Unit = {
    strArr.foreach(println)
  }
}
