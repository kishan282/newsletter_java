package com.nks.admin;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nks.admin.dto.request.AddAdminOrEditorRequest;
import com.nks.admin.dto.request.NewsSubmitRequest;
import com.nks.admin.dto.response.AddAdminOrEditorResponse;
import com.nks.admin.dto.response.GetUserDetails;
import com.nks.admin.dto.response.NewsSubmitResponse;
import com.nks.admin.model.NewsImageEntity;
import com.nks.admin.model.NewsSubmitEntity;
import com.nks.admin.model.UserCredentials;
import com.nks.admin.model.UserDetailsEntity;
import com.nks.admin.repositories.NewsSubmitRepo;
import com.nks.admin.repositories.UserCredentialsRepo;
import com.nks.admin.repositories.UserDetailsRepo;
import com.nks.admin.validation.JSONParameterValidator;

@Service
public class MainService {

	@Autowired
	private NewsSubmitRepo newsSubmitRepo;

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserCredentialsRepo userCredsRepo;

	private Object Id = null;
	private String responseMessage = null;

	public NewsSubmitResponse saveStory(String story, MultipartFile[] files) {

		try {

			final NewsSubmitRequest newsSubmitRequest = new ObjectMapper().readValue(story, NewsSubmitRequest.class);

			final List<MultipartFile> listOffiles = Arrays.asList(files);

			new JSONParameterValidator(newsSubmitRequest);

			NewsSubmitEntity entity = new NewsSubmitEntity(newsSubmitRequest.getHeadline(),
					newsSubmitRequest.getCategories(), newsSubmitRequest.getStates_or_ut(),
					newsSubmitRequest.getStory());

			final NewsImageEntity.ImageBuilder builder = NewsImageEntity.builder(entity);

			listOffiles.stream().forEach(file -> {
				try {
					entity.addNewsImageEntity(builder.images(ImageUtil.compressImage(file.getBytes())).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			Id = newsSubmitRepo.save(entity).getArticleID();
			responseMessage = "Thank you for your input";

		} catch (Exception e) {

			responseMessage = "Some problem, please try after sometime";
			e.printStackTrace();

		}

		final NewsSubmitResponse response = new NewsSubmitResponse((Long) Id, responseMessage);

		return response;
	}

	public AddAdminOrEditorResponse saveUserdetails(String profiledetails, MultipartFile profilepicture)
			throws CloneNotSupportedException, IOException {

		final AddAdminOrEditorRequest addAdminOrEditorRequest = new ObjectMapper().readValue(profiledetails,
				AddAdminOrEditorRequest.class);

		new JSONParameterValidator(addAdminOrEditorRequest);

		UserCredentials creds = new UserCredentials(addAdminOrEditorRequest.getEmailId(),
				passwordEncoder.encode(addAdminOrEditorRequest.getUserPassword()),
				addAdminOrEditorRequest.getUserRole());

		final UserDetailsEntity.UserBuilder builder = UserDetailsEntity.builder(addAdminOrEditorRequest.getFirstName(),
				addAdminOrEditorRequest.getLastName(), addAdminOrEditorRequest.getEmailId(),
				addAdminOrEditorRequest.getUserAddress(), addAdminOrEditorRequest.getUserState(),
				addAdminOrEditorRequest.getUserZipcode(), addAdminOrEditorRequest.getUserContact(), creds);

		//builder.build();
		
		creds.addUserDetails(builder.addUserImage(ImageUtil.compressImage(profilepicture.getBytes())).build());

		Id = userCredsRepo.save(creds).getEmailId();
		responseMessage = "Thank you to be our part!";

		final AddAdminOrEditorResponse response = new AddAdminOrEditorResponse((String) Id, responseMessage);
		return response;

	}

	public GetUserDetails getUsers(String username) throws CloneNotSupportedException {

		UserDetailsEntity userDetailsEntity = userDetailsRepo.findByEmailId(username);

		final GetUserDetails user = new GetUserDetails(userDetailsEntity.getFirstName(),
				userDetailsEntity.getLastName(), userDetailsEntity.getEmailId(),
				userDetailsEntity.getUserCreds().getUserRole());

		return user;

	}

	public AddAdminOrEditorResponse deleteUser(String username) {

		try {

			userDetailsRepo.deleteById(username);
			Id = username;
			responseMessage = "User has been deleted successfully";

		} catch (Exception e) {
			Id = username;
			responseMessage = "User not found";
		}

		final AddAdminOrEditorResponse response = new AddAdminOrEditorResponse((String) Id, responseMessage);
		return response;
	}

	public List<GetUserDetails> fetchAllAdminsAndUsers() {

		List<UserDetailsEntity> listOfUsers = userDetailsRepo.findAll();

		List<GetUserDetails> dtoUsers = listOfUsers.stream().map(user -> {
			try {
				return new GetUserDetails(user.getFirstName(), user.getLastName(), user.getEmailId(),
						user.getUserCreds().getUserRole());
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());

		return dtoUsers;

	}

}
