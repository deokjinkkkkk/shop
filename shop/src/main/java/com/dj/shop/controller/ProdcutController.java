package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.service.ProductService;
import com.dj.shop.vo.ProductVO;

@RestController
@RequestMapping("/product")
public class ProdcutController {
	@Autowired
	ProductService productservice;
	
	@Value("${saveimg}")
	private String saveimg;
	
	@PostMapping("/uploadProduct")
	public String uploadProduct(ProductVO vo,
	                            @RequestParam("proImage1") MultipartFile proImg1,
	                            @RequestParam("proImage2") MultipartFile proImg2,
	                            @RequestParam("proImage3") MultipartFile proImg3) {

	    String fileName1 = productservice.saveImage(proImg1, saveimg);
	    String fileName2 = productservice.saveImage(proImg2, saveimg);
	    String fileName3 = productservice.saveImage(proImg3, saveimg);
	    
	    vo.setProImg1(fileName1);
	    vo.setProImg2(fileName2);
	    vo.setProImg3(fileName3);

	    productservice.insertMarket(vo);

	    return "redirect:";
	}
}
