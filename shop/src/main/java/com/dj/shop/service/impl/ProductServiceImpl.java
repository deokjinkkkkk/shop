package com.dj.shop.service.impl;

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
import com.dj.shop.service.inf.ProductService;
import com.dj.shop.vo.ProductVO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j2
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
		//이미지 삭제
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
                // 고유한 파일 이름 생성
                fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                File uploadFile = new File(saveFolder, fileName);

                // 임시 파일로 저장
                File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
                file.transferTo(tempFile);

                // 이미지 크기 조정 (예: 800x600)
                Thumbnails.of(tempFile)
                          .size(800, 600)
                          .toFile(uploadFile);

                // 임시 파일 삭제
                tempFile.delete();

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

	@Override
	public List<ProductVO> productList(ProductVO vo) {
		log.info("get");
		return product.productList(vo);
	}

}
