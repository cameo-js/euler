import scala.annotation.tailrec

val list = List(1,2)

@tailrec
def fibonacci(l: List[Int]):List[Int] = {
  val next = l.drop(l.length-2).sum
  if(next > 4000000) l
  else fibonacci(l ::: List(next))
}

def isEven(n:Int) = (n % 2) == 0

val sum = fibonacci(list).filter(isEven).sum

println(s"sum is $sum")
