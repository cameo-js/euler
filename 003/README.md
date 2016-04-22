# Largest prime factor

## 문제

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

## 고찰

### 초기 해결 방법

초기 해결 방법은 모든 범위의 수를 리스트로 만들어(인수만 1차 필터링) 꼬리재귀를 이용해 [에라토스테네스의체](https://namu.wiki/w/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98%20%EC%B2%B4) 방법으로 가장 높은 소인수를 걸러내는 것.

### @tailrec [docs](http://www.scala-lang.org/api/current/index.html#scala.annotation.tailrec)

스칼라는 꼬리 재귀 최적화를 하는데 재귀호출마다 새로운 스택을 만들지 않고 같은 스택 프레임을 재활용하기 때문에 루프와 성능차이가 거의 없다고 볼 수 있다.
꼬리재귀가 아닐때다른 문제로는 스택 프레임의 사이즈가 제한되어있는 상황에서 StackOverflowError 가 발생할 수 있다.

**재귀 호출후에 연산을 수행하면 꼬리 재귀가 아닌데** 그렇게 되면 스칼라의 최적화 혜택?을 받지 못한다.

@tailrec 어노테이션은 컴파일시점에 꼬리 재귀인지 알려준다.

### NumericRange [docs](http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.NumericRange)

문제에 나온 600851475143는 Int 의 범위(2^31-1, 2147483647)를 훌쩍 넘어선다.

간단히 List를 만들기 위해 Range를 만들고 변환(예, (1 to 100).toList) 하려고 했다.

하지만 Range를 만드는 `1L to 600851475143L` 과정에서 `java.lang.IllegalArgumentException: More than Int.MaxValue elements.` 메시지를 접하게 된다.

이는 `def count(p: (T) ⇒ Boolean): Int` 보시면 알겟지만 반환값이 Int 이다. :disappointed_relieved:

### 초기 구현의 문제

[초기 구현](https://github.com/cameo-js/euler/blob/7b8cbf971c1eb6774c8ccfda12bb35feaec53327/003/LargestPrimeFactor.sc)의 문제는 크게 두 가지 였다.

- Range로 해당 범위를 만들지 못하는 것(사용법 미숙)
- 해당 범위의 리스트를 만들고 필터링을 하는것은 성능상 결과를 보는것 보다 팬이 도는 소리를 듣는것이 빠름

### 새로운 로직

쉽게 봤지만 이해하는데 오랜시간이 걸렸다.

실제 구현된 코드는 간단하지만 왜 그런 코드가 되는지 단번에 이해하기가 어려웠다. :sob:

(내가 다시 까먹을 것 같아서)간단하게 설명을 하자면 가장 작은 소인수 부터 찾아 나가는 것 인데,

- 인자를 기준이 되는 (초기 값: 600851475143)수를 받는 꼬리 재귀로 함수를 구현
- 2 부터 시작(제수)해서 기준이 되는 수(피제수)에 나누며 찾아 나간다.
- 나누어 떨어지면 그 수(제수)는 소인수 이다. (나누어 떨어지면 그냥 인수 아닌가? 왜 소인수인지가 의문스러웠는데..!! 그냥 소수가 아닌 인수라면 그 이전에 다른 (소)인수가 있었겟지...)
- 그리고 재귀 호출시 인자를 몫 으로 넘겨준다. (여기서도 왜 인자를 몫으로 넘겨주나? 에 대한 의문이 있었는데 가장 작은 소인수를 구하는 과정에 지나온 수들은 결론적으로 나누어 떨어지지 않기 때문에 즉 인수가 아니기 때문에 범위를 줄일 수 있다.)
- 몫이 소인수가 될 때 까지 반복하면 가장 큰 소인수를 구할 수 있다.
