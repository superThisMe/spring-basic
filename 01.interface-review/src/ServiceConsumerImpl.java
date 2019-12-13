public class ServiceConsumerImpl implements ServiceConsumer {

	@Override
	public void doSomething() {

		// 1.
//		MessageServiceImpl messageService = new MessageServiceImpl();

		// 2.
//		MessageService messageService = new MessageServiceImpl();

		// 3.
		int no = (int) (Math.random() * 2) + 1;
		MessageServiceFactory factory = new MessageServiceFactory();
		MessageService messageService = factory.getInstance(no + "");

		String message = messageService.makeMessage();
		System.out.println(message);
	}

}
