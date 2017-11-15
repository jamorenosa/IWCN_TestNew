import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.iwcn.master.PracticaTestingBootApp;
import com.iwcn.master.entities.Product;

@RunWith(SpringRunner.class)
public class DummyTest {

	@Test
	public void testProductAllArgsConstructor() {
		//Compruebo el constructor
		Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
		Assert.assertEquals("wrong Price", testProduct.getPrice(),123,0.01);
		Assert.assertEquals("wrong Description", testProduct.getDescription(),"Bonito");
		Assert.assertEquals("wrong Size", testProduct.getSize(),"Med");
		Assert.assertEquals("wrong Origin", testProduct.getOrigin(),"USA");
		Assert.assertEquals("wrong Year in Lot", testProduct.getYearLot(),2017);
		Assert.assertEquals("wrong Month in Lot", testProduct.getMonthLot(),11);
		Assert.assertEquals("worng Day in Lot", testProduct.getDayLot(),5);
		Assert.assertEquals("wrong Lot writing", testProduct.writeLot(),"5-11-2017");
	}
	
	@Test
	public void testProductNoArgsConstructor() {
		//Compruebo el constructor
		//Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		Product testProduct = new Product();
		testProduct.setName("Ordenador");
		testProduct.setPrice(123);
		testProduct.setDescription("Bonito");
		testProduct.setSize("Med");
		testProduct.setOrigin("USA");
		testProduct.setYearLot(2017);
		testProduct.setMonthLot(11);
		testProduct.setDayLot(5);
		testProduct.setId(1);
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
		Assert.assertEquals("wrong Price", testProduct.getPrice(),123,0.01);
		Assert.assertEquals("wrong Description", testProduct.getDescription(),"Bonito");
		Assert.assertEquals("wrong Size", testProduct.getSize(),"Med");
		Assert.assertEquals("wrong Origin", testProduct.getOrigin(),"USA");
		Assert.assertEquals("wrong Year in Lot", testProduct.getYearLot(),2017);
		Assert.assertEquals("wrong Month in Lot", testProduct.getMonthLot(),11);
		Assert.assertEquals("wronng Day in Lot", testProduct.getDayLot(),5);
		Assert.assertEquals("wrong Lot writing", testProduct.writeLot(),"5-11-2017");
	}
	
}
