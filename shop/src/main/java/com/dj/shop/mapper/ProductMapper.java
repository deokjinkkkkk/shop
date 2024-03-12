package com.dj.shop.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;

public interface ProductMapper {
	public int count(ProductVO vo); 
	public List<ProductVO> getAllSangpums(ProductVO vo);
	public int insertMarket(ProductVO vo);
	public ProductVO getData(int num);
	public int updateMarket(ProductVO vo);
	public int deleteMarket(String num);
	
	public String saveImage(MultipartFile file, String saveFolder);
}
