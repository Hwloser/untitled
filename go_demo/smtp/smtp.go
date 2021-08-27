// smtp.go
package main

import (
	"net/smtp"
)

func main() {
	auth := smtp.PlainAuth(
		"go demo",
		"insight@mail.hypers.com",
		"1234abcD",
		"smtp.exmail.qq.com",
	)

	smtp.SendMail(
		"smtp.exmail.qq.com:25",
		auth,
		"insight@mail.hypers.com",
		[]string{"huanwei.guo@mail.hypers.com"},
		[]byte("To: recipient@example.net\\r\\nFrom: sender@example.org\\r\\nSubject: 邮件主题\\r\\nContent-Type: text/plain; charset=UTF-8\\r\\n\\r\\nHello World\n\n————————————————\n原文作者：Go 技术论坛文档：《Go 入门指南（）》\n转自链接：https://learnku.com/docs/the-way-to-go/1512-smtp-sends-mail/3714\n版权声明：翻译文档著作权归译者和 LearnKu 社区所有。转载请保留原文链接"),
	)
}
