package com.dj.shop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dj.shop.mapper.ProductMapper;
import com.dj.shop.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{
	@Value("${saveimg}")
	private String saveimg;
	@Autowired
	private ProductMapper product;
	
	@Override
	public int count(ProductVO vo) {
		return product.count(vo);
	}

	@Override
	public List<ProductVO> getAllSangpums(ProductVO vo) {

		return product.getAllSangpums(vo);
	}

	@Override
	public int insertMarket(ProductVO vo) {
		return product.insertMarket(vo);		
	}

	@Override
	public ProductVO getData(int num) {
		
		return product.getData(num);
	}

	@Override
	public int updateMarket(ProductVO vo) {
		return product.updateMarket(vo);
			
	}

	@Override
	public int deleteMarket(ProductVO vo) {
		ProductVO pro = product.getData(vo.getProductNum());
		
		if(pro != null) {
			Path path1 ;
			Path path2 ;
			Path path3 ;
			if(pro.getProImg1() != null) {
				path1 = Paths.get(saveimg, pro.getProImg1());
				path1.toFile().delete();
			}
			if(pro.getProImg2() != null) {
				path2 = Paths.get(saveimg, pro.getProImg2());
				path2.toFile().delete();
			}
			if(pro.getProImg3() != null) {
				 path3 = Paths.get(saveimg, pro.getProImg3());
				 path3.toFile().delete();
			}
				
		}
		
		return product.deleteMarket(vo);
				
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
				e.printStackTrace();
			}
           
        }
		return fileName;
		
	}

	@Override
	public List<ProductVO> categoryList(ProductVO vo) {
		int category = vo.getCategoryNum();
		vo.setCategoryNum(category);
		
		return product.categoryList(vo);
	}

}
