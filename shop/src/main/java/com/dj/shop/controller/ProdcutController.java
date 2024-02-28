package com.dj.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.service.ProductService;
import com.dj.shop.vo.ProductVO;

@Controller
public class ProdcutController {
	@Autowired
	ProductService productservice;
	
	@Value("${saveimg}")
	private String saveimg;
	
	@GetMapping("/alltable")
	public String tableList(@ModelAttribute ProductVO vo, Model model) {
		vo.setCategoryNum(2);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		model.addAttribute("tableList",tableList);
	    return "pages/table";
	}
	
	@PostMapping("/product/uploadProduct")
	public String uploadProduct(ProductVO vo,
	                            @RequestParam("proImage1") MultipartFile proImage1,
	                            @RequestParam("proImage2") MultipartFile proImage2,
	                            @RequestParam("proImage3") MultipartFile proImage3) {
		if(vo != null) {
			String fileName1 = productservice.saveImage(proImage1, saveimg);
		    String fileName2 = productservice.saveImage(proImage2, saveimg);
		    String fileName3 = productservice.saveImage(proImage3, saveimg);
		    vo.setProImg1(fileName1);
		    vo.setProImg2(fileName2);
		    vo.setProImg3(fileName3);

		    productservice.insertMarket(vo);
		    
		}
	    System.out.println(vo.getCategoryNum());
	    System.out.println(vo.getProCnt());
	    System.out.println(vo.getProContent());
	    System.out.println(vo.getProName());
	    System.out.println(vo.getProPrice());
	    System.out.println(vo.getProImg1());
	    System.out.println(vo.getProImg2());
	    System.out.println(vo.getProImg3());
	    return "redirect:/";
	}
	

}
