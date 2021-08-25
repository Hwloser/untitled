package main

import "fmt"

func main() {
	var Map map[string]string

	Map = make(map[string]string)

	Map["key"] = "value"
	Map["number"] = "number_value"

	fmt.Println(Map)

	testMapRange(&Map)

	fmt.Println("test delete")
	testDeleteFunctionForMap(&Map)
}

func testMapRange(Map *map[string]string) {
	for key, value := range *Map {
		fmt.Println(key, value)
	}
}

func testDeleteFunctionForMap(Map *map[string]string) {
	delete(*Map, "key")
	delete(*Map, "test")

	fmt.Println(Map)
}
