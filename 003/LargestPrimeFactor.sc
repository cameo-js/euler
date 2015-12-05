import scala.annotation.tailrec

val num = 600851475143L

@tailrec
def largest_prime_factor(n: Long): Long = {
  val pf = Stream.from(2).dropWhile(n % _ != 0).head
  if(pf == n) pf else largest_prime_factor(n / pf)
}

println(largest_prime_factor(num))
