package main

import (
	"fmt"
	"time"
)

func main() {
	//testSimpleChannel()

	iterateReceive()
}

func iterateReceive() {
	ch := make(chan int)

	go func() {
		for i := 3; i >= 0; i-- {
			ch <- i

			time.Sleep(time.Second)
		}
	}()

	for data := range ch {
		fmt.Println(data)

		if data == 0 {
			break
		}
	}
}

func testSimpleChannel() {
	// 创建一个channel
	ch := make(chan int)

	// 开启一个并发匿名函数
	go func() {
		fmt.Println("start goroutine")

		// 通过 channel 通知 main 的 goroutine
		ch <- 0

		fmt.Println("exit goroutine")
	}()

	fmt.Println("wait go routine")

	// 等待匿名 goroutine
	<-ch

	fmt.Println("all done")
}
