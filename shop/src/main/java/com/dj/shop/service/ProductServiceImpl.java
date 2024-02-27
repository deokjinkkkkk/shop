package com.dj.shop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.vo.ProductVO;
@Service
public class ProductServiceImpl implements ProductService{
	@Value("${saveimg}")
	private String saveimg;
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
	public String saveImage(MultipartFile file, String saveFolder) {
		String fileName = null;
		if (file != null && !file.isEmpty()) {
            try {
            	fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
                File uploadFile = new File(saveFolder, fileName);
                // 파일을 서버에 업로드
                file.transferTo(uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        }
		return fileName;
		
	}

}
