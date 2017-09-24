package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import demo.DemoApplication;
import service.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class ProductsControllerTest {

	private static final String PRODUCT_URL = "/products/1360428";
	private MockMvc mockMvc;
	
	@Mock
    private ProductServiceImpl productService;
	
	@InjectMocks
	ProductsController productsController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
	}
	
	@Test
	public void getProduct_Returns404() throws Exception {
		when(productService.findProduct(1360428)).thenReturn(null);
		mockMvc.perform(get(PRODUCT_URL)).andExpect(status().isNotFound());
				
		
	}

}
