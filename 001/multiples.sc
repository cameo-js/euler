def isMultiplesOf(num:Int):Boolean = if(num%3==0 || num%5==0) true else false

val sum = (1 until 1000).filter(isMultiplesOf).sum

println(s"sum is $sum");
