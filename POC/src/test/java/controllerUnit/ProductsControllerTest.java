package controllerUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controller.ProductsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

	private static final String PRODUCT_URL = "/products/13860429";
	private MockMvc mockMvc;
	
	@Mock
    private ProductService productService;
	
	@InjectMocks
	ProductsController productsController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
	}
		
	@Test
	public void getProduct_Returns200() throws Exception {
		mockMvc.perform(get(PRODUCT_URL)).andExpect(status().isOk());
		verify(this.productService, times(1)).findProduct(eq(13860429));
		
	}

}
