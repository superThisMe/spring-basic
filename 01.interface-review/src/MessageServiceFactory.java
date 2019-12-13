import java.io.FileInputStream;
import java.util.Properties;

public class MessageServiceFactory {

//	public MessageService getInstance(String type) {
//		switch (type) {
//		case "1":
//			return new MessageServiceImpl();
//		case "2":
//			return new MessageServiceImpl2();
//		default:
//			return null;
//		}
//
//	}

	public MessageService getInstance(String type) {

		try {
			// xxx.properties file의 data를 읽어서 key-value 형식으로 관리하는 collection object
			Properties props = new Properties();

			FileInputStream fis = new FileInputStream("mapping.properties");
			props.load(fis); // 이 때 파일의 데이터를 읽어서 collection 구성

			String typeName = props.getProperty(type); // String typeName 찾기

			@SuppressWarnings("rawtypes")
			Class claz = Class.forName(typeName); // String typeName -> class object 만들기
			MessageService messageService = (MessageService) claz.newInstance(); // new를 이용한 object 생성 구문과 동일한 기능

			return messageService;
		} catch (Exception ex) {
			return null;
		}

	}

}
