package com.emrekara.finalproject;

import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.productInfo.dao.PrProductInfoDao;
import com.emrekara.finalproject.app.productInfo.entity.PrProductInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class FinalprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(PrProductInfoDao dao) {
		return args -> {

			PrProductInfo prProductInfo1 = new PrProductInfo();
			prProductInfo1.setVatRate(BigDecimal.ONE);
			prProductInfo1.setProductType(ProductType.FOOD);
			dao.save(prProductInfo1);

			PrProductInfo prProductInfo2 = new PrProductInfo();
			prProductInfo2.setVatRate(new BigDecimal(8));
			prProductInfo2.setProductType(ProductType.STATIONARY);
			dao.save(prProductInfo2);

			PrProductInfo prProductInfo3 = new PrProductInfo();
			prProductInfo3.setVatRate(new BigDecimal(8));
			prProductInfo3.setProductType(ProductType.CLOTHES);
			dao.save(prProductInfo3);

			PrProductInfo prProductInfo4 = new PrProductInfo();
			prProductInfo4.setVatRate(new BigDecimal(18));
			prProductInfo4.setProductType(ProductType.TECHNOLOGY);
			dao.save(prProductInfo4);

			PrProductInfo prProductInfo5 = new PrProductInfo();
			prProductInfo5.setVatRate(new BigDecimal(18));
			prProductInfo5.setProductType(ProductType.CLEANING);
			dao.save(prProductInfo5);

			PrProductInfo prProductInfo6 = new PrProductInfo();
			prProductInfo6.setVatRate(new BigDecimal(18));
			prProductInfo6.setProductType(ProductType.OTHER);
			dao.save(prProductInfo6);

			PrProductInfo prProductInfo7 = new PrProductInfo();
			prProductInfo7.setVatRate(new BigDecimal(50));
			prProductInfo7.setProductType(ProductType.DUMMY);
			dao.save(prProductInfo7);
		};
	}

}
