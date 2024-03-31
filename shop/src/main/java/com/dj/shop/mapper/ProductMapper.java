package com.dj.shop.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;

public interface ProductMapper {
	public int count(ProductVO vo);  //상품 개수
	public List<ProductVO> getAllSangpums(ProductVO vo); //모든 상품 리스트
	public int insertMarket(ProductVO vo); // 상품 등록
	public ProductVO getData(int num); //상품 상세정보
	public int updateMarket(ProductVO vo); //상품 수정
	public int deleteMarket(ProductVO vo); //상품 삭제
	public List<ProductVO> categoryList(ProductVO vo); //카테고리별 상품 리스트
	public String saveImage(MultipartFile file, String saveFolder); //상품 이미지 등록
}
