package com.hsmap.yuezhihui.pay.alipay;

public class Code {

	//沙箱
//	public static String URL = "https://openapi.alipaydev.com/gateway.do";
//	public static String APP_ID = "2018110662022423";
//	public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDftTS80HP86WfZSZBZCiePH7JO0aWSCTV5p2uJUPYCbTYESx6NjH/4Y8iUeV4EmX5Jek+Gbqa0SYjVxNacsYfSYgfQ71aJ3Pm7+gJgUYcTYIlm/kSszsI0pujiS46yHzBveV91KLqhdIMhOs2B6Tc7foDTdzNH0NkdL5/RGQFhMF1LBixF2ARCH728mzG5n8ZDUagrkTd9KU3EH8wYkSdslRURhhKr0wKxK/u7LK0mR8QzXvLL1oyGtW+w9M3OtHRJnzAqD3omnuAnV5Y8bJR08HS8NlEfkRr9WWHKwAKC2Tbx0x662wVBZYg86zr/eOCVkO6QkzmSCVhD1eSkafkJAgMBAAECggEAP1myYJHrdsCmPvs9HGnqv/Yq6XUnHQ7sjNHjexRpRlClDlwzf8Q87Rtczub7v/Oztp0jbh9hqAZ28Ip4eBEyNFq8eed9KzdfpWLsT1hnFw6senUg/4rhpkVqB8N72mdIsRi+9o+dImG7TKq5TcO8d2Wgl8HWzMyQ31oAOReKs3mWHDJs4iN6hW8timCgA88XcKIH9olr8FpkEQzZNrkObXv6J5OCk1xp/NDZ1bANi8KHHgWziA7F36SduqtNC1ANFAieJt68Jz9bj8yEQD2RXbDm9St2nWnGIK42LZwKPcKATy8WVwbFlzrJQErLulwBVbzlqduvmN6FPwtHclwNAQKBgQD5CU9H+FLGnQb2vBnPcqqOqAJCmxkGOoBgsvaLdVBpX+7ji4gZ7J3lTtlmHVk/zWYqweyja1CZzoHjdKek0hAdUoxcESflPTFJfgCcbVIj4XwXw+sifKjxA9C0PnR6M/3myeW30CVA1A7ZcCwbcSQTSFX/AkvypQIcstOLYOB3KQKBgQDl9pXzdFhcPakve5Ji5FSVnZRipWArt+csAJUFKiAao9oMrCL+BJc2OT8q4taKlDUi+ViTr//KRS53bjUGWxnUjpNX3PjPVDoOrfkw1gQ5KZxUKx5CMp6fGxbzGVyY9cCCvgpSC5C/+L9wwQVqmy1QfBWv1OJh8Ly3e4noaFUO4QKBgQDPwfaEIiRcUZAHszENIaosTPllu6KK0a9b/LE18u7cmg3fgv5OMloffUJcsEtOsPp03Uefpd2eEuvFaAKbvCt5au5xuFZCJmTB3P6BgaovCOPPp8C4CqueNgKc/CWL9Wfya6jbSihqBv9EYxJI6oCgHY6wqafWi9ZRPtbAxlc80QKBgQCIoiFoPFl/vNc4MijA/z9Oggk3L2FbuHHvEA16uxPGIFKfMuu7l8isXAkvFwY34mVL+XDqlyjY6o/4QA/bTeekBm5/RvjCXx37uNyVaqOpvxJ2ZAQfeuoKSNMy73+4FlQimn2RT/PaTBabDOSF+dtLtd5A0bEMVjgaoJlI8CyRoQKBgQDIF+ZfVVZwnCs8XLzv35Rx0Nv7RWKUyUGf2Z6ROFbF+kFDxWmTMYIERPk7NgmfOTFitLt2I2KdHP1rL4v/lnofWDKkotnlHbzviXtVg6BeQTUYtMIOoLGYTPr5d1CfhrWuecVt1d128jHu2wlKMbH1LxMTHXLAAM7ei03DIH97wA==";
//	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1dbbBQajqwRMyQrauZvLMxMmovU5kN23GD2zPNjcmNYKO+Szrt9s2LdO9K/oqfaPxTTNhUbBlUB7Sg6ebrVlPvSlN8MhaK+G7yqKhKr5zrX/lB/0ec3yZSIXZkFWGq+eoFcA1+tNyz7fiOQmg0TJQ1F5X4CKSEM6SYJuPM6F3T1DiJjy20jx50rVS0JJ6Uag7pORHw78dXSC9lqLH25uuJtlarM4kkUySr6OG1rUX7at+HmJr9z8LHBwh2WIEp8XGxnTitQzBFENBRt3RvTr7lu31mDmsi7kU1f35/2Gqgzi81WhITwRp07rjJKnoNp4rwtSAHRnLcxmlBOE7b2i0QIDAQAB";

	//支付宝网关（固定）
	public static String URL = "https://openapi.alipay.com/gateway.do";
	//APPID即创建应用后生成
	public static String APP_ID = "2019050664411046";
	//开发者应用私钥，由开发者自己生成
	public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKa7TjrpTb3vRNmtUn3LETTv7HM042PRGdURVUztQBzXcKmeLj1vANOvLFgKTvQGvTpNZM47NwVUlnjzkatOT3GalSf7VkFHeQKsHkL2lq8dqRlLnbw8NYHAEH0OjDVEoQGAoclL8acCeyk91i+z0KsEmDy84v9mQzL0XkmekYE5SxO4D3PUgMrU2k05y/i5VoJJafA8IkbvrdYAE4vUPeKBTD9R3w2LtcP9oQHvEhUX4fEL5MyLCX7I9pjedeyJRGJcBNZZ/qjTBJcpe4zokaTj43YwRhw2oV4MdXALfWMogcWtr4EwQHuq3w2tjHTd8EpMsfJHHuDOrynIiePNUJAgMBAAECggEARKLMzT8PPpaU5BOSH1gXuuJYRZdB9+YxKNSrlPwkqw/OQ4v2gScKcpIAa7O5OV7wsOz9tUioOXjR0QmhZUrdMGRFI7ImAW9nMz0sTYE+D4M+h4qUks4IWMH2H35YzpKwIlDkbNiLDB3neAfnU365LfykkMn0ehLj2u82ldMSVJUEpV8C5+JNitO/TdCOcjRzJtze624HuJ6ja/613SsIB02Vt/ywIMrbWrrcYp69w3TI9EGjUGg/DybmH+xEkCcW/vhhRMrDlYXYv1vGFcLa+7T9uYyXpnsQ12wHRRMJnInb69fwPHyvQwuxMULLN4dxN2gIYqWuG56ai9UmxjZhGQKBgQDsH6vRWXp1pX+os09YDL2zQcfxDwsZ7CIisOfmshA2TrWrdHPEWOF1tdb14gsir2lLtazZIKGxnnsZAISq6U4YrB7gMe+7i7h15MPW/AbKy0J3GhKe9ug8kRtnKgaPswCkMOglzLfCddrlaMc7gv+MvsWHdDEOm2rJyOusw4Nd5wKBgQDbdcFzIBXvQmoN5Y92gK5w3nP9LlDdaY5Y6kvMg2TaqR5BtZeDaukxWzobWCLHNUJBpv4zWPKV6vl0Td376xpSnMadC/Q9iGbwM44Ky0jl+kIc+q5RPq4G2fyeQaQGRfIWLIPq9qE7cbjXtD6xrT8hGGY8hHkJINu0heykJoN3jwKBgFvR8RGm1v0az2q917PouHgVs3elHu8EqT2OR4+pXK6RqwmybjZDTeFlReM19JiXV73uNvn0O4yDkWDml7CSqyDjBdqu8IegzKljvVhDhyyjIASKqgURBLxQvTQ/3m9Q74Xp0syoMCk5hVbCiP82cMO9h7il0VHZ1AwFysfS+6mvAoGBAL34ylO5E6PAjwOmkzCWpepodmlU3YU8qY9IigLoPraVrO5enmCRfHlQKhWfjsN40/j28JdMSPoDpJrMCPvYgTnAIqGxxmQ5njoRtsSbQmhp4S8qxRjpNEYUDvyDf9Q6Whxdvnf3tIw7njGDAr5VZQ9mCqIaXpfLTMSRJc7W0/j7AoGAdZvFf8in23H84kV090LBe3cFZTU8wQjUL6QPi6lxSImV0Laq9Jljg07ahdGihGs2gMIj3Qc6S23wkA+DZTb8+IB59Z8MEa5lP8sjsGytN5161rzLKOI/cCT2iGlZGWvRofezWalQ9v2sSK318aSGx9QwRP87WSOrDvZiDLP26tE=";
	//应用公钥，由支付宝生成
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhp/IauYXMAPJWhof3EDE840zWJLoSuPZG+5DhhoxiOtMqzh8Ac+wtzdU96xZkESTztAwRlQvsQDiQ1s1FL6rqqZO/S4AApaD+alhtYhABwATh4Ms6oIYyn1mRL4izqpW1KGDvoR0Jim9KHqpDPzYIw5V175GLVC/K7oi3YpeT4hbYYbUEaozRaIFgU86HGRhiHvAHG4cbqNRSii4A5QIYYecR0lwthK2xjoVd66hfWRsu7AatDPKPWcLwLYi/zDDmaF1KcNAm4IB+DQpqUlLQSgBKsuoNCMf9hEYcfU5lm8Q152SEMECpU6kWPDKcGAIzdMhfGTfcoJ9UuHh2BEdSwIDAQAB";
	//支付宝公钥(验签)
	public static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAymu0466U2970TZrVJ9yxE07+xzNONj0RnVEVVM7UAc13Cpni49bwDTryxYCk70Br06TWTOOzcFVJZ485GrTk9xmpUn+1ZBR3kCrB5C9pavHakZS528PDWBwBB9Dow1RKEBgKHJS/GnAnspPdYvs9CrBJg8vOL/ZkMy9F5JnpGBOUsTuA9z1IDK1NpNOcv4uVaCSWnwPCJG763WABOL1D3igUw/Ud8Ni7XD/aEB7xIVF+HxC+TMiwl+yPaY3nXsiURiXATWWf6o0wSXKXuM6JGk4+N2MEYcNqFeDHVwC31jKIHFra+BMEB7qt8NrYx03fBKTLHyRx7gzq8pyInjzVCQIDAQAB";


	//参数返回格式，只支持json
	public static String FORMAT = "json";
	//请求和签名使用的字符编码格式，支持GBK和UTF-8
	public static String CHARSET = "UTF-8";
	//商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	public static String SIGN_TYPE = "RSA2";
	//支付异步回调地址-资金易投币
	//public static String NOTIFY_URL_COINS = "http://file.5etou.cn/paymentCallback/alipayCallbackForEasyCoins.shtml";
	//支付异步回调地址-支持项目
	public static String NOTIFY_URL = "http://m.yhk188.com/index/alipayCallback.dos";



}
