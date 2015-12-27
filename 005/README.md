# Smallest Multiple

## problem

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

[link](https://projecteuler.net/problem=5)

## solution

### ver.1 소인수분해를 이용해서 구하기

각 수를 소인수분해하고 공통인 소인수를 모두 곱한다.
이때 거듭제곱의 지수는 같거나 큰 것을 택하고 공통이 아닌수와 모두 곱한다.

### ver.2 공약수로 나누어 최소공배수 구하기

각 수를 어느 두 수의 몫에도 1 이외의 공약수가 없을 때까지 공약수로 나누고, 공약수가 없는 수는 그대로 내린다.
그리고 모든 공약수와 마지막 몫을 모두 곱한다.