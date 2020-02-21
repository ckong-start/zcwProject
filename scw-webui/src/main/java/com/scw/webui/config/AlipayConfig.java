package com.scw.webui.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
	public static String app_id = "2016101800715307";
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDmLl7zlvH6Kk/tH7ynouw1LqCQbWJZpeQ2MPT3SG21wAFKR7FnuePjb9TXhCGFKwCadMs8wtx/Dqzj9JOM6NgcfkxxDgyxIFDdLZBY1OujjFIxO0chJthy7xf0MOCk4Ge+pxPrIvNPpE1NFlFm2pS4IwPxfC+zNew9ASmpyI99CuDNDl9Og3FnMuHIuHBQbBO3/q81eWyv7rkeN1vhzJ2rHe65gkpdfQujjucQ000xnMqEhgnk7nk5Yt1f6dJ6jNqY9anoFti08MYxXBLYzFoeUUTnZi1k0pcFuC+fFDB7UUWqWv0YcA2YclB+tWcOjOkTK9Pd1cME/rfeb36i9XGNAgMBAAECggEAQg32k6KyiB7SVvLvmY/Ihy59R1UMjztq9R4xfjEbwrd5504Rm/mdaywxgTNaxzNi9fAkTqiAzDly5evO1/YN5odb+mmUvNPQQNiNfuCDSqi/AX/IVrwmCz2PuzS3krkPLIgX0Dd0aVt2D3QjIggB+6GYxRjlO2FzDMKjqSR0AOat5pJqFSxOT2av/wz0pnc/Z5Ssu+ll75SkZTHBdATTSRVAPxyScLiCxQQjqrZKtCTtb70d10aIwfkEzPsfcP55PTU4V8nVYdVDNLVjSmeRuZFgkBnP9ZpkOJ36rPM9IklrxmoSRw3RPug+n7fxI6/K3uX5VlQURr0k/6v8kIY8YQKBgQD4OxTpH16SfTX6D0xo90UZzCb2O6XrxIxS4abEq4rVeG1aP91oYtFrCVE26GWlfLYwj0mWiIC9XOdVqxRpiOptC3H+1XTPEHsYzh6ICmHc/tUvAu31ibfscUQtsjjoNPRcE7cIV+qRuTyEtIlcGfl6Fh5mQq2qaI6FlqJj5tlGxQKBgQDtYqsrn51DDCrlAWcT+XKwDTDc6sUCSaqf7R+WywSpA6c14XqATzcFS+tFpXtey4rgS6MlGcCP5r++IIFWNlXNluZ7yOUFD0qG4Tz4dBNJ8K94BzPDSoNirnMChm2DrxJ3xHzTbaMO/l/1amQA4zi6wb9OUlqu/BxnoNLU8/ZsKQKBgQDczult6dqdYkX2d09X+8T7CrAJzstsKz1LqLBJN/LaqzJS2oagCW8R0Iru1dE9YO9LyNoDzs7+Izsg6HrS2dj2QP0ZW1V5r8ZgLVHZX6roA8Ms+2UjSmtSJvsnzuZyIFDR6zOgd1wQXn8n6NRbGjviTvShsGR9Nl65FGLFjR7vpQKBgQC9KeM83/RxeUC1PGjngd/iaqCFao+TQyC0UEDb6OvNwxt71DHKDVXK4rSYy0WERK5mDd/zsJHCGh6xYzWtfZ7iNV11RqE5ZvX8hYAQfG7ifOlWvGaDON15xYngeT7LsLcGuYy+7p8BU7Mj3aPTET4jlEDeKMIarsvzyDQ5fi7gAQKBgQDjCvXjjUv/3+4By0Q8/wN+5lzhdHo0DiXginVlNuFNc2gmExREjZWfXiAt1mH/8UwPHR4jXLbXAv8BRDfmTGXng7Y9A8EYpTOJhuB+Tw/L0CoUgRcpBONTvg3Mj1Gn5Y9AfLfLqDpUIHM3XohjUAM5J2NhjTi8jus7vtcn7Jjaig==";
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1c5fPW3CtjCE08qjymxfDXhU7ADRub/H6j9skAH42EPxC9XOkhQfY01TIHJcK/YOxalvmAa5MObRZ01zg/U5HII1/HE1cF8WRF+XhlnEvy2LiAB9pHa0BYtoaowEWd1aPvqyYVAt2EazrPIzl33py6qZKEUWmtS+3Upn5Stu+w29jExlllPHHXq0BIRo6DhcPcNlhavRsqh2QMrAiW2PiaZwUkd79t1/8FDIsard0b4GNOrVYH9GK4977yF4uYL8gZFRT+7mxEEURe+z6FclW3flAyL7wLuTojQU8Hy5/wgcFfy/lCxtk2kzlt8A9ExT0NUvl1syfF22JtDz/t+NewIDAQAB";
	public static String notify_url = "http://47.116.5.173/order/returnurl";
	public static String return_url = "http://47.116.5.173/order/notifyurl";
	public static String sign_type = "RSA2";
	public static String charset = "utf-8";
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	public static String log_path = "";

	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}