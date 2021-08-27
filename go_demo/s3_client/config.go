package s3_client

import (
	"github.com/aws/aws-sdk-go/service/s3"
)

// Config global configuration
type Config struct {
	AccessKey    string `ini:"access_key"`
	SecretKey    string `ini:"secret_key"`
	StorageClass string `ini:"storage-class"`

	PartSize int64 `ini:"part-size"`

	Verbose      bool `ini:"verbose"`
	Recursive    bool `ini:"recursive"`
	Force        bool `ini:"force"`
	SkipExisting bool `ini:"skip_existing"`

	HostBase   string `ini:"host_base"`
	HostBucket string `ini:"host_bucket"`
}

var (
	validStorageClasses = map[string]bool{
		"":                                      true,
		s3.ObjectStorageClassStandard:           true,
		s3.ObjectStorageClassReducedRedundancy:  true,
		s3.ObjectStorageClassGlacier:            true,
		s3.ObjectStorageClassStandardIa:         true,
		s3.ObjectStorageClassOnezoneIa:          true,
		s3.ObjectStorageClassIntelligentTiering: true,
		s3.ObjectStorageClassDeepArchive:        true,
	}
)

// functions

//func NewConfig(ctx *cli.Context) {
//	var cfgPath string
//
//	if obj := ctx.StringSlice("config")
//}
