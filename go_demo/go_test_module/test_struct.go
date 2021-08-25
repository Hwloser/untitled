package main

import "fmt"

type Books struct {
	Title   string
	Author  string
	Subject string
	BookId  int
}

func main() {
	//b1 := Books{"go 语言", "huanwei", "go 教程", 1234}
	//
	//fmt.Println(b1)
	//
	//fmt.Println(Books{Title: "title", Author: "huanwei"})
	//fmt.Println(b1.BookId)

	testStructPointer()
}

func testStructPointer() {
	var b2 *Books

	b2 = &Books{"go 语言", "huanwei", "go 教程", 1234}

	fmt.Println(b2)
}
