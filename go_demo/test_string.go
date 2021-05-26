package main

import "fmt"

func main() {
	println("Google" + "test")

	var num = 123
	var end_date = "2020-12-31"
	var url = "Code=%d&endDate=%s"
	var target_url = fmt.Sprintf(url, num, end_date)

	println(target_url)
}
