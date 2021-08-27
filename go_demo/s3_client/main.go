package s3_client

import (
	"github.com/urfave/cli/v2"
	"os"
)

func main() {
	cliApp := cli.NewApp()
	cliApp.Name = "s3-cli"

	err := cliApp.Run(os.Args)
	if err != nil {
		return
	}
}
