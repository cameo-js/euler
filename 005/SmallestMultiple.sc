import scala.collection.mutable.HashMap

def getResult:Int = {
  def prime_factorization(n: Int): HashMap[Int,Int] = {
    val result = scala.collection.mutable.HashMap.empty[Int,Int]
    var i = 2
    var num = n
    while(i < num){
      if(num%i == 0){
        result += i -> (result.getOrElse(i, 0) + 1)
        num = num / i
      } else {
        i += 1
      }
    }
    result += num -> (result.getOrElse(num, 0) + 1)
  }

  def merge_prime_factor(base: HashMap[Int, Int], target: HashMap[Int, Int]): HashMap[Int, Int] = {
    val base_result = for {
      base_key <- base.keys
      if( !target.contains(base_key) || (target.contains(base_key) && base(base_key) >= target(base_key)) )
    } yield (base_key, base(base_key))

    val target_result = for {
      target_key <- target.keys
      if( !base.contains(target_key) || (base.contains(target_key) && target(target_key) > base(target_key)) )
    } yield (target_key, target(target_key))

    (base_result ++ target_result).foldLeft(HashMap.empty[Int, Int])((m,t) => m += t._1 -> t._2)
  }

  val prime_factors = for(n <- (1 to 20); pf = prime_factorization(n) ) yield (pf)
  var result = prime_factors.foldLeft(HashMap.empty[Int,Int])((b, t)=> merge_prime_factor(b,t))
  result.keys.foldLeft(1)((n,a) => n * math.pow(a,result(a)).toInt )
}
println(getResult)