# Largest prime factor

## 문제

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

## 고찰

### 초기 해결 방법

초기 해결 방법은 모든 범위의 수를 리스트로 만들어(인수만 1차 필터링) 꼬리재귀를 이용해 [에라토스테네스의체](https://namu.wiki/w/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98%20%EC%B2%B4) 방법으로 가장 높은 소인수를 걸러내는 것.

### @tailrec [docs](http://www.scala-lang.org/api/current/index.html#scala.annotation.tailrec)

스칼라는 꼬리 재귀 최적화를 하는데 재귀호출마다 새로운 스택을 만들지 않고 같은 스택 프레임을 재활용하기 때문에 루프와 성능차이가 거의 없다고 볼 수 있다.

**재귀 호출후에 연산을 수행하면 꼬리 재귀가 아닌데** 그렇게 되면 스칼라의 최적화 혜택?을 받지 못한다.

@tailrec 어노테이션은 컴파일시점에 꼬리 재귀인지 알려준다.

### NumericRange [docs](http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.NumericRange)

문제에 나온 600851475143는 Int 의 범위(2^31-1, 2147483647)를 훌쩍 넘어선다.

간단히 List를 만들기 위해 Range를 만들고 변환(예, (1 to 100).toList) 하려고 했다.

하지만 Range를 만드는 `1L to 600851475143L` 과정에서 `java.lang.IllegalArgumentException: More than Int.MaxValue elements.` 메시지를 접하게 된다.

이는 `def count(p: (T) ⇒ Boolean): Int` 보시면 알겟지만 반환값이 Int 이다. :disappointed_relieved:
