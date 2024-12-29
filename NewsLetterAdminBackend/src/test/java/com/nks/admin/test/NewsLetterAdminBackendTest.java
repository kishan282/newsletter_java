package com.nks.admin.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nks.admin.MainService;
import com.nks.admin.customexceptionhandler.JavaJsonMapperException;
import com.nks.admin.dto.response.AddAdminOrEditorResponse;

@SpringBootTest
public class NewsLetterAdminBackendTest {

	@Autowired
	private MainService service;

	@Test
	public void saveUserdetailsTest() throws CloneNotSupportedException, IOException {

		try {

			String user = "{\r\n" + "    \"emailId\": \"eca.oist2016@gmail.com\",\r\n"
					+ "    \"userAddress\": \"Pune\",\r\n" + "    \"userState\": \"Maharashtra\",\r\n"
					+ "    \"userZipcode\": \"851213\",\r\n" + "    \"userContact\": \"8839045054\",\r\n"
					+ "    \"userRole\": \"ADMIN\",\r\n" + "    \"userPassword\": \"IamtestingneedEncryption\"\r\n"
					+ "}";
			
			AddAdminOrEditorResponse response = service.saveUserdetails(user, null);

			System.out.println(response);

			assertEquals("eca.oist2016@gmail.com", response.getUserId());

		} catch (JavaJsonMapperException jx) {
			System.out.println(jx.getMessage());
		}
	}

}
