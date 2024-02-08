package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.ProductVO;

public interface ProductMapper {
	public int getTotalCountOfMarket(); // getTotalCountOfMarket이 id(framework에서 id로 썼던거) 역할을 해준다.
	public List<ProductVO> getAllSangpums();
	public void insertMarket(ProductVO vo);
	public ProductVO getData(String num);
	public void updateMarket(ProductVO vo);
	public void deleteMarket(String num);
}
