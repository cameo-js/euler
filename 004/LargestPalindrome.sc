def isPelindrome(n: Int): Boolean = n == n.toString.reverse.toInt

def largestPalindrome: Int = {
  var result = 0
  for {
    x <- 100 to 999
    y <- 100 to 999
    temp = x * y
    if isPelindrome(temp)
    if result < temp
  } result = temp
  result
}

println(largestPalindrome)
