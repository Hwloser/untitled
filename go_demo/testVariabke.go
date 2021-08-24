package main

import "fmt"

func main() {
	// 1. test default zero value
	var i int
	var f float64
	var b bool
	var s string

	fmt.Printf("%v %v %v %q\n", i, f, b, s)

	// 2. judge value type self
	var d = true
	fmt.Printf("%v \n", d)

	// 3. avoid var
	// declare variable and initial it
	v_name := "huanwei"
	fmt.Println(v_name)

}
