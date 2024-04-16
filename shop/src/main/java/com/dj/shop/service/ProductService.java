package com.dj.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;

public interface ProductService {
	//상품 개수
	public int count(ProductVO vo);  
	//모든 상품 리스트 페이징 기능
	public List<ProductVO> getAllSangpums(ProductVO vo); 
	//상품 등록
	public int insertMarket(ProductVO vo); 
	//상품 상세정보
	public ProductVO getData(int num);
	//상품 수정
	public int updateMarket(ProductVO vo); 
	//상품 삭제
	public int deleteMarket(ProductVO vo); 
	//카테고리별 상품 리스트
	public List<ProductVO> categoryList(ProductVO vo); 
	//상품 이미지 등록
	public String saveImage(MultipartFile file, String saveFolder); 
}
