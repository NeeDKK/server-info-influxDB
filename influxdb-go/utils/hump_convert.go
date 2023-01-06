package utils

import (
	"fmt"
	"strings"
	"unicode"
)

func Convert(words string) string {
	for index, str := range words {
		if index == 0 {
			words = ""
		}
		if unicode.IsUpper(str) {
			lower := strings.ToLower(fmt.Sprintf("%c", str))
			words = words + "_" + lower
		} else {
			words = words + fmt.Sprintf("%c", str)
		}
	}
	return words
}
