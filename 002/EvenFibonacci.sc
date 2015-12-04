import scala.annotation.tailrec

val list = List(2,1)

@tailrec
def fibonacci(list: List[Int]):List[Int] = list match {
  case first :: second :: rest =>
    if(first + second > 4000000) list
    else fibonacci(first + second :: list)
  case head :: rest => fibonacci(head + rest.head :: list)
  case _ => list
}

val sum = fibonacci(list).filter(_%2==0).sum

println(s"sum is $sum")
