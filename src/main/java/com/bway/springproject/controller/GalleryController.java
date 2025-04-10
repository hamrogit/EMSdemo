package com.bway.springproject.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

	@GetMapping("/gallery")
	public String getGallery(Model model) {

		String[] imageNames = new File("src/main/resources/static/image").list();

		model.addAttribute("imageNameList", imageNames);
		return "galleryform";
	}

}
