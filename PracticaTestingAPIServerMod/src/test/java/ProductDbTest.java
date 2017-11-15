
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.common.collect.Iterables;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.iwcn.master.entities.Product;
import com.iwcn.master.repositories.ProductRepository;
import com.iwcn.master.services.ProductDataBase;


@RunWith(SpringRunner.class)
public class ProductDbTest {

	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductDataBase productDB;
	
	@Before
	public void Init() {
		productDB=new ProductDataBase();
		MockitoAnnotations.initMocks(this);
	}
	
	public void TestSingleProduct(Product testProduct)
	{
		when(productRepository.findOne(anyLong())).then(answer ->{return testProduct;});
		//Pruebo los productos usando sus Gets, para que la prueba sea del Servicio, no de Product
		Assert.assertEquals("wrong Name", testProduct.getName(),productDB.getProduct(1).getName());
		Assert.assertEquals("wrong Price", testProduct.getPrice(),productDB.getProduct(1).getPrice(),0.01);
		Assert.assertEquals("wrong Description", testProduct.getDescription(),productDB.getProduct(1).getDescription());
		Assert.assertEquals("wrong Size", testProduct.getSize(),productDB.getProduct(1).getSize());
		Assert.assertEquals("wrong Origin", testProduct.getOrigin(),productDB.getProduct(1).getOrigin());
		Assert.assertEquals("wrong Year in Lot", testProduct.getYearLot(),productDB.getProduct(1).getYearLot());
		Assert.assertEquals("wrong Month in Lot", testProduct.getMonthLot(),productDB.getProduct(1).getMonthLot());
		Assert.assertEquals("worng Day in Lot", testProduct.getDayLot(),productDB.getProduct(1).getDayLot());
		Assert.assertEquals("wrong Lot writing", testProduct.writeLot(),productDB.getProduct(1).writeLot());
		Assert.assertEquals("wrong Id reading", testProduct.getId(),productDB.getProduct(1).getId());
	}
	
	@Test
	public void testGetSingleProduct() {
		Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		TestSingleProduct(testProduct);
	}
	
	@Test
	public void testGetAllProducts() {
		Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		Product testProduct2 = new Product("Peine", 11, "Azul", "Peq", "Francia",	2016, 6, 21);
		List<Product> testProducts = new ArrayList<>();
		when(productRepository.findAll()).then(answer -> {return testProducts;});
		testProducts.add(testProduct);
		testProducts.add(testProduct2);
		//Paso las pruebas de cada producto, para comprobar que sigue funcionando para todos los elementos de la lista
		this.TestSingleProduct(testProduct);
		this.TestSingleProduct(testProduct2);
		//Compruebo tamaño de la lista:
		Assert.assertEquals("wrong list size", 2,Iterables.size(this.productDB.getAll()));
		//Compruebo que cada producto pase el testing individual:
		StreamSupport.stream(this.productDB.getAll().spliterator(), false).forEach(streamProduct ->{
			this.TestSingleProduct(streamProduct);
		});
	}
	
	@Test
    public void testAddProduct() {
		//Genero un producto testProduct2
		Product testProduct2 = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		//Llamo al método de la clase
		this.productDB.addProduct(testProduct2);
		//Compruebo que se llama al método save del repositorio
		verify(productRepository).save(productCaptor.capture());
		//Capturo el producto (testProduct) que el servicio envía al repositorio
		Product testProduct = productCaptor.getValue();
		//Compruebo que los datos son los correctos
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
		Assert.assertEquals("wrong Price", testProduct.getPrice(),123,0.01);
		Assert.assertEquals("wrong Description", testProduct.getDescription(),"Bonito");
		Assert.assertEquals("wrong Size", testProduct.getSize(),"Med");
		Assert.assertEquals("wrong Origin", testProduct.getOrigin(),"USA");
		Assert.assertEquals("wrong Year in Lot", testProduct.getYearLot(),2017);
		Assert.assertEquals("wrong Month in Lot", testProduct.getMonthLot(),11);
		Assert.assertEquals("wrong Day in Lot", testProduct.getDayLot(),5);
    }
	
	@Test
    public void testEditProduct() {
		//Como el anterior
		Product testProduct2 = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		this.productDB.editProduct(testProduct2);
		verify(productRepository).save(productCaptor.capture());
		Product testProduct = productCaptor.getValue();
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
		Assert.assertEquals("wrong Price", testProduct.getPrice(),123,0.01);
		Assert.assertEquals("wrong Description", testProduct.getDescription(),"Bonito");
		Assert.assertEquals("wrong Size", testProduct.getSize(),"Med");
		Assert.assertEquals("wrong Origin", testProduct.getOrigin(),"USA");
		Assert.assertEquals("wrong Year in Lot", testProduct.getYearLot(),2017);
		Assert.assertEquals("wrong Month in Lot", testProduct.getMonthLot(),11);
		Assert.assertEquals("wrong Day in Lot", testProduct.getDayLot(),5);
    }
	
	@Test
    public void testDeleteProduct() {
		Long idToSend = (long) 2;
		ArgumentCaptor<Long> IdCaptor = ArgumentCaptor.forClass(Long.class);
		//Llamo al método delete de mi DB con un ID conocido (en este caso 2)
		this.productDB.deleteProduct(idToSend);
		//Compruebo que llama al delete de mi repositorio, y capturo el valor de la Id enviada.
		verify(productRepository).delete(IdCaptor.capture());
		Assert.assertEquals("wrong delete Id sent", idToSend, IdCaptor.getValue());
    }
	
}
