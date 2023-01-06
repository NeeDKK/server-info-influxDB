package entity

import "encoding/json"

type ServerInfoVO struct {
	Times []string      `json:"times"`
	Cpu   []json.Number `json:"cpu"`
	Mem   []json.Number `json:"mem"`
}
