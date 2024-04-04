package com.dj.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.service.ProductService;
import com.dj.shop.vo.OrderPageVO;
import com.dj.shop.vo.Pagination;
import com.dj.shop.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProdcutController {
	@Autowired
	ProductService productservice;
	
	@Value("${saveimg}")
	private String saveimg;
	
	@GetMapping("/admin")
	public String product() {
		return "pages/product";
	}
	
	@GetMapping("/list")
	public String productList(ProductVO vo, Model model, 
			@RequestParam(value = "page",required = false) Integer page
			,@RequestParam(value = "category",required = false) int category
			) {
		
		vo.setCategoryNum(category);
		
	    Pagination pagination = new Pagination();
	    if(page != null) {
	    pagination.setPage(page);
	    }
	    pagination.setPageSize(5); //한 페이지에 보여질 페이지 갯수	
	    pagination.setPageUnit(9);//한 페이지에 출력할 레코드 건수
	    
	    int totalCount = productservice.count(vo);
	    pagination.setTotalRecord(totalCount);
	    
		vo.setFirst(pagination.getFirst());
		vo.setLast(pagination.getLast());
		
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
	   
		List<ProductVO> categories = productservice.categoryList(vo);
	    
	    model.addAttribute("pagination", pagination);
	    model.addAttribute("tableList", tableList);
	    model.addAttribute("categories", categories);
	    
	    return "pages/table";
	}
	
	@GetMapping("/detail")
	public String prDetail(@RequestParam("proNum") int productId, Model model) {
		
		ProductVO product =	productservice.getData(productId);
		model.addAttribute("pro", product);
	    return "pages/deProduct";
	}
	@PostMapping("/uploadProduct")
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
	@PostMapping("/productUpdate")
	public String updateProduct(ProductVO vo,
			@RequestParam("proImage1") MultipartFile proImage1,
            @RequestParam("proImage2") MultipartFile proImage2,
            @RequestParam("proImage3") MultipartFile proImage3) {
		if(proImage1 != null) {
			String fileName1 = productservice.saveImage(proImage1, saveimg);
		}
		if(proImage2 != null) {
			String fileName2 = productservice.saveImage(proImage2, saveimg);
		}
		if(proImage3 != null) {
			String fileName3 = productservice.saveImage(proImage3, saveimg);  
		}
		productservice.updateMarket(vo); 
		return "redirect:/";			
	}
	
	@GetMapping("/updateForm")
	public String updateProductForm(ProductVO vo,Model model,
			@RequestParam("productNum") int productId) {
		ProductVO product =	productservice.getData(productId);
		model.addAttribute("pro", product);
		return "pages/updateProduct";			
	}
	
	@GetMapping("/search")
	public String searchForm() {
		
		return "pages/search";			
	}
	
	@PostMapping("/productDelete")
	public String delProduct(ProductVO vo ) {
		productservice.deleteMarket(vo);
		return "redirect:/";			
	}
	

}
