package mauricio.ccasani.examenjavaspring.service.exception;

public class ExceptionService extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionService() {
		// TODO Auto-generated constructor stub
	}

	public ExceptionService(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExceptionService(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExceptionService(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExceptionService(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
