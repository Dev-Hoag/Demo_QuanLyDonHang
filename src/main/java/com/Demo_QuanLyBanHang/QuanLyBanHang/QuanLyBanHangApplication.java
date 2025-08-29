package com.Demo_QuanLyBanHang.QuanLyBanHang;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.constants.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ JwtProperties.class})
public class QuanLyBanHangApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyBanHangApplication.class, args);
	}

}
