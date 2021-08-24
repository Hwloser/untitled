package main

import "fmt"

func main() {
	println("Google" + "test")

	var num = 123
	var end_date = "2020-12-31"
	var url = "Code=%d&endDate=%s"
	var target_url = fmt.Sprintf(url, num, end_date)

	println(target_url)

	var a string = "huanwei"
	fmt.Println(a)

	var b, c int = 1, 2
	fmt.Println(b, c)

}
