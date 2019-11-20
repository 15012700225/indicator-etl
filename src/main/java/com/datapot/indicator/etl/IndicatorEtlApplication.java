package com.datapot.indicator.etl;

import com.datapot.indicator.etl.config.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Import({ DynamicDataSourceRegister.class })
@SpringBootApplication
@EnableTransactionManagement
public class IndicatorEtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndicatorEtlApplication.class, args);
	}

}
