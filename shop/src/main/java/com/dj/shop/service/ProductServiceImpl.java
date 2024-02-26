package com.dj.shop.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;
@Service
public class ProductServiceImpl implements ProductService{

	@Override
	public int getTotalCountOfMarket() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductVO> getAllSangpums() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMarket(ProductVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductVO getData(String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMarket(ProductVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMarket(String num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveImage(MultipartFile[] file, String saveFolder) {
		String fileName = null; //파일 저장 경로
		for (MultipartFile file1 : file) {
			try {
				if (!file1.isEmpty()) {
					fileName = UUID.randomUUID().toString() + file1.getOriginalFilename();
					File uploadFile = new File(saveFolder, fileName);
					file1.transferTo(uploadFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

}
