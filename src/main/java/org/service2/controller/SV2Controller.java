package org.service2.controller;

import org.service2.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SV2Controller {
	@Autowired
	private MessageQueueService messageQueueService;

	@PostMapping("/send-message")
	public ResponseEntity<String> sendMessage(@RequestBody String message) {
		messageQueueService.sendMessage(message);
		return ResponseEntity.ok("Message sent successfully");
	}
}