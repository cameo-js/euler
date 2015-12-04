import scala.annotation.tailrec

val num = 13195L

@tailrec
def primeFactor(large: Long, list: List[Long]): Long = list match {
  case head :: rest => primeFactor(head, rest.filter(_ % head != 0))
  case _ => large
}

println(primeFactor(2L, (Range.Long(2L, num, 1)).toList.filter(num % _ == 0)))
