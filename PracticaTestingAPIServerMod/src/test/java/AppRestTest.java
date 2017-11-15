import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import com.iwcn.master.controllers.AppController;
import com.iwcn.master.entities.Product;
import com.iwcn.master.services.ProductDataBase;

@RunWith(SpringRunner.class)
public class AppRestTest {

	@Mock
	private ProductDataBase Service;
	
	@InjectMocks
	private AppController AppRest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
    	AppRest = new AppController();
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(AppRest).build();

    }

	@Test
	public void testShowProducts() throws Exception {
        ArrayList<Product> testList = new ArrayList<Product>();
        Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
        testList.add(testProduct);
        when(Service.getAll()).then(answer ->{return testList;});
		
		this.mockMvc.perform(get("/productList").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.[0].name").value("Ordenador"));
	}

	@Test
	public void testShowProduct() throws Exception{
		Product testProduct = new Product("Ordenador", 123, "Bonito", "Med", "USA",	2017, 11, 5);
		when(Service.getProduct(anyLong())).then(answer ->{return testProduct;});
		this.mockMvc.perform(get("/showProduct?index=1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(jsonPath("$.name").value("Ordenador"));
	}

	@Test
	public void testEditProduct() throws Exception {
		String putBody = "{\"name\":\"Ordenador\",\"price\":\"123\",\"description\":\"Bonito\",\"size\":\"Med\",";
		putBody += "\"origin\":\"USA\",\"yearLot\":\"2017\",\"monthLot\":\"11\",\"dayLot\":\"5\",\"index\":\"1\"}";
		this.mockMvc.perform(put("/editProduct").contentType(MediaType.APPLICATION_JSON).content(putBody));
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(Service).editProduct(productCaptor.capture());
		Product testProduct = productCaptor.getValue();
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
	}

	@Test
	public void testDeleteProduct() throws Exception {
		this.mockMvc.perform(delete("/deleteProduct?index=1"));
		verify(Service).deleteProduct((long) 1);
	}

	@Test
	public void testAddProduct() throws Exception {
		String postBody = "{\"name\":\"Ordenador\",\"price\":\"123\",\"description\":\"Bonito\",\"size\":\"Med\",";
		postBody += "\"origin\":\"USA\",\"yearLot\":\"2017\",\"monthLot\":\"11\",\"dayLot\":\"5\",\"index\":\"1\"}";
		this.mockMvc.perform(post("/addProduct").contentType(MediaType.APPLICATION_JSON).content(postBody));
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(Service).addProduct(productCaptor.capture());
		Product testProduct = productCaptor.getValue();
		Assert.assertEquals("wrong Name", testProduct.getName(),"Ordenador");
	}

}
