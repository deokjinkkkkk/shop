package com.dj.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;

public interface ProductService {
	public int getTotalCountOfMarket(); // getTotalCountOfMarket이 id(framework에서 id로 썼던거) 역할을 해준다.
	public List<ProductVO> getAllSangpums(ProductVO vo);
	public int insertMarket(ProductVO vo);
	public ProductVO getData(String num);
	public void updateMarket(ProductVO vo);
	public void deleteMarket(String num);
	
	public String saveImage(MultipartFile imgFile, String saveFolder);
}
