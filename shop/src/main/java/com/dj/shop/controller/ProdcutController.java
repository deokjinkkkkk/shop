package com.dj.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.service.inf.ProductService;
import com.dj.shop.vo.Pagination;
import com.dj.shop.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProdcutController {
	private static final org.slf4j.Logger logger =org.slf4j.LoggerFactory.getLogger(ProdcutController.class);
	@Autowired
	ProductService productservice;
	
	@Value("${saveimg}")
	private String saveimg;
	
	@GetMapping("/list")
	public String productList(ProductVO vo, Model model, 
			@RequestParam(value = "page",required = false) Integer page
			,@RequestParam(value = "category",required = false) int category
			) {
		
		vo.setCategoryNum(category);
		
	    Pagination pagination = new Pagination();
	    //페이지의 값이 있는경우
	    if(page != null) {
	    	pagination.setPage(page);
	    }
	    
	    //상품 총 개수 
	    int totalCount = productservice.count(vo);
	    //상품 개수를 페이징 처리하기 위해 넣어주기
	    pagination.setTotalRecord(totalCount);
	    //보여질 페이지 vo에 담아주기
		//상품 페이지 출력
		List<ProductVO> tableList = productservice.getAllSangpums(vo);
	    // 사이드바 카테고리 데이터
		List<ProductVO> categories = productservice.categoryList(vo);
	    
	    model.addAttribute("tableList", tableList);
	    model.addAttribute("categories", categories);
	    model.addAttribute("pagination", pagination);
	    return "pages/product/productList";
	}
	
	@GetMapping("/detail")
	public String prDetail(ProductVO vo,
			@RequestParam("proNum") int productId,
			Model model) {

		List<ProductVO> categories = productservice.categoryList(vo);
		
		vo = productservice.getData(productId);
		
		model.addAttribute("pro", vo);
		model.addAttribute("categories", categories);
	    return "pages/product/productDetail";
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
		System.out.println(vo.getProductNum());
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
		return "pages/product/productUpdate";			
	}
	
	@GetMapping("/search")
	public String searchForm(ProductVO vo, Model model, 
							@RequestParam(value = "page",required = false) Integer page
							,@RequestParam(value = "pageunit",required = false) Integer pageunit) {
	    Pagination pagination = new Pagination();
	    //페이지의 값이 있는경우
	    if(page != null) {
	    	pagination.setPage(page);
	    }
	    
	    //상품 총 개수 
	    int totalCount = productservice.count(vo);
	    //상품 개수 에 맞춰서 출력
		if(pageunit != null){
			pagination.setPageUnit(pageunit);
		}
		
	    pagination.setTotalRecord(totalCount);
	    //보여질 페이지 vo에 담아주기
		vo.setFirst(pagination.getFirst());
		vo.setLast(pagination.getLast());
		//상품 페이지 출력
		List<ProductVO> productList = productservice.productList(vo);
	    model.addAttribute("keyword", vo.getKeyword());
	    model.addAttribute("productList", productList);
	    model.addAttribute("pagination", pagination);
		logger.info("상품 완료");
		return "pages/product/productSearch";			
	}
	
	@PostMapping("/productDelete")
	public String delProduct(ProductVO vo ) {
		productservice.deleteMarket(vo);
		return "redirect:/";			
	}
	
	

}
