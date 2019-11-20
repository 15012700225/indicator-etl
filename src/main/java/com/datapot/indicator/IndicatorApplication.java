package com.datapot.indicator;

import com.datapot.indicator.config.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Import({ DynamicDataSourceRegister.class })
@SpringBootApplication
@EnableTransactionManagement
public class IndicatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(IndicatorApplication.class, args);
	}
}