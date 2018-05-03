package fr.insa.fmc.javaback;

import fr.insa.fmc.javaback.service.GenerationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaBackApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testGenerationCode(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			Date date = format.parse("2018-05-03T22:00:00.000Z");
			System.out.println(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}
