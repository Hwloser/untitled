package main

import (
	"fmt"
	"time"
)

func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(s)
	}
}
func simpleGoRoutine() {
	go say("world")
	say("hello")
}

func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum
}
func channelGoRoutine() {
	s := []int{7, 2, 8, -9, 4, 0}

	c := make(chan int)

	go sum(s[:len(s)/2], c)
	go sum(s[len(s)/2:], c)

	x, y := <-c, <-c

	fmt.Println(x, y, x+y)
}

func channelBuffer() {
	// 带缓冲区的channel允许发送端的数据发送和接收端的数据获取处于异步状态，
	// 也就是说发送端发送的数据可以放在缓冲区里面，可以等待接收端去获取数据，
	// 而不是立刻需要接收端去获取数据。
	ch := make(chan int, 2)

	ch <- 1
	ch <- 2

	fmt.Println(<-ch)
	fmt.Println(<-ch)
}

func main() {
	// 1.
	simpleGoRoutine()

	fmt.Println(" -- -- --  -- -- --  -- -- -- ")

	// 2.
	channelGoRoutine()

	fmt.Println(" -- -- --  -- -- --  -- -- -- ")

	// 3.

	fmt.Println(" -- -- --  -- -- --  -- -- -- ")

	channelBuffer()
}
