package fr.insa.fmc.javaback;

import fr.insa.fmc.javaback.service.GenerationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaBackApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testGenerationCode(){
		String a = GenerationService.GenerateCode();
		String b = GenerationService.GenerateCode();
		System.out.println(a);
		System.out.println(b);
		assert(!a.equals(b));
	}


}
