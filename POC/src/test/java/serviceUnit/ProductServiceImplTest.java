package serviceUnit;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.PriceRepository;
import dao.ProductsDao;
import exceptions.ProductNotFoundException;
import model.CurrencyPriceModel;
import service.ProductServiceImpl;

public class ProductServiceImplTest {

	@Mock
	private PriceRepository priceRepository;

	@Mock
	private ProductsDao productsDao;

	@Mock
	private CurrencyPriceModel currencyPriceModel;

	@InjectMocks
	ProductServiceImpl productService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindProduct() throws ProductNotFoundException {
		when(this.priceRepository.findByProductId(13860429)).thenReturn(this.currencyPriceModel);
		this.productService.findProduct(13860429);
		verify(this.priceRepository, times(1)).findByProductId(eq(13860429));
		verify(this.productsDao, times(1)).findProductNameById(eq(13860429));
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void getProduct_Returns404() throws ProductNotFoundException {
		when(this.priceRepository.findByProductId(13860429)).thenReturn(null);
		this.productService.findProduct(13860429);
	}

}
