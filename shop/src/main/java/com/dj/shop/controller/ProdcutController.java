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
import com.dj.shop.vo.Criteria;
import com.dj.shop.vo.PageVO;
import com.dj.shop.vo.ProductVO;

@Controller
public class ProdcutController {
	@Autowired
	ProductService productservice;
	
	@Value("${saveimg}")
	private String saveimg;
	
	@GetMapping("/admin/product")
	public String product() {
		return "pages/product";
	}
	
	@GetMapping("/table")
	public String tableList(@ModelAttribute ProductVO vo, Model model, Criteria cri) {
		vo.setCategoryNum(2);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		model.addAttribute("tableList",tableList);
	    return "pages/table";
	}
	@GetMapping("/bed")
	public String bedList(@ModelAttribute ProductVO vo, Model model, Criteria cri) {
		vo.setCategoryNum(1);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		model.addAttribute("tableList",tableList);
	    return "pages/table";
	}
	@GetMapping("/chair")
	public String chairList(@ModelAttribute ProductVO vo, Model model, Criteria cri) {
		vo.setCategoryNum(3);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		model.addAttribute("tableList",tableList);
	    return "pages/table";
	}
	@GetMapping("/diningtable")
	public String diningtableList(@ModelAttribute ProductVO vo, Model model, Criteria cri) {
		vo.setCategoryNum(4);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		model.addAttribute("tableList",tableList);
	    return "pages/table";
	}
	@GetMapping("/closet")
	public String closetList(@ModelAttribute ProductVO vo, Model model, Criteria cri, PageVO pvo) {
		vo.setCategoryNum(5);
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
		pvo.setCri(cri);
		pvo.setTotal(0);
		model.addAttribute("tableList",tableList);
		model.addAttribute("page",pvo);
	    return "pages/table";
	}
	
	@GetMapping("/detail")
	public String prDetail(@RequestParam("proNum") int productId, Model model) {
		
		ProductVO product =	productservice.getData(productId);
		model.addAttribute("pro", product);
	    return "pages/deProduct";
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

	    return "redirect:/";
	}
	

}
