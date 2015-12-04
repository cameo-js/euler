import scala.annotation.tailrec

val list = List(1,2)

@tailrec
def fibonacci(l: List[Int]):List[Int] = {
  val next = l.takeRight(2).sum
  if(next > 4000000) l
  else fibonacci(l ::: List(next))
}

val sum = fibonacci(list).filter(_%2==0).sum

println(s"sum is $sum")
