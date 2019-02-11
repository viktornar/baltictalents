package lt.baltictalents.lessons.api.exception;

public class ProductNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ProductNotFoundException(Long id) {
				super("Could not find product with id: " + id);
		}
}