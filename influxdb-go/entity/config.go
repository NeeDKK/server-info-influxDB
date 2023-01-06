package entity

type Config struct {
	Server   Server   `yaml:"server" json:"server"`
	Influxdb Influxdb `yaml:"influxdb" json:"influxdb"`
}
