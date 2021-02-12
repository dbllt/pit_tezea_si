package tezea.si.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ints")
public class TestController {

	@GetMapping
	@ResponseBody
	public List<Integer> getAllBoards() {
		return List.of(5);
	}
}