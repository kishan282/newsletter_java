package com.nks.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nks.admin.dto.response.AddAdminOrEditorResponse;
import com.nks.admin.dto.response.GetUserDetails;
import com.nks.admin.dto.response.NewsSubmitResponse;
import com.nks.admin.validation.JwtUtil;

@RestController
@RequestMapping("/newsletter")
public class MainController {

	@Autowired
	private MainService mainService;

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@PostMapping(path = "/submit_story", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addStory(@RequestParam("story") String story,
			@RequestParam("image") MultipartFile[] files) {
		try {
			final NewsSubmitResponse newsSubmitResponse = mainService.saveStory(story, files);
			return ResponseEntity.status(HttpStatus.CREATED).body(newsSubmitResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(path = "/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add_Admin(@RequestParam("profiledetails") String profiledetails,
			@RequestParam("profileimage") MultipartFile profilepicture) throws CloneNotSupportedException, IOException {
		final AddAdminOrEditorResponse addAdminOrEditorResponse = mainService.saveUserdetails(profiledetails,
				profilepicture);
		return ResponseEntity.status(HttpStatus.CREATED).body(addAdminOrEditorResponse);
	}

	// @PreAuthorize("hasAnyRole('USER')")
	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchUserDetails() throws CloneNotSupportedException {
		final GetUserDetails getUserDetails = mainService.getUsers(JwtUtil.getUserid());
		return ResponseEntity.status(HttpStatus.OK).body(getUserDetails);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(path = "/delete")
	public ResponseEntity<?> deleteUser(@RequestParam(required = true) String username) {
		mainService.deleteUser(username);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("User deleted");
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(path = "/allusers")
	public ResponseEntity<?> fetchAllAdminsAndUsers() {

		List<GetUserDetails> users = mainService.fetchAllAdminsAndUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

}
